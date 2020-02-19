package com.pl.admin.dto;



import com.pl.data.model.UUser;

import java.util.Date;

public class UserDto {
    private String email;
    private String nickname;
    private String pswd;
    private String roles;
    private Long id;

    public UUser toUser(){
        UUser u=new UUser();
        u.setId(this.id);
        u.setEmail(email);
        u.setPswd(pswd);
        u.setNickname(nickname);
        u.setRoles(roles);
        u.setLastLoginTime(new Date());
        u.setCreateTime(new Date());
        u.setStatus(-1L);
        return u;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPswd() {
        return pswd;
    }

    public void setPswd(String pswd) {
        this.pswd = pswd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }
}
