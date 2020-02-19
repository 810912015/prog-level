package com.pl.data.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class ExamInvite implements Serializable {
    private Long id;

    private Long userid;

    private Long vuserid;

    private Integer eid;

    private String uemail;

    private String iid;

    private String done;

    private String remarks;

    private Date dclt;

    private Integer firmid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getVuserid() {
        return vuserid;
    }

    public void setVuserid(Long vuserid) {
        this.vuserid = vuserid;
    }

    public Integer getEid() {
        return eid;
    }

    public void setEid(Integer eid) {
        this.eid = eid;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
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

    public Integer getFirmid() {
        return firmid;
    }

    public void setFirmid(Integer firmid) {
        this.firmid = firmid;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userid=").append(userid);
        sb.append(", vuserid=").append(vuserid);
        sb.append(", eid=").append(eid);
        sb.append(", uemail=").append(uemail);
        sb.append(", iid=").append(iid);
        sb.append(", done=").append(done);
        sb.append(", remarks=").append(remarks);
        sb.append(", dclt=").append(dclt);
        sb.append(", firmid=").append(firmid);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}