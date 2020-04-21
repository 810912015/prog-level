package com.pl.portal.service;

import com.google.common.base.Throwables;
import com.pl.portal.controller.BaseController;
import com.pl.portal.dto.AuthDto;
import com.pl.portal.dto.LoginDto;
import com.pl.portal.dto.RegisterDto;
import com.pl.portal.dto.Result;
import com.pl.data.mapper.UUserMapper;
import com.pl.data.model.UUser;
import com.pl.data.model.UUserExample;
import com.pl.data.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Duration;
import java.util.*;

@Component
public class AuthServiceImpl implements AuthService {
    private static final Logger logger= LoggerFactory.getLogger(AuthService.class);
    public AuthServiceImpl(){
    }

    @Override
    public Result<AuthDto> login(LoginDto ld) {
        UUserExample ue=new UUserExample();
        ue.createCriteria().andPswdEqualTo(ld.getPwd()).andEmailEqualTo(ld.getName());
        List<UUser> ul=um.selectByExample(ue);
        if(ul==null||ul.isEmpty()){
            return new Result<>(false,"用户名或密码错误",new AuthDto(false,"",ld.getName()));
        }
        UUser u=ul.get(0);
        Result<AuthDto> r=new Result<>();
        r.setMsg("登录成功");
        r.setSuccess(true);
        AuthDto d=new AuthDto();
        d.setAuthed(true);
        d.setFirmId(u.getFirmid());
        d.setPers(new String[0]);
        d.setUname(ld.getName());
        d.setUid(Long.toString(u.getId()));
        r.setData(d);
        return r;
    }

    @Override
    public Result<AuthDto> logout(AuthDto ad) {
        //sm.endSession(ad);
        return new Result<>(true,"已登出",new AuthDto(false,"",""));
    }

    @Override
    public Result<AuthDto> register(RegisterDto ld) {
        if(ld.getPwd()==null||ld.getPwd().length()<8){
            return new Result.Complex<>(false,"密码至少8位",
                    new AuthDto(false,"","")).addMsg("pwd","密码至少8位");
        }
        // 简单注册:用户名,密码
        if(ld.isSimple()){
             List<UUser> lu=getUsers(ld.getName());
            if(lu!=null&&!lu.isEmpty()){
                return new Result.Complex<>(false,"用户名已存在",
                        new AuthDto(false,"","")).addMsg("name","用户名已存在");
            }
            UUser u = makeUser(ld);
            u.setEmail(ld.getName());
            um.insert(u);
            return new Result<>(true,"",new AuthDto(true,u.getId().toString(),u.getNickname()));
        }
        // 验证:1.邮箱是否唯一,2.密码位数,3.验证码,4.激活码
        // 插入用户表
        // 更新session
        List<UUser> ul = getUsers(ld.getEmail());
        if(ul!=null&&!ul.isEmpty()){
            return new Result.Complex<>(false,"邮箱已注册",
                    new AuthDto(false,"","")).addMsg("email","邮箱已注册");
        }


        UUser u = makeUser(ld);
        um.insert(u);


        return new Result.Complex<>(true,"",new AuthDto(true,u.getId().toString(),u.getNickname()));
    }

    private UUser makeUser(RegisterDto ld) {
        UUser u=new UUser();
        u.setCreateTime(new Date());
        u.setEmail(ld.getEmail());
        u.setId(0L);
        u.setNickname(ld.getName());
        u.setPswd(ld.getPwd());
        u.setStatus(0L);
        u.setLastLoginTime(new Date());
        u.setRoles("teacher");
        return u;
    }

    private List<UUser> getUsers(String email) {
        UUserExample ue = new UUserExample();
        ue.createCriteria().andEmailEqualTo(email);
        return um.selectByExample(ue);
    }

    @Override
    public Result<AuthDto> reset(RegisterDto ld) {
        List<UUser> ul = getUsers(ld.getEmail());
        if(ul==null||ul.isEmpty()){
            return new Result.Complex<>(false,"此邮箱未注册",
                    new AuthDto(false,"","")).addMsg("email","此邮箱未注册");
        }
        UUser u=ul.get(0);
        u.setPswd(ld.getPwd());
        int r= um.updateByPrimaryKey(u);
        return new Result.Complex<>(r>0,"",
                new AuthDto(true,u.getId().toString(),u.getNickname()));
    }
    static String makeEmailRedisKey(String email){
        return String.format("confirm_%s",email);
    }
    @Override
    public Result sendRegister(String email) {
        try {
            String s = UUID.randomUUID().toString().substring(0, 5);

            Result r = notifier.sendRegister(email, "", s);
            if (r.isSuccess()) {
                redisService.set(makeEmailRedisKey(email),s, Duration.ofMinutes(10));
            }
            return new Result(r.isSuccess(), r.isSuccess() ? "发送成功" : "发送失败");
        } catch (Exception e) {
            logger.error(e.getMessage() + Throwables.getStackTraceAsString(e));
            return new Result<>(e.getMessage());
        }
    }

    @Override
    public Boolean isConfirmMatch(String email,String confirm) {
        String s=redisService.get(makeEmailRedisKey(email));
        boolean r= confirm.equals(s);
        return r;
    }

    private UUserMapper um;

    @Autowired
    public void setUm(UUserMapper um) {
        this.um = um;
    }
    @Autowired
    private Notifier notifier;
    @Autowired
    private RedisService redisService;
}
