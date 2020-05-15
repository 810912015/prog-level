package com.pl.portal.service.question;

import com.pl.portal.dto.Bound;
import com.pl.portal.dto.Result;
import com.pl.data.mapper.QuestionMapper;
import com.pl.data.model.Question;
import com.pl.data.model.QuestionExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@CacheConfig(cacheNames = {"myCache1"})
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
    @Cacheable(key="'q_tags'")
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
        qe.handleQueryArgs(args.getBound()).andLangEqualTo(args.getMeta().getName());
        return Result.success(questionMapper.selectByExample(qe));
    }

    static List<Index<Question>> recommends=null;
    synchronized void initRecommends(){
        if(recommends!=null) return;
        String[] ra=new String[]{"最热","提交最多","搜索最多"};

        recommends=new ArrayList<>();
        int count=0;
        for(String s:ra){
            Index<Question> i=new Index<>();
            QuestionExample qe=new QuestionExample();
            qe.setLimit(10);
            qe.setOffset(500+(count++*71));
            List<Question> ql=questionMapper.selectByExample(qe);
            i.setList(ql);
            i.setName(s);
            i.setDesc(Arrays.asList(s+"的问题"));
            recommends.add(i);
        }
    }
    @Override
    @Cacheable(key="'q_recommend'")
    public Result<List<Index<Question>>> recommend() {
        if(recommends==null) initRecommends();
        return Result.success(recommends);
    }

    @Autowired
    private QuestionMapper questionMapper;
}
