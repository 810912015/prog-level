package com.pl.admin.dto;

/**
 * 查询边界:从某个id开始取size个
 */
public class Bound {
    private Long maxId;
    private Long minId;
    private Integer size;

    public Long getMaxId() {
        return maxId;
    }

    public void setMaxId(Long maxId) {
        this.maxId = maxId;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public static final int DEFAULT_SIZE=10;

    public void normalize(){
        if(this.maxId ==null){
            this.maxId =0L;
        }
        if(this.size==null){
            this.size=DEFAULT_SIZE;
        }
        if(this.minId==null){
            this.minId=Long.MAX_VALUE;
        }
    }

    public Long getMinId() {
        return minId;
    }

    public void setMinId(Long minId) {
        this.minId = minId;
    }
}
