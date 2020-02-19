package com.pl.data.common.api.msg;

import java.util.Date;

public class BaseMsg implements Msg{
    private Date sendTime;
    private Long bid;
    private Long uid;

    @Override
    public Long getBid() {
        return bid;
    }


    public void setBid(Long bid) {
        this.bid = bid;
    }

    @Override
    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public BaseMsg(Date d){
        this.sendTime=d;
    }

    public BaseMsg(){
        this(new Date());
    }

    @Override
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
