package com.pl.portal.dto;


import com.pl.data.model.Firm;
import com.pl.data.model.FirmExample;
import org.springframework.util.StringUtils;

import java.util.Date;

public class FirmDto {
    public static class FirmParam{
        private String name;
        private String title;

        public FirmExample toExample(){
            FirmExample fe=new FirmExample();
            if(!StringUtils.isEmpty(name)){
                fe.createCriteria().andNameLike("%"+this.name+"%");
            }
            if(!StringUtils.isEmpty(title)){
                fe.createCriteria().andTitleLike("%"+this.title+"%");
            }
            return fe;
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
    }

    private Integer id;
    private String name;
    private String title;
    private String remarks;

    public FirmDto() {
    }

    public Firm toFirm(){
        Firm f=new Firm();
        f.setId(this.id);
        f.setDclt(new Date());
        f.setName(this.name);
        f.setTitle(this.title);
        f.setRemarks(this.remarks);
        return f;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
