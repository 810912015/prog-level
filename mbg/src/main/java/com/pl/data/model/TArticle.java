package com.pl.data.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class TArticle implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "文章名称")
    private String name;

    @ApiModelProperty(value = "中文名称")
    private String cName;

    @ApiModelProperty(value = "使用序号")
    private Integer useIndex;

    @ApiModelProperty(value = "可用级别")
    private Integer useLevel;

    @ApiModelProperty(value = "创建者id")
    private Long userId;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "标签")
    private String tag;

    @ApiModelProperty(value = "来源")
    private String sourceUrl;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "最后修改时间")
    private Date dclt;

    @ApiModelProperty(value = "作者")
    private String auth;

    @ApiModelProperty(value = "发表时间")
    private Date pubTime;

    @ApiModelProperty(value = "标志图片")
    private String thumbnail;

    @ApiModelProperty(value = "简介")
    private String brief;

    @ApiModelProperty(value = "内容")
    private String text;

    @ApiModelProperty(value = "中文内容")
    private String cText;

    @ApiModelProperty(value = "格式内容")
    private String html;

    @ApiModelProperty(value = "中文格式内容")
    private String cHtml;

    @ApiModelProperty(value = "对照内容")
    private String mText;

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

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Integer getUseIndex() {
        return useIndex;
    }

    public void setUseIndex(Integer useIndex) {
        this.useIndex = useIndex;
    }

    public Integer getUseLevel() {
        return useLevel;
    }

    public void setUseLevel(Integer useLevel) {
        this.useLevel = useLevel;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getDclt() {
        return dclt;
    }

    public void setDclt(Date dclt) {
        this.dclt = dclt;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Date getPubTime() {
        return pubTime;
    }

    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getcText() {
        return cText;
    }

    public void setcText(String cText) {
        this.cText = cText;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getcHtml() {
        return cHtml;
    }

    public void setcHtml(String cHtml) {
        this.cHtml = cHtml;
    }

    public String getmText() {
        return mText;
    }

    public void setmText(String mText) {
        this.mText = mText;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", cName=").append(cName);
        sb.append(", useIndex=").append(useIndex);
        sb.append(", useLevel=").append(useLevel);
        sb.append(", userId=").append(userId);
        sb.append(", createTime=").append(createTime);
        sb.append(", tag=").append(tag);
        sb.append(", sourceUrl=").append(sourceUrl);
        sb.append(", remark=").append(remark);
        sb.append(", dclt=").append(dclt);
        sb.append(", auth=").append(auth);
        sb.append(", pubTime=").append(pubTime);
        sb.append(", thumbnail=").append(thumbnail);
        sb.append(", brief=").append(brief);
        sb.append(", text=").append(text);
        sb.append(", cText=").append(cText);
        sb.append(", html=").append(html);
        sb.append(", cHtml=").append(cHtml);
        sb.append(", mText=").append(mText);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}