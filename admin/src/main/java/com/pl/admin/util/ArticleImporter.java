package com.pl.admin.util;

import cn.hutool.json.JSONUtil;
import com.pl.data.mapper.TArticleMapper;
import com.pl.data.model.TArticle;
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
            TArticle a=new TArticle();
            a.setName(i.getN());
            a.setHtml(i.getHtml());
            a.setSourceUrl(i.getL());
            a.setText(i.getText());
            a.setcText(i.getCtext());
            a.setcName(i.getCname());
            Date d=new Date();
            a.setCreateTime(d);
            a.setDclt(d);
            articleMapper.insert(a);
        }
    }

    public String toSql(List<Item> items){
        StringBuilder sb=new StringBuilder();
        sb.append("insert into t_article (name,html,sourceUrl,text,cText,cName)");
        for(Item i:items){
            sb.append(" values (");
            sb.append("`"+i.getN()+"`,");
            sb.append("`"+i.getHtml()+"`,");
            sb.append("`"+i.getL()+"`,");
            sb.append("`"+i.getText()+"`,");
            sb.append("`"+i.getCtext()+"`,");
            sb.append("`"+i.getCname()+"`");
            sb.append(")");
            sb.append("\n");
        }
        return sb.toString();
    }

    @Autowired
    private TArticleMapper articleMapper;
}
