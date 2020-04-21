package com.pl.search.component;

import com.pl.search.domain.EsBook;
import com.pl.search.service.EsBookService;

import java.util.List;
import java.util.Set;

public interface SearchIndex {

    List<EsBook.Scored> hotBooks(int type);
    Set<String> hot(int type);
    List<EsBookService.HotKey> hot2(int type);
}
