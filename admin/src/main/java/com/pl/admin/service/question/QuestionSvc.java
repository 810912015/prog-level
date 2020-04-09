package com.pl.admin.service.question;

import com.pl.admin.dto.Bound;
import com.pl.admin.dto.Result;
import com.pl.data.mapper.QuestionMapper;
import com.pl.data.model.Question;
import com.pl.data.model.QuestionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QuestionSvc implements IQuestionSvc {
    static List<Meta> METAS= null;
    synchronized void initMeta() {
        if(METAS!=null) return;
        METAS=new ArrayList<>(3);
        Map<String,String> dm=new HashMap<>();
        dm.put("简单","适用于入门");
        dm.put("中等","难度适中，可用于练习");
        dm.put("困难","复杂的编程习题");
        for(String s:dm.keySet()){
            Meta m=new Meta();
            m.setName(s);
            m.setType("level");
            m.setDesc(Arrays.asList(dm.get(s)));
            m.setCount(0);
            QuestionExample qe=new QuestionExample();
            qe.createCriteria().andLangEqualTo(s);
            Long c=questionMapper.countByExample(qe);
            m.setCount(c.intValue());
            METAS.add(m);
        }
    }

    @Override
    public Result<List<Meta>> allTags() {
        if(METAS==null) initMeta();
        return Result.success(METAS);
    }

    @Override
    public Result<List<Question>> byTag(Args args) {
        QuestionExample qe=new QuestionExample();
        if(args.getBound()==null){
            args.setBound(new Bound());
        }
        args.getBound().normalize();
        qe.handleQueryArgs(args.getBound());
        qe.createCriteria().andLangEqualTo(args.getMeta().getName());
        return Result.success(questionMapper.selectByExample(qe));
    }

    @Autowired
    private QuestionMapper questionMapper;
}
