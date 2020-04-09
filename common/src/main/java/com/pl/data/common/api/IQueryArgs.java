package com.pl.data.common.api;

public interface IQueryArgs {
    String toSql();
    Integer getSize();
    Integer makeStart();
}
