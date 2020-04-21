package com.pl.admin.controller;


import com.google.common.base.Throwables;
import com.pl.admin.dto.UUserDetails;
import com.pl.data.model.UUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import springfox.documentation.spi.service.contexts.SecurityContext;


import javax.servlet.http.HttpServletRequest;

@Component
public class BaseController {
    protected Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    protected HttpServletRequest request;


    private static final UUser FAKE_USER=new UUser();
    static {
        FAKE_USER.setId(-1L);
        FAKE_USER.setFirmid(-1);
    }
    public UUser curUser(){
        try {
            UUser u = (UUser) request.getAttribute(AuthKeys.USER);
            if(u==null){
                u=FAKE_USER;
            }
            return u;
        }catch (Exception e){
            logger.error(Throwables.getStackTraceAsString(e));
            return FAKE_USER;
        }
    }

    public String getAid(){
        return (String) request.getAttribute("aid");
    }

    public UUserDetails getUserDetail(){
        Object o=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(o instanceof UUserDetails) {
            return (UUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }
        return null;
    }

    public Long getUid(){
        UUserDetails u=getUserDetail();
        if(u==null) return -1L;
        return u.getUser().getId();
    }

    public static class AuthKeys {

        public static final String KAPTCHA = "kaptcha";
        public static final String CONFIRM = "comfirm";
        public static final String NEED_CAPTCHA="NEED_CAPTCHA";
        public static final String USER="USER";
    }
}
