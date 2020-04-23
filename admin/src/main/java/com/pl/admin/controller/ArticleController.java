package com.pl.admin.controller;

import com.pl.admin.dto.Bound;
import com.pl.admin.dto.RegisterDto;
import com.pl.admin.dto.Result;
import com.pl.data.common.api.CommonResult;
import com.pl.data.mapper.TArticleMapper;
import com.pl.data.model.TArticle;
import com.pl.data.model.TArticleExample;
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
        TArticleExample ae=new TArticleExample();
        if(bound==null){
            bound=new Bound();
        }
        bound.normalize();
        ae.handleQueryArgs(bound);
        return CommonResult.success(articleMapper.selectByExample(ae));
    }
    @ApiOperation(value = "detail")
    @RequestMapping(value = "detail",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<TArticle> detail(Integer id){
        return CommonResult.success(articleMapper.selectByPrimaryKey(id));
    }
    @Autowired
    private TArticleMapper articleMapper;
}
