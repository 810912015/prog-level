package com.pl.admin.controller;

import com.pl.admin.dto.Bound;
import com.pl.admin.service.IArticleService;
import com.pl.data.common.api.CommonResult;
import com.pl.data.model.TArticle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Api(tags = "ArticleController",description = "ArticleController api")
@RequestMapping("/admin/article")
public class ArticleController extends BaseController{

    @ApiOperation(value = "list")
    @RequestMapping(value = "list",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<TArticle>> list(@RequestBody Bound bound) {
        return articleService.list(bound);
    }

    @ApiOperation(value = "save")
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult save(@RequestBody TArticle art) {
        return articleService.save(art);
    }
    @ApiOperation(value = "del")
    @RequestMapping(value = "del",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult del(Long id) {
       return articleService.del(id.intValue());
    }

    @ApiOperation(value = "detail")
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<TArticle> detail(Integer id){
        return articleService.detail(id);
    }
    @Autowired
    private IArticleService articleService;
}
