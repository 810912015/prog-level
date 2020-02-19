package com.pl.admin.dto;

import com.pl.data.model.QuestionSource;

import java.util.Date;

public class QsDto {
    public static class Request{
        private long qid;
        private String lang;

        public long getQid() {
            return qid;
        }

        public void setQid(long qid) {
            this.qid = qid;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }
    }

    private long id;
    private String lang;
    private String source;

    public QsDto() {
    }

    public QsDto(QuestionSource qs){
        this.id=qs.getId();
        this.lang=qs.getLang();
        this.source=qs.getSource();
    }

    public QuestionSource toQs(long qid){
        QuestionSource qs=new QuestionSource();
        qs.setDclt(new Date());
        qs.setId(this.id);
        qs.setLang(this.lang);
        qs.setSource(this.source);
        qs.setRemarks("");
        qs.setQid(qid);
        return qs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
