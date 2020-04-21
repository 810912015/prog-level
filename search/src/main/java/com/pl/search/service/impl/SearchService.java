package com.pl.search.service.impl;


import com.pl.search.domain.Qdto;
import com.pl.search.repository.QuestionRepo;
import com.pl.search.service.ISearch;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService implements ISearch {
    private static final Logger logger= LoggerFactory.getLogger(SearchService.class);
    @Autowired
    private QuestionRepo repo;

    @Override
    public Page<Qdto> search(String text, Integer page, Integer size) {
        FunctionScoreQueryBuilder functionScoreQueryBuilder =queryMaker.makeQuery(text);
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(functionScoreQueryBuilder);
        builder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        Pageable pageable = PageRequest.of(page, size);
        builder.withPageable(pageable);
        NativeSearchQuery searchQuery = builder.build();
        logger.debug("DSL:{}", searchQuery.getQuery().toString());
        Page<Qdto> r= repo.search(searchQuery);
        return r;
    }

    @Autowired
    QueryMaker queryMaker;
}
