package com.pl.admin.controller;




import com.google.common.base.Throwables;
import com.pl.admin.dto.*;
import com.pl.admin.service.AuthService;
import com.pl.admin.util.JwtTokenUtil;
import com.pl.data.mapper.UUserMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

@Controller
@Api(tags = "Auth2Controller",description = "Auth2Controller api")
@RequestMapping("/auth")
public class Auth2Controller extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(Auth2Controller.class);
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



    private AuthService as;

    private UUserMapper um;


    @Autowired
    public void setUm(UUserMapper um) {
        this.um = um;
    }


    @Autowired
    public void setAs(AuthService as) {
        this.as = as;
    }

}

