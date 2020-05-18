package com.pl.portal.service;

import com.pl.data.mapper.TArticleMapper;
import com.pl.data.model.TArticle;
import com.pl.data.model.TArticleExample;
import com.pl.portal.dao.ArticleDao;
import com.pl.portal.dto.Bound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@CacheConfig(cacheNames = {"myCache1"})
public class ArticleService implements IArticleService {
    @Override
    @Cacheable(key="'list_'+#b.toString()")
    public List<TArticle> getList(Bound b) {
        b.normalize();
        TArticleExample ae=new TArticleExample();
        ae.handleQueryArgs(b).andUseLevelGreaterThan(0);
        return articleMapper.selectByExample(ae);
    }

    @Override
    @Cacheable(key="'detail_'+#id.intValue()+'_'+#version")
    public TArticle getDetail(Long id,String version) {
        if(version.equals("e")) {
            return articleDao.getEnById(id.intValue());
        }else if(version.equals("m")){
            return articleDao.getMixById(id.intValue());
        }else{
            return articleDao.getChById(id.intValue());
        }
    }

    @Autowired
    private TArticleMapper articleMapper;

    @Autowired
    private ArticleDao articleDao;
}
