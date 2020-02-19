package com.pl.admin.dto;

public class Result<T> {
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
