package com.pl.admin.controller;


import com.google.common.base.Throwables;
import com.pl.admin.dto.Pager;
import com.pl.admin.dto.Result;
import com.pl.admin.service.CsvReader;
import com.pl.admin.service.ObjectToExample;
import com.pl.data.mapper.InviteeMapper;
import com.pl.data.model.Invitee;
import com.pl.data.model.InviteeExample;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/invitee")
@Api(tags = "InviteeController",description = "InviteeController api")
public class InviteeController extends BaseController {
    @RequestMapping(value = "create")
    @ResponseBody
    public Result<Invitee> createOrUpdate(@RequestBody Invitee i) {
        try {
            if(hasEmail(i.getEmail())){
                return new Result<>(false,"邮箱已存在",i);
            }
            i.setDclt(new Date());
            int s = curUser().getFirmid();
            i.setFirmid(s);
            if(i.getId()>0){
                im.updateByPrimaryKey(i);
            }else {
                im.insert(i);
            }
            return new Result<>(true, "", i);
        } catch (Exception e) {
            logger.error(Throwables.getStackTraceAsString(e));
            return new Result<>(false, "请重新登录");
        }
    }

    private static final String INV_TPL = "姓名,邮箱,分类1,分类2,分类3,分类4,分类5,备注\n" +
            "张三,zs@abc.com,分类1,分类2,分类3,分类4,分类5,无备注\n";

    @RequestMapping(value = "tpl", method = RequestMethod.GET)
    public void download(HttpServletResponse response) {
        response.setContentType("application/ms-excel");
        response.setCharacterEncoding("gb2312");
        response.setHeader("content-disposition", "attachment;filename="
                + "tpl.csv");
        ServletOutputStream writer = null;
        try {
            writer = response.getOutputStream();
            writer.write(INV_TPL.getBytes());
            writer.flush();
            writer.close();
        } catch (Exception e) {
            logger.error("download", e);
        }
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> upload(@RequestPart(value = "file", required = false) byte[] groups,
                                 @RequestPart(value = "path", required = false) byte[] ps) {
        try {
            long uid = curUser().getId();
            if (uid == 0) {
                return new Result<>(false, "请重新登录", "");
            }
            String s = new String(groups);
            CsvReader.Readers.InviteeReader reader=new CsvReader.Readers.InviteeReader(s);
            List<Invitee> r=reader.toValues();
            int c=0;
            for(Invitee i:r){
                if(hasEmail(i.getEmail())){
                    continue;
                }
                im.insert(i);
                c++;
            }
            if(c==0){
                return new Result<>(false,"邮箱重复,无可保存的记录");
            }
            return new Result<>(true, "保存成功", String.format("一共%d条记录",c));
        } catch (Exception e) {
            logger.error(Throwables.getStackTraceAsString(e));
            return new Result<>(false, "保存失败", null);
        }
    }
    private boolean hasEmail(String email){
        if(StringUtils.isEmpty(email)){
            return true;
        }
        InviteeExample ie=new InviteeExample();
        ie.createCriteria().andEmailEqualTo(email);
        return im.countByExample(ie)>0;
    }


    @RequestMapping(value = "list")
    @ResponseBody
    public Pager.Paged<List<Invitee>> get(@RequestBody Pager.PagedQuery<Invitee> p) {
        try {
            InviteeExample ie = new InviteeExample();
            int fid = curUser().getFirmid();
            ie.createCriteria().andFirmidEqualTo(fid);
            ObjectToExample.toLike(p.getQuery(), ie.createCriteria(),
                    new String[]{"Name", "Email", "Cat1", "Cat2", "Cat3", "Cat4", "Cat5", "Remarks"});
//            ie.setLimit(p.getPager().getSize());
//            ie.setOffset(p.getPager().start());
            List<Invitee> r = im.selectByExample(ie);
            long t = im.countByExample(ie);
            return new Pager.Paged<>(r, p.getPager(), (int) t);
        }catch (Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            return new Pager.Paged<>(new ArrayList<>(),new Pager.Param(),0);
        }
    }

    @RequestMapping(value = "del/{id}")
    @ResponseBody
    public int delById(@PathVariable Integer id) {
        return im.deleteByPrimaryKey(id);
    }

    @RequestMapping(value="get/{id}")
    @ResponseBody
    public Invitee getById(@PathVariable Integer id){
        return im.selectByPrimaryKey(id);
    }

    private InviteeMapper im;

    @Autowired
    public void setIm(InviteeMapper im) {
        this.im = im;
    }

}
