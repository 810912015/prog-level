package com.pl.portal.service;

import com.riversoft.weixin.common.message.News;
import com.riversoft.weixin.common.message.XmlMessageHeader;
import com.riversoft.weixin.common.message.xml.NewsXmlMessage;

import java.util.Date;

public interface IWxArticleService {
    News getArticles();
    NewsXmlMessage getMsg(XmlMessageHeader xmlRequest);
}
