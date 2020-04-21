package com.pl.search.controller;

import com.pl.data.common.api.CommonResult;
import com.pl.search.domain.Qdto;
import com.pl.search.service.EsOperator;
import com.pl.search.service.ISearch;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 搜索商品管理Controller
 * Created by macro on 2018/6/19.
 */
@Controller
@Api(tags = "SearchController", description = "搜索")
@RequestMapping("/s")
public class SearchController {
    @ApiOperation(value = "搜索")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Page<Qdto>> search(@RequestParam(required = false) String k,
                                           @RequestParam(required = false, defaultValue = "0") Integer p,
                                           @RequestParam(required = false, defaultValue = "5") Integer s){
        return CommonResult.success(search.search(k,p,s));
    }
    @ApiOperation(value = "分词:拆分搜索语句")
    @RequestMapping(value = "/analyze/{keyword}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<String>> analyze(@PathVariable("keyword") String keyword){
        return CommonResult.success(esOperator.analyze(keyword));
    }
    @Autowired
    private EsOperator esOperator;

    @Autowired
    private ISearch search;
}
