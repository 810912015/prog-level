package com.pl.admin.dto;



import com.pl.data.model.Pass;
import com.pl.data.model.Question;

import java.util.List;

public class PassParam {
    private Long qid;
    private Long eid;
    private Long uid;
    private Long cur;

    public Long getQid() {
        return qid;
    }

    public void setQid(Long qid) {
        this.qid = qid;
    }

    public Long getEid() {
        return eid;
    }

    public void setEid(Long eid) {
        this.eid = eid;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Long getCur() {
        return cur;
    }

    public void setCur(Long cur) {
        this.cur = cur;
    }

    public static class PassScore{
        private int score;
        private String reason;
        private String gid;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public String getGid() {
            return gid;
        }

        public void setGid(String gid) {
            this.gid = gid;
        }
    }

    public static class PassDetail{
        private Question q;
        private List<Pass> p;

        public PassDetail(Question q, List<Pass> p) {
            this.q = q;
            this.p = p;
        }




        public Question getQ() {
            return q;
        }

        public void setQ(Question q) {
            this.q = q;
        }

        public List<Pass> getP() {
            return p;
        }

        public void setP(List<Pass> p) {
            this.p = p;
        }
    }
}
