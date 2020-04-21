package com.pl.portal.component.mq.sender;

import java.util.Date;

public interface IQSender {
    void send(String exchange,String route,Object msg);

    class Types{
        public static final String REQUEST="pl-req";
    }
    class ReqMsg{
        private Date when;
        private String aid;
        private String name;
        private String url;

        public ReqMsg() {
        }

        public ReqMsg(String aid, String name, String url) {
            this.aid = aid;
            this.name = name;
            this.url = url;
            this.when=new Date();
        }

        public Date getWhen() {
            return when;
        }

        public void setWhen(Date when) {
            this.when = when;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
