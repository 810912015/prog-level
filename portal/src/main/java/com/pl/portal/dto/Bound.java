package com.pl.portal.dto;

import com.pl.data.common.api.IQueryArgs;

/**
 * 查询边界:从某个id开始取size个,使用id过滤
 */
public class Bound implements IQueryArgs {
    private Long maxId;
    private Long minId;
    private Integer size;

    @Override
    public String toString() {
        return "Bound{" +
                "maxId=" + maxId +
                ", minId=" + minId +
                ", size=" + size +
                ", forward=" + forward +
                '}';
    }

    /**
     * true:大于maxId
     * false:小于minId
     */
    private Boolean forward;

    public Long getMaxId() {
        return maxId;
    }

    public void setMaxId(Long maxId) {
        this.maxId = maxId;
    }

    @Override
    public String toSql() {
        if(forward){
            long i=maxId==null?0L:maxId;
            return "id>"+i;
        }else{
            long i=minId==null?Long.MAX_VALUE:minId;
            return "id<"+i;
        }
    }

    public Integer getSize() {
        return size;
    }

    @Override
    public Integer makeStart() {
        return null;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public static final int DEFAULT_SIZE=10;

    public Bound normalize(){
        if(this.maxId ==null){
            this.maxId =0L;
        }
        if(this.size==null||this.size==0){
            this.size=DEFAULT_SIZE;
        }
        if(this.minId==null){
            this.minId=Long.MAX_VALUE;
        }
        if(this.forward==null){
            this.forward=false;
        }
        return this;
    }

    public Long getMinId() {
        return minId;
    }

    public void setMinId(Long minId) {
        this.minId = minId;
    }

    public Boolean getForward() {
        return forward;
    }

    public void setForward(Boolean forward) {
        this.forward = forward;
    }
}
