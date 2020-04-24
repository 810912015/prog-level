package com.pl.portal.service;

import com.pl.data.mapper.TArticleMapper;
import com.pl.data.model.TArticle;
import com.pl.data.model.TArticleExample;
import com.pl.portal.dto.Bound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService implements IArticleService {
    @Override
    public List<TArticle> getList(Bound b) {
        b.normalize();
        TArticleExample ae=new TArticleExample();
        ae.handleQueryArgs(b);
        return articleMapper.selectByExample(ae);
    }

    @Override
    public TArticle getDetail(Long id) {
        return articleMapper.selectByPrimaryKey(id.intValue());
    }

    @Autowired
    private TArticleMapper articleMapper;
}
