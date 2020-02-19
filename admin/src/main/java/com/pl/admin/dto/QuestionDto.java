package com.pl.admin.dto;


import com.pl.data.model.Question;
import com.pl.data.model.QuestionSource;
import com.pl.data.model.Validation;
import org.springframework.util.StringUtils;

import java.util.*;

public class QuestionDto {
    private int id;
    private String name;
    private String title;
    private String source;
    private List<ValiDto> vali;
    private String[] tags;
    private int level;
    private String lang;
    private long userId;
    private String cat;
    private List<QsDto> sourceList;

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<ValiDto> getVali() {
        return vali;
    }

    public void setVali(List<ValiDto> vali) {
        this.vali = vali;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public List<QsDto> getSourceList() {
        return sourceList;
    }

    public void setSourceList(List<QsDto> sourceList) {
        this.sourceList = sourceList;
    }

    public static class ValiDto{
        private int vid;
        private String input;
        private String output;
        private int weight;

        public int getVid() {
            return vid;
        }

        public void setVid(int vid) {
            this.vid = vid;
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

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }




    public QuestionDto(){}
    public QuestionDto(List<QuestionItem> l){
        if(l==null||l.isEmpty()){
            return;
        }
        QuestionItem qi=l.get(0);
        id=qi.getId();
        name=qi.getName();
        title=qi.getTitle();
        source=qi.getSource();
        vali=new ArrayList<>();
        List<String> tl=new ArrayList<>();
        if(!StringUtils.isEmpty(qi.getTag1())) tl.add(qi.getTag1());
        if(!StringUtils.isEmpty(qi.getTag2())) tl.add(qi.getTag2());
        if(!StringUtils.isEmpty(qi.getOtherTag())) {
            String[] sa=qi.getOtherTag().split(",");
            for(String s:sa){
                if(!StringUtils.isEmpty(s)){
                    tl.add((s));
                }
            }
        }
        tags=new String[tl.size()];
        tl.toArray(tags);
        level=qi.getLevel();
        lang=qi.getLang();
        userId=qi.getUserId();
        cat=qi.getCategory();

        for(QuestionItem i:l){
            ValiDto d=new ValiDto();
            d.setVid(i.getVid());
            d.setInput(i.getInput()==null?"":i.getInput());
            d.setOutput(i.getOutput()==null?"":i.getOutput());
            d.setWeight(i.getWeight());
            vali.add(d);
        }
    }

    public Question toQuestion(){
        Question q=new Question();
        q.setDclt(new Date());
        q.setId(this.id);
        q.setCategory("");
        q.setName(this.name);
        q.setTitle(this.title);
        q.setSource(this.source);
        q.setRemarks("");
        q.setLang(this.lang);
        q.setLevel(this.level);
        q.setCategory(this.cat);
        if(this.tags!=null){
            if(this.tags.length>0){
                q.setTag1(tags[0]);
            }
            if(tags.length>1){
                q.setTag2(tags[1]);
            }
            if(tags.length>2){
                StringBuilder sb=new StringBuilder();
                for(int i=2;i<tags.length;i++){
                    if(i>2){
                        sb.append(",");
                    }
                    sb.append(tags[i]);
                }
                String s=sb.toString();
                if(s.length()>500){
                    s=s.substring(0,500);
                }
                q.setOthertag(s);
            }
        }
        q.setUserid(userId);
        return q;
    }

    public List<QuestionSource> toSource(int qid){
        List<QuestionSource> r=new ArrayList<>();
        if(this.sourceList==null||this.sourceList.isEmpty()){
            return r;
        }
        for(QsDto t:this.sourceList){
            QuestionSource qs=t.toQs(qid);
            r.add(qs);
        }
        return r;
    }
    public List<Validation> toValidation(int qid){
        List<Validation> r=new ArrayList<>();
        if(this.vali==null||this.vali.isEmpty()){
            return r;
        }
        for(ValiDto e:this.vali){
            Validation v=new Validation();
            v.setQid(qid);
            v.setDclt(new Date());
            v.setInput(e.getInput()==null?"":e.getInput());
            v.setOutput(e.getOutput()==null?"":e.getOutput());
            v.setRemarks("");
            v.setSequnence(0);
            v.setWeight(e.getWeight());
            v.setId(0);
            r.add(v);
        }
        return r;
    }
}
