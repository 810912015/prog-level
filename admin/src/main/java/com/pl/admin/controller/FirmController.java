package com.pl.admin.controller;

import com.google.common.base.Throwables;
import com.pl.admin.dao.ScoreMapper;
import com.pl.admin.dto.FirmDto;
import com.pl.admin.dto.Pager;
import com.pl.admin.dto.Result;
import com.pl.admin.dto.ScoreDto;
import com.pl.admin.service.Notifier;
import com.pl.data.mapper.ExamInviteMapper;
import com.pl.data.mapper.FirmMapper;
import com.pl.data.model.ExamInvite;
import com.pl.data.model.Firm;
import com.pl.data.model.FirmExample;
import com.pl.data.model.Invitee;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

@Controller
@RequestMapping(value = "/firm")
@Api(tags = "FirmController",description = "FirmController api")
public class FirmController extends BaseController {
    @RequestMapping(value="/create")
    @ResponseBody
    public Result<Firm> create(@RequestBody FirmDto fd){

        Firm f=fd.toFirm();
        if(f.getId()!=null&&f.getId()>0) {
           fm.updateByPrimaryKey(f);
        }else {
            fm.insert(f);
        }
        return new Result<>(true,"",f);
    }
    @RequestMapping(value="/list")
    @ResponseBody
    public List<Firm> list(@RequestBody Pager.PagedQuery<FirmDto.FirmParam> p){
        FirmExample fe=null;
        if(p!=null&&p.getQuery()!=null){
            fe=p.getQuery().toExample();
        }else{
            fe=new FirmExample();
        }
        List<Firm> r= fm.selectByExample(fe);
        return r;
    }
    @RequestMapping(value="/get/{id}")
    @ResponseBody
    public Firm getById(@PathVariable("id") Integer id){
        return fm.selectByPrimaryKey(id);
    }

    @RequestMapping(value="/del/{id}")
    @ResponseBody
    public int delById(@PathVariable("id") Integer id){
        return fm.deleteByPrimaryKey(id);
    }

    @RequestMapping(value="/invite")
    @ResponseBody
    public Result<String> invite(@RequestBody InvitePrm ip){
        try {
            if (ip == null || !ip.valid()) {
                return new Result<>(false, "选择考试且至少有一个考生");
            }

            long uid = curUser().getId();
            Integer fid = curUser().getFirmid();
            List<ExamInvite> eil = ip.toInvite(uid, fid);
            for (ExamInvite ei : eil) {
                eim.insert(ei);
            }
            for (ExamInvite ei : eil) {
                notifier.sendInvite(ei.getUemail(), ei.getIid());
            }
            return new Result<>(true, "邀请成功", String.format("共邀请%d人", eil.size()));
        }catch (Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            return new Result<>(false,e.getMessage());
        }
    }

    @RequestMapping(value="/score")
    @ResponseBody
    public Pager.Paged<List<ScoreDto.Firm>> score(@RequestBody Pager.PagedQuery<Map<String,Object>> map){
        int fid=curUser().getFirmid();

        String in=(String)map.getQuery().get("iname");
        String en=(String) map.getQuery().get("ename");
        if(in==null){
            in="";
        }
        if(en==null){
            en="";
        }
        in="%"+in+"%";
        en="%"+en+"%";
        int start=map.getPager().start();
        int size=map.getPager().getSize();

        List<ScoreDto.Firm> l= sm.getByFirmId(fid,in,en,start,size);
        Integer total=sm.countByFirmId(fid,in,en);
        return new Pager.Paged<>(l, map.getPager(), total==null?0:total);
    }




    private FirmMapper fm;
    private ExamInviteMapper eim;
    private Notifier notifier;
    private ScoreMapper sm;

    @Autowired
    public void setFm(FirmMapper fm) {
        this.fm = fm;
    }

    @Autowired
    public void setEim(ExamInviteMapper eim) {
        this.eim = eim;
    }

    @Autowired
    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    @Autowired
    public void setSm(ScoreMapper sm) {
        this.sm = sm;
    }


    public static class InvitePrm{
        private Integer eid;
        private List<Invitee> iidl;

        public Integer getEid() {
            return eid;
        }

        public void setEid(Integer eid) {
            this.eid = eid;
        }

        public List<Invitee> getIidl() {
            return iidl;
        }

        public void setIidl(List<Invitee> iidl) {
            this.iidl = iidl;
        }

        public boolean valid(){
            return eid!=null&&iidl!=null&&iidl.size()>0;
        }

        public List<ExamInvite> toInvite(long uid, Integer fid){
            List<ExamInvite> eil=new ArrayList<>();
            for(Invitee v:this.iidl){
                ExamInvite ei=new ExamInvite();
                ei.setUserid(0L);
                ei.setVuserid((long)v.getId());
                ei.setEid(eid);
                ei.setUemail(v.getEmail());
                ei.setIid(UUID.randomUUID().toString());
                ei.setDone("F");
                ei.setDclt(new Date());
                ei.setFirmid(fid);
                ei.setRemarks(Long.toString(uid));
                eil.add(ei);
            }
            return eil;
        }

    }
}
