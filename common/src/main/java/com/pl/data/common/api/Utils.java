package com.pl.data.common.api;

import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

public class Utils {
    public static <T> String toIn(Collection<T> list, Function<T, Object> f){
        return join(list,",",f);
    }

    public static <T> String join(Collection<T> list, String splitter, Function<T, Object> f){
        if(list==null||splitter==null) return "";
        StringBuilder sb=new StringBuilder();
        for(T t:list){
            if(sb.length()>0){
                sb.append(splitter);
            }
            Object v=f.apply(t);
            sb.append(v);
        }
        return sb.toString();
    }



    public static Integer dateToInt(Date d){
        if(d==null) return null;
        return Integer.parseInt(Utils.dateToLocal(d).format(DateTimeFormatter.ofPattern("yyyyMMdd")));
    }
    public static LocalDate dateToLocal(Date d){
        Instant instant=d.toInstant();
        return instant.atZone(ZoneId.systemDefault()).toLocalDate();
    }
    public static Date localToDate(LocalDate ld){
        return Date.from(ld.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    public static <T> T[] makeArray(Class<T> ct, Consumer<List<T>> c){
        List<T> l=new ArrayList<>();
        c.accept(l);
        T[] r= (T[]) Array.newInstance(ct,l.size());
        l.toArray(r);
        return r;
    }
    /**
     * 获取给定日期的加上偏移的时间范围.如果给定日期为空,则返回当前日期
     * 例如,给定2019-11-21 14:19 ,1,则返回2019-11-21 00:00:00,2019-11-22 00:00:00
     * @param d 给定的日期
     * @param delta 偏移
     * @return 数组,当日的开始结束时间表示,0-开始,1-结束
     */
    public static Date[] dayRange(Date d, int delta){
        if(d==null) d=new Date();
        if(delta==0) delta=1;
        LocalDate t=dateToLocal(d);
        LocalDate t1=t.plusDays(delta);
        if(delta>0) {
            return new Date[]{localToDate(t), localToDate(t1)};
        }else{
            return new Date[]{localToDate(t1), localToDate(t)};
        }
    }

    /**
     * 和指定日期偏移天数后的1天的时间范围
     * @param d 指定日期
     * @param delta 偏移日期
     * @return
     */
    public static Date[] dayOffsetRange(Date d, int delta){
        if(d==null) d=new Date();
        LocalDate t0=dateToLocal(d);
        LocalDate t=t0.plusDays(delta);
        LocalDate t1=t.plusDays(1);
        return new Date[]{localToDate(t),localToDate(t1)};
    }

    public static void noError(Runnable r){
        try{
            r.run();
        }
        catch (Throwable t){
            LoggerFactory.getLogger(Utils.class).error("",t);
        }
    }

    public static Map<String, String> toMap(String kw){
        if(StringUtils.isEmpty(kw)) return null;
        Map<String, String> m=new HashMap<>();
        String[] sa=kw.split(",");
        for(String s:sa){
            String[] a=s.split(":");
            if(a.length>1){
                if(!StringUtils.isEmpty(a[0])&&!StringUtils.isEmpty(a[1])){
                    m.put(a[0],a[1]);
                }
            }
        }
        return m;
    }

    public static <T> List<T> parseCsv(String file, Function<String[],T> makeOne){
        return parseCsv(file,";",makeOne);
    }

    public static <T> List<T> parseCsv(String file, String delemeter, Function<String[],T> makeOne){
        List<String> ds= null;
        try {
            ds = Files.readAllLines(Paths.get(file),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(ds==null) return new ArrayList<>();
        List<T> r=new ArrayList<>();
        for(String t:ds) {
            String[] ta = t.split(delemeter);
            T tr=makeOne.apply(ta);
            if(tr!=null){
                r.add(tr);
            }
        }
        return r;
    }
}
