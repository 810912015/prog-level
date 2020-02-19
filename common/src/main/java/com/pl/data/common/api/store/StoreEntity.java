package com.pl.data.common.api.store;

import java.util.List;

public class StoreEntity {


    public static class ContentList{
        private String title;
        private String subTitle;
        private Integer index;
        private String styleName;
        private Boolean couldChange;
        private Boolean loadByScroll;
        private Integer lines;
        private List<ContentItem> books;

        public Boolean getCouldChange() {
            return couldChange;
        }

        public void setCouldChange(Boolean couldChange) {
            this.couldChange = couldChange;
        }

        public Boolean getLoadByScroll() {
            return loadByScroll;
        }

        public void setLoadByScroll(Boolean loadByScroll) {
            this.loadByScroll = loadByScroll;
        }

        public Integer getLines() {
            return lines;
        }

        public void setLines(Integer lines) {
            this.lines = lines;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSubTitle() {
            return subTitle;
        }

        public void setSubTitle(String subTitle) {
            this.subTitle = subTitle;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public String getStyleName() {
            return styleName;
        }

        public void setStyleName(String styleName) {
            this.styleName = styleName;
        }

        public List<ContentItem> getBooks() {
            return books;
        }

        public void setBooks(List<ContentItem> books) {
            this.books = books;
        }
    }


    public static class ListPage{
        private String name;
        private String items;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getItems() {
            return items;
        }

        public void setItems(String items) {
            this.items = items;
        }
    }

    public static class ContentItem{
        private Long cooeeId;
        private Long bookId;
        private String name;
        private String cover;
        private String author;
        private String brief;
        private String tag;
        private String score;

        public Long getCooeeId() {
            return cooeeId;
        }

        public void setCooeeId(Long cooeeId) {
            this.cooeeId = cooeeId;
        }

        public Long getBookId() {
            return bookId;
        }

        public void setBookId(Long bookId) {
            this.bookId = bookId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
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

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
            this.score = score;
        }
    }
}
