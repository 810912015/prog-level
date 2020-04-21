package com.pl.search.service.impl;


import com.pl.data.common.api.Utils;
import com.pl.search.service.EsOperator;
import com.pl.search.service.IQueryMaker;
import org.elasticsearch.common.lucene.search.function.FunctionScoreQuery;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class QueryMaker implements IQueryMaker {
    static final Map<String,Integer> score=new HashMap<>();
    static {
        score.put("name",128);
        score.put("title",64);
    }
    FunctionScoreQueryBuilder.FilterFunctionBuilder mkPhaseBuilder(String field, String keyword, int factor){
        int s=score.get(field)*factor;
        return new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchPhraseQuery(field, keyword).analyzer("ik_max_word"),
                ScoreFunctionBuilders.weightFactorFunction(s));
    }
    FunctionScoreQueryBuilder.FilterFunctionBuilder mkMatchBuilder(String field,String keyword,int factor){
        String f=field;
        int s=score.get(f)*factor;
        return new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.matchQuery(field, keyword).analyzer("ik_max_word"),
                ScoreFunctionBuilders.weightFactorFunction(s));
    }
    private void makeQuery(List<String> sl, List<FunctionScoreQueryBuilder.FilterFunctionBuilder> l,String k) {
        int t=sl.size();
        if(t>10){
            t=10;
        }
        l.add(mkPhaseBuilder("name",k,t*10));
        l.add(mkPhaseBuilder("title",k,t*10));
        for(int i=0;i<t;i++) {
            String keyword=sl.get(i);
            int factor=t-i;
            l.add(mkMatchBuilder("name",keyword,factor));
            l.add(mkMatchBuilder("title",keyword,factor));
        }
    }

    public FunctionScoreQueryBuilder makeQuery(String k){
        List<String> sl=eso.analyze(k);
        FunctionScoreQueryBuilder.FilterFunctionBuilder[] fa= Utils.makeArray(FunctionScoreQueryBuilder.FilterFunctionBuilder.class,(l)->{
            List<String> tl=sl;
            if(tl==null||tl.isEmpty()){
                tl= Arrays.asList(k);
            }
            makeQuery(tl, l,k);
            Long id=null;
            try{
                id=Long.parseLong(k);
            }catch (Exception e){

            }
            if(id!=null) {
                l.add( new FunctionScoreQueryBuilder.FilterFunctionBuilder(QueryBuilders.termQuery("id", id),
                        ScoreFunctionBuilders.weightFactorFunction(8)));
            }
        });

        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery(fa)
                .scoreMode(FunctionScoreQuery.ScoreMode.SUM)
                .setMinScore(2);
        return functionScoreQueryBuilder;
    }

    @Autowired
    private EsOperator eso;
}
