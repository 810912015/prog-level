package com.pl.admin.service.runner;

import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * 执行器:执行代码,获取out和err输出
 */
public interface Runner {
    public static class Result {
        private String out;

        public Result(String out, String err) {
            this.out = out;
            this.err = err;
        }

        public Engine.Result convert(){
            prettify();
            Engine.Result rr = new Engine.Result();
            boolean suc = StringUtils.isEmpty(getErr());
            rr.setSuccess(suc);
            rr.setMsg(suc ? getOut() : getErr());
            return rr;
        }

        public String getOut() {
            return out;
        }

        public String getErr() {
            return err;
        }

        public void setErr(String err) {
            this.err = err;
        }

        public void setOut(String out) {
            this.out = out;
        }

        private String err;

        private static final String FMT="(^//.|^/|^[a-zA-Z])?:?/.+(/$)?";
        private static final Pattern P= Pattern.compile(FMT);

        private static String pretty(String s){
            if(s==null){
                return s;
            }
            int i=s.lastIndexOf("/");
            if(i>0){
                return s.substring(i);
            }
            else{
                return s;
            }
        }

        public void prettify(){
            this.out=pretty(this.out);
            this.err=pretty(this.err);
        }
    }

    Result run(String strCmd);
    Result run(List<String> commands);
}
