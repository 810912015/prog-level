package com.pl.search.controller;

import com.pl.data.common.api.Result;
import com.pl.search.domain.EsBook;
import com.pl.search.service.EsBookService;
import com.pl.search.service.EsOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.lucene.util.fst.Util;
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
    @Autowired
    private EsBookService bookService;

    @ApiOperation(value = "导入所有数据库中书籍到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> importAllList() {
        int count = bookService.importAll();
        return Result.success(count);
    }

    @ApiOperation(value = "导入所有数据库中书籍到ES")
    @RequestMapping(value = "/importAll2", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> importAllList2() {
        int count = bookService.importAll2();
        return Result.success(count);
    }

    @ApiOperation(value = "从redis更新评分数据到db和es")
    @RequestMapping(value = "/updateScore", method = RequestMethod.POST)
    @ResponseBody
    public Result updateScore() {
        bookService.updateScore();
        return Result.success(null);
    }
    @ApiOperation(value = "根据id删除书籍")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> delete(@PathVariable Long id) {
        bookService.delete(id);
        return Result.success(null);
    }

    @ApiOperation(value = "根据id批量删除书籍")
    @RequestMapping(value = "/batch/delete", method = RequestMethod.GET)
    @ResponseBody
    public Result<Object> delete(String ids) {
        List<Long> r=toIds(ids);
        if(r==null||r.isEmpty()) return Result.failed("无可用id");


        return Result.success(null);
    }
    static List<Long> toIds(String s){
        if(StringUtils.isEmpty(s)) return null;
        List<Long> r=new ArrayList<>();
        String[] sa=s.split(",");
        for(String t:sa){
            if(StringUtils.isEmpty(t)) continue;
            try{
                Long i=Long.parseLong(t.trim());
                r.add(i);
            }catch (Exception e){

            }
        }
        return r;
    }
    @ApiOperation(value = "根据id创建或更新书籍")
    @RequestMapping(value = "/create/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Result<EsBook> create(@PathVariable Long id) {
        EsBook esProduct = bookService.create(id);
        if (esProduct != null) {
            return Result.success(esProduct);
        } else {
            return Result.failed("");
        }
    }

    @ApiOperation(value = "根据id创建或更新书籍")
    @RequestMapping(value = "/batch/create", method = RequestMethod.GET)
    @ResponseBody
    public Result<EsBook> batchCreate(String ids) {
        List<Long> il=toIds(ids);
        if(il==null||il.isEmpty()) return Result.failed("无可用id");


        return Result.success(null);
    }

    @ApiOperation(value = "刷新书籍索引")
    @RequestMapping(value = "/refresh/{index}", method = RequestMethod.GET)
    @ResponseBody
    public Result refesh(@PathVariable("index") String index) {
        esOperator.refreshIndex(index);
        return Result.success(null);
    }

    @Autowired
    private EsOperator esOperator;
}
