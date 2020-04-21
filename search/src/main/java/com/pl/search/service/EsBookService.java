package com.pl.search.service;
import com.pl.search.domain.EsBook;
import com.pl.search.domain.EsBook2;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.elasticsearch.index.query.*;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品搜索管理Service
 * Created by macro on 2018/6/19.
 */
public interface EsBookService {
    /**
     * 从数据库中导入所有商品到ES
     */
    Integer importAll();


    /**
     * 根据id删除商品
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     */
    EsBook create(Long id);

    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<EsBook> search(String keyword, Integer pageNum, Integer pageSize);

    // 按实际列表搜索接口
    Integer importAll2();
    Page<EsBook2> search(KeyCatArgs prm);



    /**
     * 根据商品id推荐相关商品
     */
    Page<EsBook> recommend(Long id, Integer pageNum, Integer pageSize);


    Page<EsBook> searchSimple(String keyword, Integer pageNum, Integer pageSize);
    Page<EsBook> byField(String kw, Integer pageNum, Integer pageSize);
    List<EsBook> byIds(List<Long> idl);
    void updateScore();

    class HotKey{
        private Integer index;
        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }
    }

    class KeyCatQueryMaker{
        private KeyCatArgs args;

        public KeyCatQueryMaker(KeyCatArgs args){
            this.args=args;
        }
        public NativeSearchQueryBuilder make() {
            BoolQueryBuilder b = makeTerms();
            for (MatchPhraseQueryBuilder m : makeMatch()) {
                b.must(m);
            }
            NativeSearchQueryBuilder bm = new NativeSearchQueryBuilder();
            bm.withPageable(PageRequest.of(args.getPage(), args.getSize()));
            bm.withQuery(b);//functionScoreQueryBuilder);
            bm.withSort(makeSort());
            return bm;
        }
        private FieldSortBuilder makeSort(){
            FieldSortBuilder r= SortBuilders.fieldSort(args.getSort()).unmappedType("integer").order(SortOrder.DESC);

            return r;
        }
        private MatchPhraseQueryBuilder match(String field,String value){
            return QueryBuilders.matchPhraseQuery(field,
                    value).analyzer("ik_max_word");
        }

        private List<MatchPhraseQueryBuilder> makeMatch(){
            List<MatchPhraseQueryBuilder> f=new ArrayList<>();
            if(!StringUtils.isEmpty(args.getCat())) {
                f.add(match("category", args.getCat()));
            }
            if(!StringUtils.isEmpty(args.getCat2())) {
                f.add(match("category", args.getCat2()));
            }
            if(args.getKeywords()!=null) {
                for (String k : args.getKeywords()) {
                    f.add(match("keywords", k));
                }
            }
            return f;
        }
        private BoolQueryBuilder makeTerms(){
            BoolQueryBuilder r=QueryBuilders.boolQuery();
            if(args.getWords()!=null){
                Range t=args.getWords();
                if(t.getMax()!=null&&t.getMin()!=null){
                    r.must(QueryBuilders.rangeQuery("wordcount").gte(t.getMin()).lte(t.getMax()));
                }else if(t.getMax()!=null){
                    r.must(QueryBuilders.rangeQuery("wordcount").gte(t.getMax()));
                }else if(t.getMin()!=null){
                    r.must(QueryBuilders.rangeQuery("wordcount").lte(t.getMin()));
                }
            }
            if(!StringUtils.isEmpty(args.getCompleteness())){
                r.must(QueryBuilders.matchQuery("completestatus",args.getCompleteness()));
            }
            return r;
        }
    }

    class Range{
        private Integer min;
        private Integer max;

        public Integer getMin() {
            return min;
        }

        public void setMin(Integer min) {
            this.min = min;
        }

        public Integer getMax() {
            return max;
        }

        public void setMax(Integer max) {
            this.max = max;
        }
    }
    @ApiModel
    class KeyCatArgs{
        @ApiModelProperty("分类")
        private String cat;
        @ApiModelProperty("分类2")
        private String cat2;
        @ApiModelProperty("关键字")
        private List<String> keywords;
        @ApiModelProperty("连载:Y-完成,N-未完结,空表示全部")
        private String completeness;
        @ApiModelProperty("字数范围:max为空表示小于min,min为空表示大于max,都为空表示全部")
        private Range words;
        @ApiModelProperty("排序:人气(阅读次数)-favorCount,人气(阅读时间)-favorTime,字数-wordcount,搜索次数-searchcount")
        private String sort;
        private Integer page;
        private Integer size;



        public String getCat() {
            return cat;
        }

        public void setCat(String cat) {
            this.cat = cat;
        }


        public String getCompleteness() {
            return completeness;
        }

        public void setCompleteness(String completeness) {
            this.completeness = completeness;
        }

        public Range getWords() {
            return words;
        }

        public void setWords(Range words) {
            this.words = words;
        }

        public List<String> getKeywords() {
            return keywords;
        }

        public void setKeywords(List<String> keywords) {
            this.keywords = keywords;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public Integer getPage() {
            return page;
        }

        public void setPage(Integer page) {
            this.page = page;
        }

        public Integer getSize() {
            return size;
        }

        public void setSize(Integer size) {
            this.size = size;
        }

        public String getCat2() {
            return cat2;
        }

        public void setCat2(String cat2) {
            this.cat2 = cat2;
        }
    }
}
