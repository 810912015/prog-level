package com.pl.admin.controller;


import com.pl.admin.service.ITranslator;
import com.pl.data.common.api.CommonResult;
import com.pl.data.model.TArticle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


@Controller
@Api(tags = "TranslateController",description = "TranslateController api")
@RequestMapping("/admin/translate")
public class TranslateController {
    @Autowired
    private ITranslator translator;

    @ApiOperation(value = "by-link")
    @RequestMapping(value = "by-link",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<TArticle>> translate(@RequestBody ITranslator.Link link) {
        if(link==null) return CommonResult.failed("要翻译的对象不能为空");
        CommonResult cr=link.valid();
        if(!cr.isSuccess()){
            return CommonResult.copy(cr);
        }
        return translator.translate(link);
    }

    @ApiOperation(value = "result")
    @RequestMapping(value = "result",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<TArticle>> query(@RequestBody ITranslator.Link link) {
        return translator.query(link);
    }

    @ApiOperation(value = "clear")
    @RequestMapping(value = "clear",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> clear(@RequestBody ITranslator.Link link) {
        return translator.clearLock(link);
    }

    @ApiOperation(value = "query-lock")
    @RequestMapping(value = "query-lock",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> queryLock(@RequestBody ITranslator.Link link) {
        return translator.queryLock(link);
    }

    @ApiOperation(value = "done")
    @RequestMapping(value = "done",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> done(@RequestBody ITranslator.Result link) {
        try {
            return translator.done(link);
        }catch (Exception e){
            LoggerFactory.getLogger(this.getClass()).error("",e);
           return CommonResult.failed(e.getMessage());
        }
    }

    @ApiOperation(value = "finish")
    @RequestMapping(value = "finish",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<String> finish(@RequestBody ITranslator.Link link) {
        try {
            return translator.finish(link);
        }catch (Exception e){
            LoggerFactory.getLogger(this.getClass()).error("",e);
            return CommonResult.failed(e.getMessage());
        }
    }

    @ApiOperation(value = "scheduled")
    @RequestMapping(value = "scheduled",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<String>> scheduled() {
        try {
            return translator.scheduled();
        }catch (Exception e){
            LoggerFactory.getLogger(this.getClass()).error("",e);
            return CommonResult.failed(e.getMessage());
        }
    }
}
