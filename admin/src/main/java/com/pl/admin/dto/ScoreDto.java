package com.pl.admin.dto;

import java.util.Date;

public class ScoreDto {
    private int qid;
    private String qname;
    private String qtitle;
    private String groupId;
    private int score;
    private Date dclt;

    public int getQid() {
        return qid;
    }

    public void setQid(int qid) {
        this.qid = qid;
    }

    public String getQname() {
        return qname;
    }

    public void setQname(String qname) {
        this.qname = qname;
    }

    public String getQtitle() {
        return qtitle;
    }

    public void setQtitle(String qtitle) {
        this.qtitle = qtitle;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    public Date getDclt() {
        return dclt;
    }

    public void setDclt(Date dclt) {
        this.dclt = dclt;
    }

    public static class Firm{

        private String ename;
        private String iname;

        private String email;


        private int weight;
        private int score;

        private Date dclt;



        public String getEname() {
            return ename;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public String getIname() {
            return iname;
        }

        public void setIname(String iname) {
            this.iname = iname;
        }


        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }



        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
        public Date getDclt() {
            return dclt;
        }

        public void setDclt(Date dclt) {
            this.dclt = dclt;
        }
    }
}
