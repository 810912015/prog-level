package com.pl.portal.controller;


import com.google.common.base.Throwables;
import com.pivot.json.AntlrJsonBuilder;
import com.pivot.json.JsonBuilder;
import com.pl.portal.dao.AuthMapper;
import com.pl.portal.dto.*;
import com.pl.portal.service.AuthService;
import com.pl.portal.service.Notifier;
import com.pl.data.mapper.UUserMapper;
import com.pl.data.model.UUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URI;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@Api(tags = "AuthController",description = "AuthController api")
public class AuthController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @ApiOperation(value = "login")
    @RequestMapping(value = "/tools/jsonbuild",method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestBody Prm prm) {
        try {
            JsonBuilder jb = AntlrJsonBuilder.build(prm.json);
            String src = jb.toSrc(prm.name);
            return Result.success(src);
        }catch (Throwable e){
            logger.error("",e);
            return Result.fail(e.getMessage());
        }
    }

    public static class Prm{
        private String json;
        private String name;

        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @ApiOperation(value = "login2")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result<AuthDto> login(@RequestBody LoginDto ld, HttpServletResponse response, HttpServletRequest request) {
        return as.login(ld);
    }
    @ApiOperation(value = "register")
    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public Result<AuthDto> register(@RequestBody RegisterDto ld) {
        if(!ld.isSimple()) {
            Result<AuthDto> x = validate(ld);
            if (x != null) return x;
        }
        Result<AuthDto> r = as.register(ld);

        return r;
    }

    private Result<AuthDto> validate(@RequestBody RegisterDto ld) {
        boolean r=as.isConfirmMatch(ld.getEmail(),ld.getConfirm());
        if (!r) {
            return new Result.Complex<>(false, "邮箱激活码错误,请重新输入",
                    new AuthDto(false, "", ld.getName())).addMsg("confirm","邮箱激活码错误,请重新输入");
        }
        return null;
    }


    public static class ConfirmDto {

        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
    @ApiOperation(value = "confirm")
    @RequestMapping(value = "confirm",method = RequestMethod.POST)
    @ResponseBody
    public Result confirm(@RequestBody ConfirmDto cd) {
        return as.sendRegister(cd.email);
    }

    @ApiOperation(value = "reset")
    @RequestMapping(value = "reset",method = RequestMethod.POST)
    @ResponseBody
    public Result<AuthDto> reset(@RequestBody RegisterDto ld) {
        Result<AuthDto> x = validate(ld);
        if (x != null) return x;
        return as.reset(ld);
    }
    @ApiOperation(value = "logout")
    @RequestMapping(value = "logout",method = RequestMethod.POST)
    @ResponseBody
    public Result<AuthDto> logout(@RequestBody AuthDto ld) {
//        SecurityUtils.getSubject().logout();
//        return as.logout(ld);
        return null;
    }

//    @Resource
//    private Producer captchaProducer;

//    @RequestMapping("captcha/{ts}")
//    public void getCaptchaCode(@PathVariable Long ts, HttpServletResponse response) throws IOException {
//        response.setDateHeader("Expires", 0);
//        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
//        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
//        response.setHeader("Pragma", "no-cache");
//        response.setContentType("image/jpeg");
//
//        //生成验证码文本
//        String capText = captchaProducer.createText();
//        expirtableIntoSession(AuthKeys.KAPTCHA, capText, 5);
//        //利用生成的字符串构建图片
//        BufferedImage bi = captchaProducer.createImage(capText);
//        ServletOutputStream out = response.getOutputStream();
//        ImageIO.write(bi, "jpg", out);
//        try {
//            out.flush();
//        } finally {
//            out.close();
//        }
//    }

    public static final int K32 = 8 * 1024 * 32;
    public static final String USER_RESOURCE="/user-resource/";

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Result<String> upload(@RequestPart(value = "file", required = false) byte[] groups,
                                 @RequestPart(value = "path", required = false) byte[] ps) {
        try {
            if (groups.length > K32) {
                return new Result<>(false, "大小不能超过32K", null);
            }
            long uid = curUser().getId();
            if (uid == 0) {
                return new Result<>(false, "请重新登录", "");
            }
            String name = UUID.randomUUID().toString() + new String(ps);
            ServletContext sc=request.getServletContext();
            String s=sc.getRealPath("/");
            String p=s.substring(0,s.lastIndexOf("ROOT"));
            String path = p+USER_RESOURCE;
            File f = new File(path, name);
            if (!f.getParentFile().exists()) {
                f.getParentFile().mkdirs();
            }
            OutputStream os = new BufferedOutputStream(new FileOutputStream(f));
            os.write(groups);
            os.flush();

            return new Result<>(true, "", String.format("%s%s",USER_RESOURCE, name));
        } catch (Exception e) {
            logger.error(Throwables.getStackTraceAsString(e));
            return new Result<>(false, "保存失败", null);
        }
    }
    @ApiOperation(value = "profile")
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    @ResponseBody
    public Result<ProfileDto> getProfile() {
        try {
            long uid = curUser().getId();
            UUser u = um.selectByPrimaryKey(uid);
            return new Result<>(true, "", new ProfileDto(u));
        } catch (Exception e) {
            logger.error(Throwables.getStackTraceAsString(e));
            return new Result<>(false, "请重新登录", new ProfileDto());
        }
    }
    @ApiOperation(value = "profile-set")
    @RequestMapping(value = "/profile-set", method = RequestMethod.POST)
    @ResponseBody
    public Result setProfile(@RequestBody ProfileDto d) {
        long uid = curUser().getId();
        if (uid == 0) {
            return new Result(false, "请重新登录");
        }
        int i = am.updateProfile(d, uid);
        return new Result(i > 0, i > 0 ? "成功" : "失败");
    }

    private AuthService as;
    private Notifier notifier;
    private UUserMapper um;
    private AuthMapper am;

    @Autowired
    public void setAm(AuthMapper am) {
        this.am = am;
    }

    @Autowired
    public void setUm(UUserMapper um) {
        this.um = um;
    }

    @Autowired
    public void setNotifier(Notifier notifier) {
        this.notifier = notifier;
    }

    @Autowired
    public void setAs(AuthService as) {
        this.as = as;
    }
}
