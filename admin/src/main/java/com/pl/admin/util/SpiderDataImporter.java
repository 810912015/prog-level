package com.pl.admin.util;

import cn.hutool.json.JSONUtil;
import com.pl.data.mapper.QuestionMapper;
import com.pl.data.mapper.QuestionSourceMapper;
import com.pl.data.model.Question;
import com.pl.data.model.QuestionSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Component
public class SpiderDataImporter {
    public static class LangSource{
        private String s;
        private String lang;

        public QuestionSource toQs(Long qid){
            QuestionSource r=new QuestionSource();
            r.setQid(qid);
            r.setLang(this.lang);
            r.setSource(this.s);
            r.setDclt(new Date());
            r.setRemarks("");
            return r;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public String getLang() {
            return lang;
        }

        public void setLang(String lang) {
            this.lang = lang;
        }
    }
    public static class BB{
        private Integer x;
        private Integer y;

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }
    }
    public static class Qi extends LangSource{
        private String page;
        private String h;
        private String d;
        private String l;
        private List<LangSource> ls;
        private String href;
        private BB bb;

        public Question toQuestion(){
            Question q=new Question();
            q.setName(this.h.substring(this.h.indexOf(".")+1));
            q.setTitle(this.d);
            q.setRemarks(this.page);
            q.setSource(this.href);
            q.setDclt(new Date());
            q.setCategory("");
            q.setLang(this.l);
            int level="简单".equals(this.l)?1:"中等".equals(this.l)?3:5;
            q.setLevel(level);
            return q;
        }

        public Collection<QuestionSource> makeQuestionSource(Long id){
            Map<String,QuestionSource> m=new HashMap<>();
            QuestionSource tq=this.toQs(id);
            m.put(tq.getLang(),tq);
            for(LangSource t:ls){
                QuestionSource q=t.toQs(id);
                if(!m.containsKey(q.getLang())){
                    m.put(q.getLang(),q);
                }
            }
            return m.values();
        }

        public String getPage() {
            return page;
        }

        public void setPage(String page) {
            this.page = page;
        }

        public String getH() {
            return h;
        }

        public void setH(String h) {
            this.h = h;
        }

        public String getD() {
            return d;
        }

        public void setD(String d) {
            this.d = d;
        }

        public String getL() {
            return l;
        }

        public void setL(String l) {
            this.l = l;
        }

        public List<LangSource> getLs() {
            return ls;
        }

        public void setLs(List<LangSource> ls) {
            this.ls = ls;
        }

        public String getHref() {
            return href;
        }

        public void setHref(String href) {
            this.href = href;
        }

        public BB getBb() {
            return bb;
        }

        public void setBb(BB bb) {
            this.bb = bb;
        }
    }

    List<Qi> readAll(){
        String dir="/home/zxf/文档/result4";
        File f=new File(dir);
        List<Qi> r=new ArrayList<>();
        for(File t:f.listFiles()){

            try {
                String ts= null;
                ts = new String(Files.readAllBytes(Paths.get(t.getPath())));
                Qi tq= JSONUtil.toBean(ts,Qi.class);
                r.add(tq);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return r;
    }
    void insert(List<Qi> l){
        for(Qi t:l){
            Question q=t.toQuestion();
            questionMapper.insert(q);
            for(QuestionSource ls:t.makeQuestionSource(q.getId().longValue())){
                sourceMapper.insert(ls);
            }
        }
    }

    public void handle(){
        insert(readAll());
    }
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionSourceMapper sourceMapper;
}
