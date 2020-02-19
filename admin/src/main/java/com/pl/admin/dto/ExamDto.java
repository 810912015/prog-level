package com.pl.admin.dto;



import com.pl.data.model.Exam;
import com.pl.data.model.ExamQuestion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamDto {
    private int id;
    private String name;
    private String title;
    private int duration;
    private String cat;
    private String tag;

    public ExamDto() {
    }





    public int getId() {
        return id;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public List<QScore> getCheck() {
        return check;
    }

    public void setCheck(List<QScore> check) {
        this.check = check;
    }

    private Date start;
    private List<QScore> check;
    public static class QScore{
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        private int score;

        public QScore() {
        }

        public QScore(ExamQuestion r) {
            id=r.getQid();
            score=r.getWeight();
        }

        public ExamQuestion toRelation(int eid){
            ExamQuestion r=new ExamQuestion();
            r.setDclt(new Date());
            r.setEid(eid);
            r.setQid(getId());
            r.setRemarks("");
            r.setSequence(0);
            r.setWeight(getScore());
            return r;
        }
    }

    public ExamDto(Exam e, List<ExamQuestion> r) {
        id=e.getId();
        name=e.getName();
        title=e.getTitle();
        duration=e.getDuration();
        cat=e.getCategory();
        tag=e.getTag();
        check=new ArrayList<>();
        for(ExamQuestion t:r){
            check.add(new QScore(t));
        }
    }

    public Exam toExam(){
        Exam e=new Exam();
        e.setCategory(getCat());
        e.setDclt(new Date());
        e.setDuration(getDuration());
        e.setId(getId());
        e.setStart(getStart());
        e.setTag(getTag());
        e.setName(getName());
        e.setTitle(getTitle());
        e.setRemarks("");
        return e;
    }
}
