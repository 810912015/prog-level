package com.pl.portal.dto;

public class LoginDto {
    private String name;
    private String pwd;
    private String kapcha;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getKapcha() {
        return kapcha;
    }

    public void setKapcha(String kapcha) {
        this.kapcha = kapcha;
    }
}
