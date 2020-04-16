package com.pl.admin.controller;


import com.google.common.base.Throwables;
import com.pl.admin.service.Json;
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

    public Expirtable expirtableFromSession(String key) {
        try {
            String s = (String)request.getAttribute(key);
            Expirtable ke = Json.from(s, Expirtable.class);
            if(ke==null){
                ke=new Expirtable();
            }
            return ke;
        }catch (Exception e){
            return new Expirtable();
        }
    }

    public void expirtableIntoSession(String key, String data, int expirtMin) {
        request.setAttribute(key, Json.to(new Expirtable(data, expirtMin)));
    }

    public static class Expirtable {
        public Expirtable() {
            expirt=-100;
            data="";
        }

        private String data;
        private long expirt;

        public long getExpirt() {
            return expirt;
        }

        public void setExpirt(long expirt) {
            this.expirt = expirt;
        }

        public Expirtable(String data, long expirt) {
            this.data = data;
            this.expirt = expirt;
        }

        public Expirtable(String data, int minutes) {
            this.data = data;
            this.expirt = System.currentTimeMillis() + minutes * 60 * 1000;
        }

        public boolean valid(){
            return expirt!=-100&&!data.equals("");
        }
        public boolean isExpirt() {
            if (expirt <= 0) {
                return false;
            }
            return System.currentTimeMillis() > this.expirt;
        }

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    public static class AuthKeys {

        public static final String KAPTCHA = "kaptcha";
        public static final String CONFIRM = "comfirm";
        public static final String NEED_CAPTCHA="NEED_CAPTCHA";
        public static final String USER="USER";
    }
}
