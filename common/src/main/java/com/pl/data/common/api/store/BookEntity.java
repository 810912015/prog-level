package com.pl.data.common.api.store;

import java.util.List;

public class BookEntity {
    public static class Category{
        private String name;
        private List<String> subs;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getSubs() {
            return subs;
        }

        public void setSubs(List<String> subs) {
            this.subs = subs;
        }
    }
    public static class Err{
        private String errorCode;
        private String errorMsg;

        public String getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(String errorCode) {
            this.errorCode = errorCode;
        }

        public String getErrorMsg() {
            return errorMsg;
        }

        public void setErrorMsg(String errorMsg) {
            this.errorMsg = errorMsg;
        }
    }
    public static class Book {
        private String launchTime;

        public String getLaunchTime() {
            return this.launchTime;
        }

        public void setLaunchTime(String launchTime) {
            this.launchTime = launchTime;
        }

        private String partnerName;

        public String getPartnerName() {
            return this.partnerName;
        }

        public void setPartnerName(String partnerName) {
            this.partnerName = partnerName;
        }

        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private Integer bookId;

        public Integer getBookId() {
            return this.bookId;
        }

        public void setBookId(Integer bookId) {
            this.bookId = bookId;
        }
    }

    public static class BookDetailList{
        private List<BookInfo> data;
        private Page page;

        public List<BookInfo> getData() {
            return data;
        }

        public void setData(List<BookInfo> data) {
            this.data = data;
        }

        public Page getPage() {
            return page;
        }

        public void setPage(Page page) {
            this.page = page;
        }
    }
    public static class BookList {
        private List<Book> data;
        private Page page;

        public List<Book> getData() {
            return data;
        }

        public void setData(List<Book> data) {
            this.data = data;
        }

        public Page getPage() {
            return page;
        }

        public void setPage(Page page) {
            this.page = page;
        }
    }

    public static class BookInfo {
        private String brief;

        public String getBrief() {
            return this.brief;
        }

        public void setBrief(String brief) {
            this.brief = brief;
        }

        private Integer wordCount;

        public Integer getWordCount() {
            return this.wordCount;
        }

        public void setWordCount(Integer wordCount) {
            this.wordCount = wordCount;
        }

        private String keywords;

        public String getKeywords() {
            return this.keywords;
        }

        public void setKeywords(String keywords) {
            this.keywords = keywords;
        }

        private String partnerName;

        public String getPartnerName() {
            return this.partnerName;
        }

        public void setPartnerName(String partnerName) {
            this.partnerName = partnerName;
        }

        private String displayName;

        public String getDisplayName() {
            return this.displayName;
        }

        public void setDisplayName(String displayName) {
            this.displayName = displayName;
        }

        private String author;

