package com.pl.search.controller;


import com.pl.data.common.api.CommonPage;
import com.pl.data.common.api.CommonResult;
import com.pl.data.common.api.store.BookEntity;
import com.pl.search.component.SearchIndex;
import com.pl.search.domain.EsBook;
import com.pl.search.domain.EsBook2;
import com.pl.search.domain.EsProductRelatedInfo;
import com.pl.search.service.EsBookService;
import com.pl.search.service.EsOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 搜索商品管理Controller
 * Created by macro on 2018/6/19.
 */
@Controller
@Api(tags = "SearchController", description = "搜索")
@RequestMapping("/bs")
public class SearchController {
    @Autowired
    private EsBookService bookService;

    @ApiOperation(value = "搜索")
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<BookEntity.BookInfo>> search(@RequestParam(required = false) String keyword,
                                                                @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                                @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsBook> esProductPage = bookService.search(keyword, pageNum, pageSize);
        return null;// CommonResult.success(CommonPage.restPage( EsBook.Converter.toPageInfo(esProductPage)));
    }

    @ApiOperation(value = "搜索")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<CommonPage<EsBook2>> list(@RequestBody EsBookService.KeyCatArgs args){
        Page<EsBook2> r=bookService.search(args);
        return CommonResult.success(CommonPage.restPage(r));
    }

    @ApiOperation(value = "搜索2")
    @RequestMapping(value = "/search2", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<EsBook>> search2(@RequestParam(required = false) String keyword,
                                                                @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                                @RequestParam(required = false, defaultValue = "50") Integer pageSize) {
        Page<EsBook> esProductPage = bookService.searchSimple(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }

    @ApiOperation(value = "搜索3:按字段搜索,关键字格式是 a1:b1,a2:b2,...")
    @RequestMapping(value = "/search3", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<EsBook>> search3(@RequestParam(required = false) String keyword,
                                                    @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                    @RequestParam(required = false, defaultValue = "50") Integer pageSize) {
        Page<EsBook> esProductPage = bookService.byField(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage));
    }
    @ApiOperation(value = "搜索-管理后台用")
    @RequestMapping(value = "/byids", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<EsBook>> byids(String idl) {
        if(StringUtils.isEmpty(idl)) return CommonResult.success(null);
        List<Long> l=new ArrayList<>();
        String[] tid=idl.split(",");
        for(String t:tid){
            try{
                l.add(Long.parseLong(t.trim()));
            }catch (Throwable e){
                //
            }
        }
        if(l.size()==0) return CommonResult.success(null);

        List<EsBook> esProductPage = bookService.byIds(l);
        return CommonResult.success(esProductPage);
    }
    @ApiOperation(value = "搜索热词,参数:0-日,1-周,2-月,3-总")
    @RequestMapping(value = "/hot", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<Set<String>> hot(Integer type) {
        return null;
    }
    @ApiOperation(value = "搜索热词,参数:0-日,1-周,2-月,3-总")
    @RequestMapping(value = "/hot2", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<EsBookService.HotKey>> hot2(Integer type) {
       return null;
    }
    @ApiOperation(value = "搜索书籍,参数:0-日,1-周,2-月,3-总")
    @RequestMapping(value = "/hotbooks", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<EsBook.Scored>> hotBook(Integer type) {
       return null;
    }

    @ApiOperation(value = "根据id推荐书籍")
    @RequestMapping(value = "/recommend/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<BookEntity.BookInfo>> recommend(@PathVariable Long id,
                                                                   @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                                   @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsBook> esProductPage = bookService.recommend(id, pageNum, pageSize);
        return null;// CommonResult.success(CommonPage.restPage(EsBook.Converter.toPageInfo(esProductPage)));
    }

    @ApiOperation(value = "分词:拆分搜索语句")
    @RequestMapping(value = "/analyze/{keyword}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<String>> analyze(@PathVariable("keyword") String keyword){
        return CommonResult.success(esOperator.analyze(keyword));
    }
    @Autowired
    private EsOperator esOperator;

}
