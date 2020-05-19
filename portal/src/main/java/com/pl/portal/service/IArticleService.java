package com.pl.portal.service;

import com.pl.data.model.TArticle;
import com.pl.portal.dto.Bound;

import java.util.List;

public interface IArticleService {
    List<TArticle> getList(Bound b);
    TArticle getDetail(Long id,String version);
    Long getDefaultId();
}
