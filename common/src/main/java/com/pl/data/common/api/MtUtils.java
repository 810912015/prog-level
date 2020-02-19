package com.pl.data.common.api;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.BiConsumer;

public class MtUtils {
    private static Logger logger= LoggerFactory.getLogger(MtUtils.class);
    static ThreadFactory pullerThreadFactory = new ThreadFactoryBuilder().setNamePrefix("puller-").build();
    public static final ThreadPoolExecutor TPE =new ThreadPoolExecutor(10,10,5, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(200000),pullerThreadFactory,new ThreadPoolExecutor.DiscardPolicy());
    public static void split(final long start,int size, BiConsumer<Long,Long> caller){
        split(start,size,null,caller);
    }
    public static void split(final long start,int size,List<CompletableFuture> l, BiConsumer<Long,Long> caller){
        split(start,size,l,100,caller);
    }

    public static void async(Runnable r){
        CompletableFuture.runAsync(()->{
           try {
               r.run();
           }catch (Throwable e){
               logger.error("",e);
           }
        });
    }

    public static void split(final long start,int size,List<CompletableFuture> l,int batch, BiConsumer<Long,Long> caller){
        if(size<batch){
            CompletableFuture cf=CompletableFuture.runAsync(()->{
                logger.info(String.format("批次开始:%d,%d",start,size));
                try{
                caller.accept(start,start+size);
                }catch (Throwable ex){
                    logger.error("",ex);
                }
                logger.info(String.format("批次结束:%d,%d",start,size));
            }, TPE);
            if(l!=null){
                l.add(cf);
            }
        }else{
            int b=(size%batch==0)?(size/batch):(size/batch+1);
            for(int i=0;i<b;i++){
                final long s=start+i*batch;
                long e=start+(i+1)*batch;
                if(e>start+size){
                    e=start+size;
                }
                final long e1=e;
                final int i1=i;
                CompletableFuture cf= CompletableFuture.runAsync(()->{
                    logger.info(String.format("批次开始:%d,%d,%d->%d,%d",start,size,i1,s,e1));
                    try {
                        caller.accept(s, e1);
                    }catch (Throwable ex){
                        logger.error("",ex);
                    }
                    logger.info(String.format("批次结束:%d,%d,%d->%d,%d",start,size,i1,s,e1));
                }, TPE);
                if(l!=null){
                    l.add(cf);
                }
            }
        }

    }
    public static  <T> List<List<T>> split(List<T> s, int size){
        List<List<T>> r=new ArrayList<>();
        if(s.size()<=size){
            return Arrays.asList(s);
        }
        int b=s.size()%size==0? s.size()/size:s.size()/size+1;
        for(int i=0;i<b;i++){
            int start=i*size;
            int end=size*(i+1);
            if(end>=s.size()){
                end=size;
            }
            List<T> t=new ArrayList<>();
            for(int j=start;j<end;j++){
                t.add(s.get(j));
            }
            r.add(t);
        }
        return r;
    }
}
