package com.pl.data.common.api.msg;

public interface OnReadReportMsg extends Msg {
    Integer getChapterId();
    String getSource();
    String KEY="on_read";
}
