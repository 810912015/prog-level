package com.pl.data.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Pass implements Serializable {
    private Integer id;

    private Integer qid;

    private Integer eid;

    private Integer vid;

    private Integer uid;

    private String groupid;

    private String input;

    private String output;

    @ApiModelProperty(value = ",T,F")
    private String success;

    private String msg;

    private Integer weight;

    private Integer sequence;

    private String stage;

    private String remarks;

    private Date dclt;

    private String lang;

    @ApiModelProperty(value = "人工评分分数")
    private Integer score;

    @ApiModelProperty(value = "人工评分依据")
    private String scorereason;

    @ApiModelProperty(value = "邀请码")
    private String eiid;

    @ApiModelProperty(value = "未登录时客户端id")
    private String aid;

    private String source;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQid() {
        return qid;
    }

    public void setQid(Integer qid) {
        this.qid = qid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public Integer getVid() {
        return vid;
    }

    public void setVid(Integer vid) {
        this.vid = vid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getScorereason() {
        return scorereason;
    }

    public void setScorereason(String scorereason) {
        this.scorereason = scorereason;
    }

    public String getEiid() {
        return eiid;
    }

    public void setEiid(String eiid) {
        this.eiid = eiid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
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
        sb.append(", qid=").append(qid);
        sb.append(", eid=").append(eid);
        sb.append(", vid=").append(vid);
        sb.append(", uid=").append(uid);
        sb.append(", groupid=").append(groupid);
        sb.append(", input=").append(input);
        sb.append(", output=").append(output);
        sb.append(", success=").append(success);
        sb.append(", msg=").append(msg);
        sb.append(", weight=").append(weight);
        sb.append(", sequence=").append(sequence);
        sb.append(", stage=").append(stage);
        sb.append(", remarks=").append(remarks);
        sb.append(", dclt=").append(dclt);
        sb.append(", lang=").append(lang);
        sb.append(", score=").append(score);
        sb.append(", scorereason=").append(scorereason);
        sb.append(", eiid=").append(eiid);
        sb.append(", aid=").append(aid);
        sb.append(", source=").append(source);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}