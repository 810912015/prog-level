package com.pl.portal.controller;




import com.google.common.base.Throwables;
import com.pivot.json.AntlrJsonBuilder;
import com.pivot.json.JsonBuilder;
import com.pl.portal.dao.AuthMapper;
import com.pl.portal.dto.*;
import com.pl.portal.service.AuthService;
import com.pl.portal.service.Notifier;
import com.pl.portal.util.JwtTokenUtil;
import com.pl.data.mapper.UUserMapper;
import com.pl.data.model.UUser;

import com.pl.data.redis.RedisService;
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
import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@Api(tags = "Auth2Controller",description = "Auth2Controller api")
@RequestMapping("/auth")
public class Auth2Controller extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @ApiOperation(value = "login")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public Result<AuthDto> login(@RequestBody LoginDto ld, HttpServletResponse response, HttpServletRequest request) {
        Result<AuthDto> r= as.login(ld);
        if(r.isSuccess()){
            response.addHeader("authorization",jwtTokenUtil.generateToken(r.getData().getUname()));
        }
        return r;
    }
    @ApiOperation(value = "register")
    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ResponseBody
    public Result register(@RequestBody RegisterDto ld) {
        if(!ld.isSimple()) {
            Result.Complex<AuthDto> x = (Result.Complex<AuthDto>)validate(ld);
            if (x != null) return x;
        }
        Result.Complex<AuthDto> r = (Result.Complex<AuthDto>)as.register(ld);
        return r;
    }

    private Result<AuthDto> validate(@RequestBody RegisterDto ld) {
        boolean r=as.isConfirmMatch(ld.getEmail(),ld.getConfirm());
        if(!r){
             return new Result.Complex<>(false, "邮箱激活码错误或已过期,请重新输入或重新获取",
                    new AuthDto(false, "", ld.getName())).addMsg("confirm","邮箱激活码错误或已过期,请重新输入或重新获取");
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
    public Result.Complex<AuthDto> reset(@RequestBody RegisterDto ld) {
        Result.Complex<AuthDto> x = (Result.Complex<AuthDto>)validate(ld);
        if (x != null) return x;
        return (Result.Complex<AuthDto>)as.reset(ld);
    }
    @ApiOperation(value = "logout")
    @RequestMapping(value = "logout",method = RequestMethod.POST)
    @ResponseBody
    public Result<AuthDto> logout(HttpServletResponse response,@RequestBody AuthDto ld) {
        response.addHeader("authorization",jwtTokenUtil.generateToken(""));
        return Result.success(null);
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
    public void setAs(AuthService as) {
        this.as = as;
    }

}

