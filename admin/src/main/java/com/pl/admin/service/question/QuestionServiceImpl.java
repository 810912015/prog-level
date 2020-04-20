package com.pl.admin.service.question;

import com.google.common.base.Throwables;
import com.pl.admin.dto.Bound;
import com.pl.admin.dto.PlayDto;
import com.pl.admin.dto.QuestionDto;
import com.pl.admin.service.runner.Engine;
import com.pl.admin.service.runner.EngineProvider;
import com.pl.admin.service.runner.Runner;
import com.pl.data.mapper.PassMapper;
import com.pl.data.mapper.QuestionMapper;
import com.pl.data.mapper.QuestionSourceMapper;
import com.pl.data.mapper.ValidationMapper;
import com.pl.data.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class QuestionServiceImpl implements QuestionService {
    private static Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);
    @Override
    public QuestionDto addOrUpdate(QuestionDto q) {
        Question qt = q.toQuestion();

        if(q.getId()==0){
            qm.insert(qt);

        }else{
            qm.updateByPrimaryKeyWithBLOBs(qt);
            ValidationExample ve=new ValidationExample();
            ve.createCriteria().andQidEqualTo(qt.getId());
            vm.deleteByExample(ve);

            QuestionSourceExample qse=new QuestionSourceExample();
            qse.createCriteria().andQidEqualTo((long)qt.getId());
            qsm.deleteByExample(qse);
        }
        List<Validation> vl = q.toValidation(qt.getId());
        int i=0;
        for (Validation v : vl) {
            vm.insert(v);
            q.getVali().get(i).setVid(v.getId());
            i++;
        }

        List<QuestionSource> sl=q.toSource(qt.getId());
        for(QuestionSource t:sl){
            qsm.insert(t);
        }

        q.setId(qt.getId());
        return q;
    }

    @Override
    public Engine.ExecuteResult pass(Pass p) {
        try {
            Engine je =ep.getByLang(p.getLang().toLowerCase());
            if(je==null){
                return new Engine.ExecuteResult(new Engine.Result(false, "未能找到"+p.getLang()+"编译环境"),null);
            }
            Engine.Result cr= je.compile(p.getSource());

            ValidationExample ve=new ValidationExample();
            ve.createCriteria().andQidEqualTo(p.getId());
            List<Validation> vl=vm.selectByExample(ve);
            List<Engine.Result> rr=new ArrayList<>();

            Engine.Result t0=je.execute(new String[]{});
            rr.add(t0);
            int i=1;
            for(Validation v :vl){
                String si=v.getInput();
                Engine.Result tr=je.execute(new String[]{si});
                boolean br=tr.getMsg().trim().equals(v.getOutput());
                String sr=br?String.format("测试%d通过",i+1):String.format("应为:%s,实际:%s",v.getOutput(),tr.getMsg());
                Engine.Result t=new Engine.Result(br,sr);
                rr.add(t);
                i++;
            }

            Engine.ExecuteResult r=new Engine.ExecuteResult(cr,rr);
            List<Pass> prr=toVali(p,r,vl);
            for(Pass pv:prr){
                if(pv.getUid()==null){
                    pv.setUid(-1);
                }
                if(pv.getEid()==null){
                    pv.setEid(-1);
                }

                prm.insert(pv);
            }
            return r;

        }catch (Exception e){
            String s=Throwables.getStackTraceAsString(e);
            logger.error(s);
            return new Engine.ExecuteResult(new Engine.Result(false, s),null);
        }
    }

    @Override
    public String play(PlayDto pd) {
        StringBuilder sb=new StringBuilder();
        Engine e=ep.getByLang(pd.getLang());
        Runner.Result er=e.doCompile(pd.getSource());
        //er.prettify();
        sb.append(er.getOut());
        sb.append(er.getErr());
        Runner.Result er2=e.doExecute(null);
        //er2.prettify();
        sb.append(er2.getOut());
        sb.append(er2.getErr());
        return sb.toString();
    }

    @Override
    public List<Pass> getByUidOrAid(Integer uid, String aid, PassArgs bound) {
        if(uid==null&&aid==null) return new ArrayList<>();
        bound.normalize();
        PassExample pe=new PassExample();
        PassExample.Criteria c=pe.handleQueryArgs(bound);
        c.andQidEqualTo(bound.getQid().intValue());
        if(uid!=null&&uid>0){
            c.andUidEqualTo(uid);
        }else if(!StringUtils.isEmpty(aid)){
            c.andAidEqualTo(aid);
        }
        pe.setLimit(10);
        pe.setOrderByClause("id desc");
        return prm.selectByExample(pe);
    }

    Pass make(Pass p,String gid){
        Pass v=new Pass();
        v.setGroupid(gid);
        v.setId(0);
        if(p.getEiid()!=null) {
            v.setEid((int) p.getEid());
        }
        v.setQid(p.getId());
        v.setDclt(new Date());
        v.setSource(p.getSource());
        v.setInput("");
        v.setAid(p.getAid());
        v.setRemarks("F");
        v.setSequence(0);
        v.setLang(p.getLang());
        v.setWeight(0);
        v.setVid(0);
        v.setUid(p.getUid());
        v.setStage("F");
        return v;
    }

    List<Pass> toVali(Pass p, Engine.ExecuteResult er,List<Validation> vl){
        List<Pass> r=new ArrayList<>();
        if(er==null){
            return r;
        }

        if(er.getCompile()!=null){
            Pass v=make(p,er.getGid());
            v.setOutput(er.getCompile().getMsg());
            v.setSuccess(er.getCompile().isSuccess()?"T":"F");
            v.setMsg(er.getCompile().getMsg());
            v.setEiid(p.getEiid());
            truncateOutput(v);
            r.add(v);
        }
        if(er.getTest()!=null&&er.getTest().size()>1){
            for(int i=1;i<er.getTest().size();i++){
                Engine.Result t=er.getTest().get(i);
                Validation tv=vl.get(i);
                Pass v=make(p,er.getGid());
                v.setInput(tv.getInput());
                v.setOutput(t.getMsg());
                v.setRemarks("T");
                v.setSequence(i);
                v.setSuccess(t.isSuccess()?"T":"F");
                // 通过才得分
                v.setWeight(!t.isSuccess()?0:tv.getWeight());
                v.setVid(tv.getId());
                v.setUid(p.getUid());
                v.setMsg(t.getMsg());
                v.setEiid(p.getEiid());
                truncateOutput(v);
                r.add(v);
            }
        }

        return r;
    }
    private void truncateOutput(Pass pr){
        if(pr.getOutput().length()>1000){
            pr.setOutput(pr.getOutput().substring(0,1000));
        }
        if(pr.getMsg().length()>1000){
            pr.setMsg(pr.getMsg().substring(0,1000));
        }
    }


    private QuestionMapper qm;
    private ValidationMapper vm;
    private PassMapper prm;
    private EngineProvider ep;
    private QuestionSourceMapper qsm;

    @Autowired
    public void setQm(QuestionMapper qm) {
        this.qm = qm;
    }

    @Autowired
    public void setVm(ValidationMapper vm) {
        this.vm = vm;
    }

    @Autowired
    public void setPrm(PassMapper prm) {
        this.prm = prm;
    }

    @Autowired
    public void setEp(EngineProvider ep) {
        this.ep = ep;
    }

    @Autowired
    public void setQsm(QuestionSourceMapper qsm) {
        this.qsm = qsm;
    }
}
