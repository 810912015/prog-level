package com.pl.data.common.api.msg;


import java.util.Random;

public class Score {


    public static class ActiveMsg {
        private String uid;
        private Long when;

        public ActiveMsg() {
        }

        public ActiveMsg(String uid, Long when) {
            this.uid = uid;
            this.when = when;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public Long getWhen() {
            return when;
        }

        public void setWhen(Long when) {
            this.when = when;
        }
    }
    public static class ScoreMsg extends BaseMsg{
        public ScoreMsg(){super();}
        public ScoreMsg(Long bid,Long uid,Float score){
            super();
            this.setBid(bid);
            this.setUid(uid);
            this.setScore(score);
        }

        private Float score;

        public Float getScore() {
            return score;
        }

        public void setScore(Float score) {
            this.score = score;
        }
    }

    public static final class RedisScore{
        private int count;
        private int score;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
        private static int initScore(){
            return 80+new Random().nextInt(10);
        }
        /**
         * 评分总和/评分人数+随机参数（0~0.9）
         * @param fscore
         */
        public void cal(float fscore){
            int fsi=(int)(fscore*10);
            int sc=score;
            if(sc==0){
                sc=initScore();
            }
            int total=sc*count;
            int avg=(total+fsi)/(count+1);
            if(avg>100||avg<=0){
                avg=new Random().nextInt(10)+80;
            }
            int rdm=new Random().nextInt(10);
            score=avg+rdm;
            count+=1;
        }
        public static RedisScore create(){
            RedisScore r=new RedisScore();
            r.setCount(1);
            r.setScore(initScore());
            return r;
        }
    }

}