        public String getAuthor() {
            return this.author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        private Integer startChargeChapter;

        public Integer getStartChargeChapter() {
            return this.startChargeChapter;
        }

        public void setStartChargeChapter(Integer startChargeChapter) {
            this.startChargeChapter = startChargeChapter;
        }

        private String updateTime;

        public String getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        private Integer bookId;

        public Integer getBookId() {
            return this.bookId;
        }

        public void setBookId(Integer bookId) {
            this.bookId = bookId;
        }

        private String cover;

        public String getCover() {
            return this.cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        private Integer billPattern;

        public Integer getBillPattern() {
            return this.billPattern;
        }

        public void setBillPattern(Integer billPattern) {
            this.billPattern = billPattern;
        }

        private String category_id;

        public String getCategory_id() {
            return this.category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        private String createTime;

        public String getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        private Float price;

        public Float getPrice() {
            return this.price;
        }

        public void setPrice(Float price) {
            this.price = price;
        }

        private String name;

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
        }

        private String completeStatus;

        public String getCompleteStatus() {
            return this.completeStatus;
        }

        public void setCompleteStatus(String completeStatus) {
            this.completeStatus = completeStatus;
        }

        private String category;

        public String getCategory() {
            return this.category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        private String categoryId;

        public String getCategoryId() {
            return this.categoryId;
        }

        public void setCategoryId(String categoryId) {
            this.categoryId = categoryId;
        }
    }

    public static class BookDetail extends BookInfo{
        private String chapter;
        private String update;
        private String reader;
        private String owner;
        private Integer words;
        private Integer comment;
        private Integer score;

        public String getChapter() {
            return chapter;
        }

        public void setChapter(String chapter) {
            this.chapter = chapter;
        }

        public String getUpdate() {
            return update;
        }

        public void setUpdate(String update) {
            this.update = update;
        }

        public String getReader() {
            return reader;
        }

        public void setReader(String reader) {
            this.reader = reader;
        }

        public String getOwner() {
            return owner;
        }

        public void setOwner(String owner) {
            this.owner = owner;
        }

        public Integer getWords() {
            return words;
        }

        public void setWords(Integer words) {
            this.words = words;
        }

        public Integer getComment() {
            return comment;
        }

        public void setComment(Integer comment) {
            this.comment = comment;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }
    }

    public static class Page {
        private Integer count;

        @Override
        public String toString() {
            return "Page{" +
                    "count=" + count +
                    ", pageSize=" + pageSize +
                    ", currentPage=" + currentPage +
                    '}';
        }

        public Integer getCount() {
            return this.count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        private Integer pageSize;

        public Integer getPageSize() {
            return this.pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        private Integer currentPage;

        public Integer getCurrentPage() {
            return this.currentPage;
        }

        public void setCurrentPage(Integer currentPage) {
            this.currentPage = currentPage;
        }

        public int total() {
            if (this.pageSize == 0) return 0;
            if (this.count % this.pageSize == 0) {
                return this.count / this.pageSize;
            } else {
                return this.count / this.pageSize + 1;
            }
        }
    }

    public static class ChapterList {
        public static class ShortChapter {
            private Integer chapterOrder;

            public Integer getChapterOrder() {
                return this.chapterOrder;
            }

            public void setChapterOrder(Integer chapterOrder) {
                this.chapterOrder = chapterOrder;
            }

            private String createTime;

            public String getCreateTime() {
                return this.createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            private Integer chapterId;

            public Integer getChapterId() {
                return this.chapterId;
            }

            public void setChapterId(Integer chapterId) {
                this.chapterId = chapterId;
            }

            private String updateTime;

            public String getUpdateTime() {
                return this.updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            private String title;

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            private Integer free;

            public Integer getFree() {
                return this.free;
            }

            public void setFree(Integer free) {
                this.free = free;
            }
        }

        public static class LongChapter extends ShortChapter{
            private boolean content;

            public int getSourceChapterId() {
                return sourceChapterId;
            }

            public void setSourceChapterId(int sourceChapterId) {
                this.sourceChapterId = sourceChapterId;
            }

            private int sourceChapterId;

            public boolean isContent() {
                return content;
            }

            public void setContent(boolean content) {
                this.content = content;
            }
        }

        private List<ShortChapter> data;

        public List<ShortChapter> getData() {
            return this.data;
        }

        public void setData(List<ShortChapter> data) {
            this.data = data;
        }

        private Page page;

        public Page getPage() {
            return this.page;
        }

        public void setPage(Page page) {
            this.page = page;
        }
    }

    public static class Chapter {
        private Integer chapterOrder;

        public Integer getChapterOrder() {
            return this.chapterOrder;
        }

        public void setChapterOrder(Integer chapterOrder) {
            this.chapterOrder = chapterOrder;
        }

        private String createTime;

        public String getCreateTime() {
            return this.createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        private Integer chapterId;

        public Integer getChapterId() {
            return this.chapterId;
        }

        public void setChapterId(Integer chapterId) {
            this.chapterId = chapterId;
        }

        private String updateTime;

        public String getUpdateTime() {
            return this.updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        private String title;

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        private String content;

        public String getContent() {
            return this.content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        private Integer bookId;

        public Integer getBookId() {
            return this.bookId;
        }

        public void setBookId(Integer bookId) {
            this.bookId = bookId;
        }
    }


}
