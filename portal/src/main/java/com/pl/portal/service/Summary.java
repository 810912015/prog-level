package com.pl.portal.service;

import com.pl.data.common.api.CommonResult;
import com.pl.data.mapper.QuestionMapper;
import com.pl.data.mapper.TArticleMapper;
import com.pl.data.model.QuestionExample;
import com.pl.data.model.TArticle;
import com.pl.data.model.TArticleExample;
import com.pl.portal.dao.MyQuestionMaper;
import com.pl.portal.dto.Bound;
import com.pl.portal.dto.ThinQuestion;
import com.pl.portal.service.question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = {"myCache2"})
public class Summary implements ISummary {
    @Override
    @Cacheable(key="'default_summary'")
    public CommonResult<Data> summary() {
        Data d=new Data();
        Bound b1=new Bound().normalize();
        b1.setSize(10);
        Item<TArticle> tas=new Item<>();
        Item<ThinQuestion> qas=new Item<>();
        tas.setItems(articleService.getList(b1));
        b1.setSize(50);
        b1.normalize();
        qas.setItems(questionMaper.getAllQuestion(b1.getMaxId(),b1.getMinId(),b1.getSize()));
        tas.setCount((int)tm.countByExample(new TArticleExample()));
        qas.setCount((int)qm.countByExample(new QuestionExample()));
        d.setArticles(tas);
        d.setQuestions(qas);
        return CommonResult.success(d);
    }

    @Autowired
    private IArticleService articleService;
    @Autowired
    private MyQuestionMaper questionMaper;
    @Autowired
    private QuestionMapper qm;
    @Autowired
    private TArticleMapper tm;
}
