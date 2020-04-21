package com.pl.search.service.impl;

import cn.hutool.json.JSONUtil;

import com.pl.data.redis.RedisService;
import com.pl.search.component.SearchIndex;
import com.pl.search.dao.EsBookDao;
import com.pl.search.domain.EsBook;
import com.pl.search.domain.EsBook2;
import com.pl.search.repository.Book2Repository;
import com.pl.search.repository.BookRepository;
import com.pl.search.service.EsBookService;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;


/**
 * 搜索管理Service实现类
 */
@Service
public class EsBookServiceImpl implements EsBookService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsBookServiceImpl.class);

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private Book2Repository book2Repository;
    @Override
    public Integer importAll() {
        return null;
    }

    @Override
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public EsBook create(Long id) {
        return null;
    }
    @Override
    public void updateScore(){
//        CompletableFuture.runAsync(()->{
//            Map<?,?> m=redisService.getRedis().opsForHash().entries(Msg.Types.BOOK_SCORE);
//            for(Map.Entry<?,?> e:m.entrySet()){
//                Score.RedisScore rs= JSONUtil.toBean(e.getValue().toString(), Score.RedisScore.class);
//                Long bid=Long.parseLong(e.getKey().toString());
//                int r=bookMapper.updateScore(bid,rs.getScore());
//                if(r>0){
//                    create(bid);
//                }
//            }
//        });
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsBook> books = new ArrayList<>();
            for (Long id : ids) {
                EsBook esProduct = new EsBook();
                esProduct.setId(id);
                books.add(esProduct);
            }
            bookRepository.deleteAll(books);
        }
    }
    @Override
    public Page<EsBook> searchSimple(String keyword, Integer pageNum, Integer pageSize) {
        return doSearch(keyword,pageNum,pageSize);
    }

    @Override
    public List<EsBook> byIds(List<Long> idl) {
        Iterable<EsBook> il= bookRepository.search(QueryBuilders.termsQuery("id",idl));
        List<EsBook> rl=new ArrayList<>();
        for(EsBook t:il){
            rl.add(t);
        }
        return rl;
    }

    @Override
    public Page<EsBook> search(String keyword, Integer pageNum, Integer pageSize) {
        return doSearch(keyword, pageNum, pageSize);
    }

    @Override
    public Integer importAll2() {
        return null;
    }


    @Override
    public Page<EsBook2> search(KeyCatArgs prm) {
        KeyCatQueryMaker m=new KeyCatQueryMaker(prm);
        NativeSearchQuery searchQuery=m.make().build();
        LOGGER.debug("DSL:{}", searchQuery.getQuery().toString());
        Page<EsBook2> r=book2Repository.search(searchQuery);
        return r;
    }

    private Page<EsBook> doSearch(String k, Integer pageNum, Integer pageSize) {
        FunctionScoreQueryBuilder functionScoreQueryBuilder =queryMaker.makeQuery(k);
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        builder.withQuery(functionScoreQueryBuilder);
        builder.withSort(SortBuilders.scoreSort().order(SortOrder.DESC));
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        builder.withPageable(pageable);
        NativeSearchQuery searchQuery = builder.build();
        LOGGER.debug("DSL:{}", searchQuery.getQuery().toString());
        Page<EsBook> r= bookRepository.search(searchQuery);
        recordSearch(k,r);
        return r;
    }

    @Override
    public Page<EsBook> byField(String kw, Integer pageNum, Integer pageSize) {
       return null;

    }
    @Override
    public Page<EsBook> recommend(Long id, Integer pageNum, Integer pageSize) {
        return null;

    }

    private void recordSearch(String kw,Page<EsBook> r){

    }

    private RedisService redisService;



    @Autowired
    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }


    @Autowired
    private QueryMaker queryMaker;
}
