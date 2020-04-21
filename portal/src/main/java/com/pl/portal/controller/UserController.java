package com.pl.portal.controller;

import com.google.common.base.Throwables;
import com.pl.portal.dao.MyQuestionMaper;
import com.pl.portal.dto.Result;
import com.pl.portal.dto.UserDto;
import com.pl.data.mapper.UUserMapper;
import com.pl.data.model.UUser;
import com.pl.data.model.UUserExample;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.util.*;

@Controller
@RequestMapping(value = "/user")
@Api(tags = "UserController",description = "UserController api")
public class UserController extends BaseController {
    @RequestMapping(value = "/all",method = RequestMethod.POST)
    @ResponseBody
    public List<UUser> all(){
        UUserExample ue=new UUserExample();
        ue.setOrderByClause("id desc");
        List<UUser> r= um.selectByExample(ue);
        return r;
    }

    @RequestMapping(value="/create",method = RequestMethod.POST)
    @ResponseBody
    public Result<UUser> create(@RequestBody UserDto ud){
        try {
            UUser u = ud.toUser();
            if(u.getId()==null||u.getId()<=0) {
                um.insert(u);
            }else{
                um.updateByPrimaryKey(u);
            }
            return new Result<>(true, "", u);
        }catch (Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            return new Result<>(false,e.getMessage());
        }
    }

    @RequestMapping(value="/get/{id}",method = RequestMethod.GET)
    @ResponseBody
    public UUser getById(@PathVariable("id") Long id){
        return um.selectByPrimaryKey(id);
    }

    @RequestMapping(value="/del/{id}",method = RequestMethod.POST)
    @ResponseBody
    public int delByid(@PathVariable("id") Long id){
        return um.deleteByPrimaryKey(id);
    }

    @RequestMapping(value="/updateFirm",method = RequestMethod.POST)
    @ResponseBody
    public Result<String> updateFirm(@RequestBody Map<String,Object> map){
        int r=mqm.updateUserFirm(map);
        return new Result<>(true,"更新成功");
    }


    @ResponseBody
    @RequestMapping(value="/all-url",method = RequestMethod.GET)
    public Result<Map<String,String>> getUrlMapping(HttpServletRequest request) {
        Map<String,String> result = new HashMap<String,String>();
        WebApplicationContext wc = (WebApplicationContext) request.getAttribute(DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        RequestMappingHandlerMapping bean = wc.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = bean.getHandlerMethods();
        for (RequestMappingInfo rmi : handlerMethods.keySet()) {
            PatternsRequestCondition pc = rmi.getPatternsCondition();
            Set<String> pSet = pc.getPatterns();
            HandlerMethod hm=handlerMethods.get(rmi);
            Annotation[] aa= hm.getMethod().getDeclaredAnnotations();
            boolean ajax=false;
            for(Annotation a :aa){
                if(a.annotationType()==ResponseBody.class){
                    ajax=true;
                    break;
                }
            }

            String k=pSet.toArray()[0].toString();
            result.put(k,ajax?"ajax":"");
        }
        return new Result<>(true,""+result.size(),result);
    }



    private UUserMapper um;
    private MyQuestionMaper mqm;

    @Autowired
    public void setUm(UUserMapper um) {
        this.um = um;
    }

    @Autowired
    public void setMqm(MyQuestionMaper mqm) {
        this.mqm = mqm;
    }
}
