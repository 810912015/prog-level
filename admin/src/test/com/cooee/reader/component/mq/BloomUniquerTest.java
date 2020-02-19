package com.cooee.reader.component.mq;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BloomUniquerTest {

    @Test
    void setSource() {
        BloomUniquer uniquer=new BloomUniquer();
        uniquer.setSource(null);
        boolean r=uniquer.contains("abc");
        assertFalse(r);
        byte[] s= uniquer.updateSource("abc");
        uniquer.setSource(s);
        r=uniquer.contains("abc");
        assertTrue(r);
    }

    @Test
    void contains() {
        BloomUniquer uniquer=new BloomUniquer();
        int count=100000;
        byte[] bs=null;
        List<String> sa=new ArrayList<>(count);
        for(int i=0;i<count;i++){
            String s= UUID.randomUUID().toString();
            sa.add(s);
            uniquer.setSource(bs);
            if(!uniquer.contains(s)){
                bs=uniquer.updateSource(s);
            }
        }
        for(int i=0;i<count;i++){
            boolean r=uniquer.contains(sa.get(i));
            assertTrue(r);
        }
    }

    @Test
    void updateSource() {
    }
}