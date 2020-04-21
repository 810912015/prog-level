package com.pl.search.controller;

import com.pl.data.common.api.CommonResult;
import com.pl.data.common.api.Result;
import com.pl.search.service.EsOperator;
import com.pl.search.service.ISearchAdmin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索商品管理Controller
 * Created by macro on 2018/6/19.
 */
@Controller
@Api(tags = "SearchAdminController", description = "搜索管理")
@RequestMapping("/sa")
public class SearchAdminController {
    @ApiOperation(value = "导入所有问题")
    @RequestMapping(value = "/import-all", method = RequestMethod.GET)
    @ResponseBody
    public Result importAll(){
        admin.importAll();
        return Result.success();
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
    private ISearchAdmin admin;
}
