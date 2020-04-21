package com.pl.search.service;
import com.pl.data.common.api.Caller;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EsOperator {
    @Value("${spring.elasticsearch.rest.uris}")
    private String es;
    public List<String> analyze(String s){
        try {
            IQueryMaker.Aq q = new IQueryMaker.Aq();
            q.setAnalyzer("ik_max_word");
            q.setText(s);
            String url=es+"/pl-question/_analyze";
            IQueryMaker.Ar ar = Caller.byPostJson(url,q, IQueryMaker.Ar.class);
            List<String> tl=ar.getTokens().stream().map(a->a.getToken()).collect(Collectors.toList());
//            Collections.reverse(tl);
//            tl.sort((a1,a2)->a2.length()-a1.length());
            return tl;
        }catch (Exception e){
            LoggerFactory.getLogger(getClass()).error("analyze",e);
            return null;
        }
    }

    private static final String REINDEX_FMT="{\n" +
            "  \"source\": {\n" +
            "    \"index\": \"%s\"\n" +
            "  },\n" +
            "  \"dest\": {\n" +
            "    \"index\": \"%s\",\n" +
            "    \"version_type\": \"internal\"\n" +
            "  }\n" +
            "}";
    public void reindex(String indexName,String newIndexName){
        String s=String.format(REINDEX_FMT,indexName,newIndexName);
        //HttpUtil.postJson(es+"/_reindex",s);
    }

    public void refreshIndex(String name){
        try {
            String tmp = name + "1";
            reindex(name, tmp);
            reindex(tmp, name);
        }catch (Exception e){
            LoggerFactory.getLogger(getClass()).error("refreshIndex",e);
        }
    }
}
