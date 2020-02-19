package com.pl.data.common.api.msg;

import java.util.Date;

public interface Msg {
    Date getSendTime();
    Long getBid();
    Long getUid();
    class Types{
        public static final String BOOK_SCORE ="reader.book.score";
        public static final String USER_REPORT="reader.user.report";
        public static final String SEARCH_BOOK="reader.search.book";
        public static final String ON_READ="reader.book.on.read";
        public static final String USER_ACTIVE="reader.user.active";
    }
}
