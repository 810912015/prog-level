package com.pl.search.domain;


import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;



@Document(indexName = "bms2", type = "book",shards = 1,replicas = 3)
public class EsBook2 implements Serializable {
    @Id
    private Long id;


    private Long bookid;


    private String name;


    private String author;


    private String brief;
    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String category;

    private String categoryid;

    private String categoryId;

    @Field(type = FieldType.Text,analyzer = "ik_max_word",searchAnalyzer = "ik_smart")
    private String keywords;


    private String cover;

    @Field(type = FieldType.Keyword)
    private String completestatus;


    private String displayname;


    private String partnername;


    private BigDecimal saleprice;


    private Integer startchargechapter;


    @Field(type = FieldType.Integer)
    private Integer wordcount;


    private BigDecimal k1price;


    private Date createTime;


    private Date updateTime;


    private Date launchTime;


    private String billpattern;


    private String copyrightid;


    private String copyrightname;


    private Long cooeeid;


    private Integer chaptercount;


    private String source;


    private Date datachangelastTime;


    private String bookstatus;


    private String intro;


    private String maintag;


    private String headtag;

    @Field(type = FieldType.Integer)
    private Integer score;


    private String cat1;


    private String cat2;


    private Integer readcount;

    @Field(type = FieldType.Integer)
    private Integer searchcount;


    private BigDecimal remain;


    private String keyword1;

    private String keyword2;

    private String keyword3;

    private String added;
    @Field(type = FieldType.Integer)
    private Integer favorCount;

    @Field(type = FieldType.Integer)
    private Integer favorTime;

    @Field(type = FieldType.Integer)
    private Integer favorPlain;

    public Integer getFavorCount() {
        return favorCount;
    }

    public void setFavorCount(Integer favorCount) {
        this.favorCount = favorCount;
    }

    public Integer getFavorTime() {
        return favorTime;
    }

    public void setFavorTime(Integer favorTime) {
        this.favorTime = favorTime;
    }

    public Integer getFavorPlain() {
        return favorPlain;
    }

    public void setFavorPlain(Integer favorPlain) {
        this.favorPlain = favorPlain;
    }

    public Integer getFavorCal() {
        return favorCal;
    }

    public void setFavorCal(Integer favorCal) {
        this.favorCal = favorCal;
    }

    @Field(type = FieldType.Integer)
    private Integer favorCal;


    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBookid() {
        return bookid;
    }

    public void setBookid(Long bookid) {
        this.bookid = bookid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getMaintag() {
        return maintag;
    }

    public void setMaintag(String maintag) {
        this.maintag = maintag;
    }

    public String getHeadtag() {
        return headtag;
    }

    public void setHeadtag(String headtag) {
        this.headtag = headtag;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getCat1() {
        return cat1;
    }

    public void setCat1(String cat1) {
        this.cat1 = cat1;
    }

    public String getCat2() {
        return cat2;
    }

    public void setCat2(String cat2) {
        this.cat2 = cat2;
    }

    public Integer getReadcount() {
        return readcount;
    }

    public void setReadcount(Integer readcount) {
        this.readcount = readcount;
    }

    public Integer getSearchcount() {
        return searchcount;
    }

    public void setSearchcount(Integer searchcount) {
        this.searchcount = searchcount;
    }

    public BigDecimal getRemain() {
        return remain;
    }

    public void setRemain(BigDecimal remain) {
        this.remain = remain;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public String getKeyword3() {
        return keyword3;
    }

    public void setKeyword3(String keyword3) {
        this.keyword3 = keyword3;
    }

    public String getAdded() {
        return added;
    }

    public void setAdded(String added) {
        this.added = added;
    }

    public String getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(String categoryid) {
        this.categoryid = categoryid;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCompletestatus() {
        return completestatus;
    }

    public void setCompletestatus(String completestatus) {
        this.completestatus = completestatus;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String displayname) {
        this.displayname = displayname;
    }

    public String getPartnername() {
        return partnername;
    }

    public void setPartnername(String partnername) {
        this.partnername = partnername;
    }

    public BigDecimal getSaleprice() {
        return saleprice;
    }

    public void setSaleprice(BigDecimal saleprice) {
        this.saleprice = saleprice;
    }

    public Integer getStartchargechapter() {
        return startchargechapter;
    }

    public void setStartchargechapter(Integer startchargechapter) {
        this.startchargechapter = startchargechapter;
    }

    public Integer getWordcount() {
        return wordcount;
    }

    public void setWordcount(Integer wordcount) {
        this.wordcount = wordcount;
    }

    public BigDecimal getK1price() {
        return k1price;
    }

    public void setK1price(BigDecimal k1price) {
        this.k1price = k1price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(Date launchTime) {
        this.launchTime = launchTime;
    }

    public String getBillpattern() {
        return billpattern;
    }

    public void setBillpattern(String billpattern) {
        this.billpattern = billpattern;
    }

    public String getCopyrightid() {
        return copyrightid;
    }

    public void setCopyrightid(String copyrightid) {
        this.copyrightid = copyrightid;
    }

    public String getCopyrightname() {
        return copyrightname;
    }

    public void setCopyrightname(String copyrightname) {
        this.copyrightname = copyrightname;
    }

    public Long getCooeeid() {
        return cooeeid;
    }

    public void setCooeeid(Long cooeeid) {
        this.cooeeid = cooeeid;
    }

    public Integer getChaptercount() {
        return chaptercount;
    }

    public void setChaptercount(Integer chaptercount) {
        this.chaptercount = chaptercount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getDatachangelastTime() {
        return datachangelastTime;
    }

    public void setDatachangelastTime(Date datachangelastTime) {
        this.datachangelastTime = datachangelastTime;
    }

    public String getBookstatus() {
        return bookstatus;
    }

    public void setBookstatus(String bookstatus) {
        this.bookstatus = bookstatus;
    }

    public static class Short{
        private Long id;
        private String name;
        private String author;


        public Short(){}
        public Short(EsBook2 b){
            this.id=b.id;
            this.name=b.name;
            this.author=b.author;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }
    }

    public static class Scored extends Short{
        private Double score;

        public Scored(){}
        public Scored(Short s,Double score){
            this.setId(s.getId());
            this.setName(s.getName());
            this.setAuthor(s.getAuthor());
            this.score=score;
        }

        public Double getScore() {
            return score;
        }

        public void setScore(Double score) {
            this.score = score;
        }
    }

}
