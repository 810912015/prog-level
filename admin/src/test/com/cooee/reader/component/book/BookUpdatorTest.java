package com.cooee.reader.component.book;

import com.cooee.reader.common.api.HttpUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;

class BookUpdatorTest {
    @Test
    void post() {
        Map<String,String> map=new HashMap<>();
        map.put("clientId","4");
        map.put("resType","json");
        map.put("sign","596f8ed30663897f8777e46806d520da");
        String url="http://api.res.ireader.com/api/v2/book/bookList";
        String r= HttpUtil.postNvp(url,map);
        assertNotNull(r);
    }

    @Test
    void join(){
        List<Integer> l=new ArrayList<>();
        for(int i=0;i<10;i++) l.add(i);
        String s=l.toString();
        assertNotNull(s);
    }

    @Test
    void md5(){
        String s="131c7f0e54cab4c35cf8e95ac32748ce5dd1027686131432";
        String r= HttpUtil.md5(s);
        assertEquals("001fa9f1cf462befa672ad1d95d2480c",r);
    }

}