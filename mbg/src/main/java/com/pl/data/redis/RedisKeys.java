package com.pl.data.redis;

public class RedisKeys {
    private RedisKeys(){}
    public static final String HOT_KEY="key_hot";
    public static final String HOT_BOOK_KEY="hot_book";

    public static final String KEY_STORE_CONTENT="KEY_STORE_CONTENT";
    public static final String KEY_STORE_TABS="KEY_STORE_TABS";

    public static final String BOOK_READ_COUNT="book_read_count";
    public static final String USER_READ_COUNT="user_read_count";
    public static final String BLOOM_FILTER_BOOK_READ="b_f_book_read";
    public static final String BLOOM_FILTER_USER_READ="b_f_user_read";
}
