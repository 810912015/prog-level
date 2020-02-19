package com.pl.data.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Question implements Serializable {
    private Integer id;

    private String name;

    private String title;

    private String category;

    private String remarks;

    private Date dclt;

    private String lang;

    @ApiModelProperty(value = ",1-5")
    private Integer level;

    @ApiModelProperty(value = ",")
    private String tag1;

    private String tag2;

    @ApiModelProperty(value = "其")
    private String othertag;

    private Long userid;

    private String source;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getDclt() {
        return dclt;
    }

    public void setDclt(Date dclt) {
        this.dclt = dclt;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getOthertag() {
        return othertag;
    }

    public void setOthertag(String othertag) {
        this.othertag = othertag;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", title=").append(title);
        sb.append(", category=").append(category);
        sb.append(", remarks=").append(remarks);
        sb.append(", dclt=").append(dclt);
        sb.append(", lang=").append(lang);
        sb.append(", level=").append(level);
        sb.append(", tag1=").append(tag1);
        sb.append(", tag2=").append(tag2);
        sb.append(", othertag=").append(othertag);
        sb.append(", userid=").append(userid);
        sb.append(", source=").append(source);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}