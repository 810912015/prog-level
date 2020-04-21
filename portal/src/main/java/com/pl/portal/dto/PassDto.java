package com.pl.portal.dto;



import com.pl.data.model.Question;

import java.util.List;

public class PassDto {
    private Question q;
    private List<QsDto> s;

    public PassDto() {
    }
    public PassDto(Question q,List<QsDto> s) {
        this.q=q;this.s=s;
    }
    public Question getQ() {
        return q;
    }

    public void setQ(Question q) {
        this.q = q;
    }

    public List<QsDto> getS() {
        return s;
    }

    public void setS(List<QsDto> s) {
        this.s = s;
    }
}
