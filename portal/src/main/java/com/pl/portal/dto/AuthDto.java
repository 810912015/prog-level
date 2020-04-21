package com.pl.portal.dto;

public class AuthDto {
    private boolean authed;
    private String uid;
    private String uname;
    private Integer firmId;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String[] getPers() {
        return pers;
    }

    public void setPers(String[] pers) {
        this.pers = pers;
    }

    private String[] pers;

    public AuthDto() {
    }

    public AuthDto(boolean authed,String uid, String uname) {
        this.authed=authed;
        this.uid = uid;
        this.uname = uname;
        this.pers=new String[0];
    }

    public boolean isAuthed() {
        return authed;
    }

    public void setAuthed(boolean authed) {
        this.authed = authed;
    }

    public Integer getFirmId() {
        return firmId;
    }

    public void setFirmId(Integer firmId) {
        this.firmId = firmId;
    }
}
