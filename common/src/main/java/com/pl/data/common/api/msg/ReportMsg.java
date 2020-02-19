package com.pl.data.common.api.msg;

public class ReportMsg extends BaseMsg{
    private String key;
    private String content;
    private String remarks;

    public ReportMsg(){}
    public ReportMsg(Long uid,Long bid,String key,String content,String remarks){
        setUid(uid);
        setBid(bid);
        this.key=key;
        this.content=content;
        this.remarks=remarks;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


}
