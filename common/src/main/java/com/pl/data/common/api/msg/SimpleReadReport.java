package com.pl.data.common.api.msg;

public class SimpleReadReport extends BaseMsg implements OnReadReportMsg {

    private int chapterId;
    private String source;

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public Integer getChapterId() {
        return chapterId;
    }

    @Override
    public String getSource() {
        return source;
    }

    public static class Content{
        private Integer chapterId;
        private String source;

        public Integer getChapterId() {
            return chapterId;
        }

        public void setChapterId(Integer chapterId) {
            this.chapterId = chapterId;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSource() {
            return source;
        }
    }
}
