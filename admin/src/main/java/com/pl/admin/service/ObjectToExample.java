package com.pl.admin.service;

import org.springframework.util.StringUtils;

/**
 * 查询对象到mybatis example的变换器
 */
public class ObjectToExample {
    static String toLike(Object pv){
        return "%"+pv+"%";
    }

    /**
     * 所有字段使用like操作
     * @param o 查询对象
     * @param e example的criteria对象
     * @param fl 要转换的字段名
     */
    public static void toLike(Object o,Object e,String[] fl)  {
        try {
            for (String ns : fl) {
                Object v = o.getClass().getMethod("get"+ns).invoke(o);
                if(v==null|| StringUtils.isEmpty(v.toString())){
                    continue;
                }
                String mn=String.format("and%sLike", ns);
                String nv=toLike(v);
                e.getClass().getMethod(mn,String.class).invoke(e,nv);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
