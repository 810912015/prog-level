package com.pl.portal.dto;



import com.pl.data.model.UUser;

import java.util.Date;

public class ProfileDto {
    private String name;
    private String gender;
    private Date birthday;
    private String imgurl;
    private String desc;

    public ProfileDto() {
    }

    public ProfileDto(UUser u) {
        this.name=u.getPetname();
        this.gender=u.getGender();
        this.birthday=u.getBirthday();
        this.imgurl=u.getPhotourl();//.getPhotoUrl();
        this.desc=u.getIntro();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
