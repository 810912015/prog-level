package com.pl.portal.controller;

import com.google.common.base.Throwables;
import com.pl.portal.dao.MyQuestionMaper;
import com.pl.portal.dto.PassParam;
import com.pl.portal.dto.Result;
import com.pl.data.mapper.PassMapper;
import com.pl.data.mapper.QuestionMapper;
import com.pl.data.model.Pass;
import com.pl.data.model.PassExample;
import com.pl.data.model.Question;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/pass")
@Api(tags = "PassController",description = "PassController api")
public class PassController extends BaseController{
    @RequestMapping(value="/judge",method = RequestMethod.POST)
    @ResponseBody
    public Result<List<String>> getByPrm(@RequestBody PassParam p){
        try {
            return new Result<>(true,"",qm.getPass(p));
        }catch (Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            return new Result<>(false,e.getMessage());
        }
    }

    @RequestMapping(value="/source/{id}",method = RequestMethod.GET)
    @ResponseBody
    public PassParam.PassDetail getById(@PathVariable("id") String id){
        PassExample e=new PassExample();
        e.createCriteria().andGroupidEqualTo(id);
        List<Pass> pr= prm.selectByExampleWithBLOBs(e);
        if(pr==null||pr.isEmpty()){
            return new PassParam.PassDetail(null,null);
        }
        Question q=qm2.selectByPrimaryKey(pr.get(0).getQid());
        return new PassParam.PassDetail(q,pr);
    }

    @RequestMapping(value="/score/update",method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> updateScore(@RequestBody PassParam.PassScore score){
        int r=qm.updatePassScore(score);
        return new Result<>(true,"",r);
    }
    private MyQuestionMaper qm;
    private PassMapper prm;
    private QuestionMapper qm2;

    @Autowired
    public void setQm(MyQuestionMaper qm) {
        this.qm = qm;
    }

    @Autowired
    public void setPrm(PassMapper prm) {
        this.prm = prm;
    }


    @Autowired
    public void setQm2(QuestionMapper qm2) {
        this.qm2 = qm2;
    }
}
