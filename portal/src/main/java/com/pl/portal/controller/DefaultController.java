package com.pl.portal.controller;

import com.pl.data.common.api.CommonResult;
import com.pl.data.model.TArticle;
import com.pl.portal.service.ISummary;
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
@Api(tags = "DefaultController",description = "DefaultController api")
@RequestMapping("/p/default")
public class DefaultController {

    @ApiOperation(value = "summary")
    @RequestMapping(value = "summary",method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<ISummary.Data> summary() {
        return summary.summary();
    }

    @Autowired
    private ISummary summary;
}
