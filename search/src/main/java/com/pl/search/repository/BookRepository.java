package com.pl.search.repository;


import com.pl.search.domain.EsBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 商品ES操作类
 * Created by macro on 2018/6/19.
 */
public interface BookRepository extends ElasticsearchRepository<EsBook, Long> {
    /**
     * 搜索查询
     *
     * @param name              商品名称
     * @param keywords          商品关键字
     * @param page              分页信息
     * @return
     */
    Page<EsBook> findByNameOrKeywords(String name,String keywords, Pageable page);

}
