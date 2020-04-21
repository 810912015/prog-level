package com.pl.search.repository;

import com.pl.search.domain.EsBook;
import com.pl.search.domain.EsBook2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 商品ES操作类
 * Created by macro on 2018/6/19.
 */
public interface Book2Repository extends ElasticsearchRepository<EsBook2, Long> {
}
