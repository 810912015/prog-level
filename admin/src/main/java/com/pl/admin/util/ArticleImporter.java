package com.pl.admin.util;

import cn.hutool.json.JSONUtil;
import com.pl.data.mapper.TArticleMapper;
import com.pl.data.model.TArticle;
import com.pl.data.model.TArticleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class ArticleImporter {
    public static class Item{
        private String l;
        private String n;
        private String html;
        private String text;
        private String ctext;
        private String cname;
        private String cMixText;

        public Item() {
        }

        public String getL() {
            return l;
        }

        public void setL(String l) {
            this.l = l;
        }

        public String getN() {
            return n;
        }

        public void setN(String n) {
            this.n = n;
        }

        public String getHtml() {
            return html;
        }

        public void setHtml(String html) {
            this.html = html;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCtext() {
            return ctext;
        }

        public void setCtext(String ctext) {
            this.ctext = ctext;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public String getcMixText() {
            return cMixText;
        }

        public void setcMixText(String cMixText) {
            this.cMixText = cMixText;
        }
    }

    public List<Item> read(String s){
        try {
            List<String> r= Files.readAllLines(Paths.get(s));
            List<Item> ri=new ArrayList<>();
            for(String t:r){
                if(StringUtils.isEmpty(t)) continue;
                Item i=JSONUtil.toBean(t,Item.class);
                ri.add(i);
            }
            return ri;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void save(List<Item> items){
        for(Item i:items){
            if(StringUtils.isEmpty(i.getText())) continue;
            if(!StringUtils.isEmpty(i.getN())){
                TArticleExample tae=new TArticleExample();
                tae.createCriteria().andNameEqualTo(i.getN());
                Long r=articleMapper.countByExample(tae);
                if(r>0) continue;
            }

            TArticle a=new TArticle();

            if(StringUtils.isEmpty(i.getN())){
                a.setName(i.getL());
                a.setcName(i.getL());
            }else{
                a.setName(i.getN());
                a.setcName(i.getCname());
            }
            a.setHtml(i.getHtml());
            a.setSourceUrl(i.getL());
            a.setText(i.getText());
            a.setcText(i.getcMixText());

            Date d=new Date();
            a.setCreateTime(d);
            a.setDclt(d);
            articleMapper.insert(a);
        }
    }
    @Autowired
    private TArticleMapper articleMapper;
}
