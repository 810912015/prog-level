package com.pl.portal.dto;

public class Pager {
    public static class Param{
        private int cur;
        private int size;

        public int getCur() {
            return cur;
        }

        public void setCur(int cur) {
            this.cur = cur;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int start(){
            int r=(cur-1)*size;
            if(r<0){
                r=0;
            }
            return r;
        }
    }

    public static class PagedQuery<T>{
        private T query;
        private Param pager;

        public T getQuery() {
            return query;
        }

        public void setQuery(T query) {
            this.query = query;
        }

        public Param getPager() {
            return pager;
        }

        public void setPager(Param pager) {
            this.pager = pager;
        }
    }

    public static class Paged<T>{
        private Pager pager;
        private T data;

        public Paged() {
        }

        public Paged(T data,Param p,int total) {
            this.data=data;
            this.pager=new Pager();
            this.pager.cur=p.cur;
            this.pager.size=p.size;
            this.pager.total=total;
        }

        public Pager getPager() {
            return pager;
        }

        public void setPager(Pager pager) {
            this.pager = pager;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }



    private int cur;
    private int size;
    private int total;

    public int getCur() {
        return cur;
    }

    public void setCur(int cur) {
        this.cur = cur;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
