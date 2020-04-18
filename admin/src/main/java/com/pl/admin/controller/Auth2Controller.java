package com.pl.admin.controller;




import com.google.common.base.Throwables;
import com.pivot.json.AntlrJsonBuilder;
import com.pivot.json.JsonBuilder;
import com.pl.admin.dao.AuthMapper;
import com.pl.admin.dto.*;
import com.pl.admin.service.AuthService;
import com.pl.admin.service.Notifier;
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
@Api(tags = "Auth2Controller",description = "Auth2Controller api")
@RequestMapping("/auth")
public class Auth2Controller extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(AuthController.class);

    @ApiOperation(value = "login")
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
        Expirtable ke = expirtableFromSession(AuthKeys.KAPTCHA);
        Expirtable ce = expirtableFromSession(AuthKeys.CONFIRM);
        if (ke.isExpirt() || !ke.getData().equals(ld.getKapcha())) {
            return new Result<>(false, "验证码错误,请点击图片刷新",
                    new AuthDto(false, "", ld.getName()));
        }
        if (ce.isExpirt() || !ce.getData().equals(ld.getConfirm())) {
            return new Result<>(false, "邮箱激活码错误,请重新输入",
                    new AuthDto(false, "", ld.getName()));
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
        try {
            String s = UUID.randomUUID().toString().substring(0, 5);

            Result r = notifier.sendRegister(cd.email, "", s);
            if (r.isSuccess()) {
                expirtableIntoSession(AuthKeys.CONFIRM, s, 5);
            }
            return new Result(r.isSuccess(), r.isSuccess() ? "发送成功" : "发送失败");
        } catch (Exception e) {
            logger.error(e.getMessage() + Throwables.getStackTraceAsString(e));
            return new Result<>(e.getMessage());
        }
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

