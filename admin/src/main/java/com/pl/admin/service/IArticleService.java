package com.pl.admin.service;

import com.pl.admin.dto.Bound;
import com.pl.data.common.api.CommonResult;
import com.pl.data.model.TArticle;

import java.util.List;

public interface IArticleService {
    CommonResult save(TArticle article);
    CommonResult del(Integer id);
    CommonResult<TArticle> detail(Integer id);
    CommonResult<List<TArticle>> list(Bound bound);
}
