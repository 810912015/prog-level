package com.pl.portal.service;

import com.pl.data.mapper.TArticleMapper;
import com.pl.data.model.TArticle;
import com.pl.portal.dao.ArticleDao;
import com.riversoft.weixin.common.message.Article;
import com.riversoft.weixin.common.message.News;
import com.riversoft.weixin.common.message.XmlMessageHeader;
import com.riversoft.weixin.common.message.xml.NewsXmlMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@CacheConfig(cacheNames = {"myCache1"})
public class WxArticleService implements IWxArticleService {
    @Override
    @Cacheable(key="'wx_article_'")
    public News getArticles() {
        News news=new News();
        List<TArticle> idl=articleDao.getLatest5();
        for(TArticle a:idl){
            Article r=new Article();
            r.setTitle(a.getcName());
            r.setDescription(a.getAuth());
            r.setUrl("http://progprac.com/detail.html#/"+a.getId());
            r.setPicUrl("http://wx.qlogo.cn/mmopen/jJSbu4Te5ib9ibK9JaC1ZKib5wl0fWWR6lia6JjfLeLNmdHf0THRzDHjxfIEkGG5nNyVydHGoLaEkmNrbc3M1htZAXqTRtbZN02x/64");
            news.add(r);
        }

        return news;
    }

    @Override
    public NewsXmlMessage getMsg(XmlMessageHeader xmlRequest) {
        NewsXmlMessage r=new NewsXmlMessage();
        r.setNews(getArticles());
        r.setFromUser(xmlRequest.getToUser());
        r.setToUser(xmlRequest.getFromUser());
        r.setCreateTime(new Date());

        return r;
    }

    @Autowired
    private ArticleDao articleDao;
}
