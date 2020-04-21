package com.pl.portal.component.mq;

import cn.hutool.bloomfilter.BitSetBloomFilter;
import cn.hutool.bloomfilter.BloomFilter;
import cn.hutool.bloomfilter.BloomFilterUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.BitSet;

@Component
public class BloomUniquer implements Uniquer {
    private static Logger logger= LoggerFactory.getLogger(BloomUniquer.class);
    private BitSet bs;
    private BloomFilter filter;


    private int records;
    private int bits;

    public BloomUniquer(int records, int bits){
        this.records=records;
        this.bits=bits;
    }

    public BloomUniquer(){
        this(100000,2);
    }

    @Override
    public void setSource(byte[] source) {
        if(source==null){
            source=new byte[this.records*this.bits];
        }
        bs=BitSet.valueOf(source);
        filter=makeBloomFilter();
    }
    private BloomFilter makeBloomFilter(){
        try {
            BitSetBloomFilter bsbf = BloomFilterUtil.createBitSet(this.records*2, this.records, this.bits);
            Class<?> c = bsbf.getClass();
            Field f = c.getDeclaredField("bitSet");
            f.setAccessible(true);
            f.set(bsbf, this.bs);
            return bsbf;
        }catch (Exception e){
            logger.error("",e);
            return null;
        }
    }

    @Override
    public boolean contains(String value) {
        return filter.contains(value);
    }

    @Override
    public byte[] updateSource(String value) {
        filter.add(value);
        return bs.toByteArray();
    }
}
