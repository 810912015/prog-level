package com.pl.portal.component.mq;

/**
 * 分布式去重器
 */
public interface Uniquer {
    void setSource(byte[] source);
    boolean contains(String value);
    byte[] updateSource(String value);
}
