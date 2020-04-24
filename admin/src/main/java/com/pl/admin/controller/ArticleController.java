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
import org.springframework.util.StringUtils;
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
        bound.setSize(100);
        ae.handleQueryArgs(bound);
        return CommonResult.success(articleMapper.selectByExample(ae));
    }

    @ApiOperation(value = "save")
    @RequestMapping(value = "save",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult save(@RequestBody TArticle art) {
        if(art==null||art.getId()==null) return CommonResult.failed("对象和id都不能为空");
        //fill cname using first of ctext
        if(!StringUtils.isEmpty(art.getcText())) {
            TArticle oa = articleMapper.selectByPrimaryKey(art.getId());
            if (StringUtils.isEmpty(oa.getcName())) {
                String cn=art.getcText().split("\n")[0];
                art.setcName(cn);
            }
            art.setcHtml(toHtml(art.getcText()));
        }

        return CommonResult.success(articleMapper.updateByPrimaryKeySelective(art));
    }
    public static String toHtml(String txt){
        StringBuilder sb=new StringBuilder();
        sb.append("<div>");
        String[] sa=txt.split("\n");
        boolean code=false;
        for(String t:sa){
            boolean isCode=t.contains("<code>")||t.contains("</code")
                    ||t.contains("<pre>")||t.contains("</pre>");

            if(isCode){
                code=!code;
            }
            if(code){
                sb.append(t);
            }else{
                sb.append("<p>");
                sb.append(t);
                sb.append("</p>");
            }
        }
        sb.append("</div>");
        return sb.toString();
    }



    @ApiOperation(value = "del")
    @RequestMapping(value = "del",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult list(Long id) {
        if(id==null) return CommonResult.failed("id不能为空");
        return CommonResult.success(articleMapper.deleteByPrimaryKey(id.intValue()));
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
