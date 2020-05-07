package com.pl.portal.controller;

import com.pl.data.common.api.CommonResult;
import com.pl.portal.dto.Bound;
import com.pl.portal.service.IArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(tags = "ArticleController",description = "ArticleController api")
@RequestMapping("/p/article")
public class ArticleController {
    @ApiOperation(value = "list")
    @RequestMapping(value = "/list",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult list(@RequestBody Bound bound) {
        return CommonResult.success(articleService.getList(bound));
    }

    @ApiOperation(value = "detail")
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult detail(Long id,String version) {
        if(StringUtils.isEmpty(version)){
            version="c";
        }
        return CommonResult.success(articleService.getDetail(id,version));
    }

    @Autowired
    private IArticleService articleService;
}
