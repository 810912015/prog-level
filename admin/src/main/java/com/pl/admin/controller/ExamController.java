package com.pl.admin.controller;

import com.google.common.base.Throwables;

import com.pl.admin.dao.MyQuestionMaper;
import com.pl.admin.dao.QuestionItemMapper;
import com.pl.admin.dao.ScoreMapper;
import com.pl.admin.dto.*;
import com.pl.admin.service.runner.Engine;
import com.pl.admin.service.Notifier;
import com.pl.admin.service.question.QuestionService;
import com.pl.data.mapper.*;
import com.pl.data.model.*;
import com.pl.data.model.Pass;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.stream.Collectors;

@Controller
@Api(tags = "ExamController",description = "ExamController api")
public class ExamController extends BaseController {

    @RequestMapping(value = "/exam/pass",method = RequestMethod.POST)
    @ResponseBody
    public Engine.ExecuteResult pass(@RequestBody Pass p) {
        ExamInviteExample ee = new ExamInviteExample();
        ee.createCriteria().andIidEqualTo(p.getEiid());
        List<ExamInvite> l = eim.selectByExample(ee);
        if (l != null && l.size() > 0) {
            p.setEid(l.get(0).getEid());
        }
        return qs.pass(p);
    }


    @RequestMapping(value = "/play",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> play(@RequestBody PlayDto pd) {
        return new Result<>(true, "", qs.play(pd));
    }

    @RequestMapping(value = "/exam/done/{gid}",method = RequestMethod.GET)
    @ResponseBody
    public Result passDone(@PathVariable @Nullable String gid) {
        try {
            return new Result(qim.done(gid) > 0, "保存成功");
        } catch (Exception e) {
            return new Result(false, e.getMessage());
        }
    }

    @RequestMapping(value = "/question/create",method = RequestMethod.POST)
    @ResponseBody
    public QuestionDto createQuestion(@RequestBody QuestionDto q) {
        try {
            q.setUserId(curUser().getId());
            return qs.addOrUpdate(q);
        } catch (Exception e) {
            logger.error(e.getMessage() + Throwables.getStackTraceAsString(e));
            return new QuestionDto();
        }
    }

    @RequestMapping(value = "/question/source",method = RequestMethod.POST)
    @ResponseBody
    public Result<QsDto> getSource(@RequestBody QsDto.Request q) {
        QuestionSourceExample qse = new QuestionSourceExample();
        qse.createCriteria().andQidEqualTo(q.getQid()).andLangEqualTo(q.getLang());
        List<QuestionSource> l = qsm.selectByExampleWithBLOBs(qse);
        if (l == null || l.isEmpty()) {
            return new Result<>(false, "");
        }
        return new Result<>(true, "", new QsDto(l.get(0)));
    }

    @RequestMapping(value = "/question/all",method = RequestMethod.POST)
    @ResponseBody
    public List<ThinQuestion> getAll(@RequestBody Bound b) {
        try {
            b.normalize();
            List<ThinQuestion> r = mqm.getAllQuestion(b.getMaxId(), b.getMinId(), b.getSize());
            return r;
        } catch (Exception e) {
            logger.error(e.getMessage() + Throwables.getStackTraceAsString(e));
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/question/mine",method = RequestMethod.POST)
    @ResponseBody
    public List<ThinQuestion> getMine(@RequestBody OwnBound b) {
        try {
            b.normalize();
            List<ThinQuestion> r = mqm.getMyQuestion(curUser().getId(), b.getMaxId(), b.getMinId(), b.getSize());
            return r;
        } catch (Exception e) {
            logger.error(e.getMessage() + Throwables.getStackTraceAsString(e));
            return new ArrayList<>();
        }
    }

    @RequestMapping(value = "/question/get/{id}",method = RequestMethod.POST)
    @ResponseBody
    public PassDto getQuestionById(@PathVariable int id) {
        Question q = qm.selectByPrimaryKey(id);

        return new PassDto(q, getSourceById(id));
    }

    @RequestMapping(value = "/question/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public boolean deleteById(@PathVariable int id) {
        boolean qr = qm.deleteByPrimaryKey(id) > 0;
        ValidationExample ve = new ValidationExample();
        ve.createCriteria().andQidEqualTo(id);
        int vr = vm.deleteByExample(ve);
        return qr;
    }


    @RequestMapping(value = "/question/withvali/{id}",method = RequestMethod.GET)
    @ResponseBody
    public QuestionDto getQuestionByIdWithVali(@PathVariable int id) {
        List<QuestionItem> l = qim.getById(id);
        List<QsDto> rl = getSourceById(id);
        QuestionDto d = new QuestionDto(l);
        d.setSourceList(rl);
        return d;
    }

    private List<QsDto> getSourceById(@PathVariable long id) {
        QuestionSourceExample qse = new QuestionSourceExample();
        qse.createCriteria().andQidEqualTo(id);
        List<QuestionSource> qsl = qsm.selectByExampleWithBLOBs(qse);
        List<QsDto> rl = new ArrayList<>();
        for (QuestionSource t : qsl) {
            rl.add(new QsDto(t));
        }
        return rl;
    }

    @RequestMapping(value = "/exam/invite",method = RequestMethod.POST)
    @ResponseBody
    public Result invite(@RequestBody ExamInvite invite) {
        try {
            invite.setDclt(new Date());
            invite.setIid(UUID.randomUUID().toString());
            eim.insert(invite);
            notifier.sendInvite(invite.getUemail(), invite.getIid());
            return new Result(true, "邀请成功");
        } catch (Exception e) {
            logger.error(e.getMessage() + Throwables.getStackTraceAsString(e));
            return new Result(e.getMessage());
        }
    }

    @RequestMapping(value = "/getbyeid/{eid}",method = RequestMethod.GET)
    @ResponseBody
    public List<Integer> getQidByEid(@PathVariable Integer eid) {
        ExamQuestionExample ee = new ExamQuestionExample();
        ee.createCriteria().andEidEqualTo(eid);
        List<Integer> r = eqm.selectByExample(ee).stream().map(a -> a.getQid()).collect(Collectors.toList());
        return r;
    }


    @RequestMapping(value = "/exam/delete/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Result deleteExam(@PathVariable int id) {
        em.deleteByPrimaryKey(id);
        ExamQuestionExample eqe = new ExamQuestionExample();
        eqe.createCriteria().andEidEqualTo(id);
        eqm.deleteByExample(eqe);
        ExamInviteExample eie = new ExamInviteExample();
        eie.createCriteria().andEidEqualTo(id);
        eim.deleteByExample(eie);
        return new Result(true, "删除成功");
    }


    @RequestMapping(value = "/exam/all",method = RequestMethod.POST)
    @ResponseBody
    public List<ExamDto2> allExam(@RequestBody Bound b) {
        b.normalize();
        return mqm.getAllExam(b.getMaxId(), b.getMinId(), b.getSize());
    }

    @RequestMapping(value = "/exam/mine",method = RequestMethod.POST)
    @ResponseBody
    public List<ExamDto2> myExam(@RequestBody Bound b) {
        b.normalize();
        return mqm.getMyExam(curUser().getId(), b.getMaxId(), b.getMinId(), b.getSize());
    }

    @RequestMapping(value = "/exam/get/{id}",method = RequestMethod.GET)
    @ResponseBody
    public ExamDto getExam(@PathVariable int id) {
        Exam e = em.selectByPrimaryKey(id);
        ExamQuestionExample eqe = new ExamQuestionExample();
        eqe.createCriteria().andEidEqualTo(id);
        List<ExamQuestion> r = eqm.selectByExample(eqe);
        return new ExamDto(e, r);
    }

    @RequestMapping(value = "/exam/create",method = RequestMethod.POST)
    @ResponseBody
    public ExamDto createExam(@RequestBody ExamDto ed) {
        Exam e = ed.toExam();
        e.setUserid(curUser().getId());
        em.insert(e);
        ed.setId(e.getId());
        for (int i = 0; i < ed.getCheck().size(); i++) {
            ExamQuestion r = ed.getCheck().get(i).toRelation(e.getId());
            eqm.insert(r);
        }
        return ed;
    }

    @RequestMapping(value = "/score",method = RequestMethod.GET)
    @ResponseBody
    public List<ScoreDto> getScore() {
        UUser u = curUser();
        return sm.getScore(u.getId().intValue());
    }


    private QuestionMapper qm;
    private ValidationMapper vm;
    private QuestionItemMapper qim;
    private QuestionService qs;
    private ExamMapper em;
    private ExamQuestionMapper eqm;
    private ScoreMapper sm;
    private ExamInviteMapper eim;
    private Notifier notifier;
    private MyQuestionMaper mqm;
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
    public void setQim(QuestionItemMapper qim) {
        this.qim = qim;
    }

    @Autowired
    public void setQs(QuestionService qs) {
        this.qs = qs;
    }

    @Autowired
    public void setEm(ExamMapper em) {
        this.em = em;
    }

    @Autowired
    public void setEqm(ExamQuestionMapper eqm) {
        this.eqm = eqm;
    }

    @Autowired
    public void setSm(ScoreMapper sm) {
        this.sm = sm;
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
    public void setMqm(MyQuestionMaper mqm) {
        this.mqm = mqm;
    }

    @Autowired
    public void setQsm(QuestionSourceMapper qsm) {
        this.qsm = qsm;
    }
}
