package com.pl.admin.dto;

import java.util.HashMap;
import java.util.Map;

public class Result<T> {
    public static class Complex<T> extends Result<T>{
        private Map<String,String> msgs;

        public Map<String, String> getMsgs() {
            return msgs;
        }

        public void setMsgs(Map<String, String> msgs) {
            this.msgs = msgs;
        }

        public Complex(boolean success, String msg, T data) {
           super(success,msg,data);
           msgs=new HashMap<>();
        }
        public Complex addMsg(String key,String value){
            msgs.put(key,value);
            return this;
        }
    }

    private boolean success;
    private String msg;
    private T data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Result(){}
    public Result(String msg){
        this.success=false;this.msg=msg;
    }

    public Result(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public Result(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

    public static <T> Result<T> success(T value){
        return new Result<>(true,"操作成功",value);
    }
    public static Result fail(String msg){
        return new Result(false,msg);
    }
}
