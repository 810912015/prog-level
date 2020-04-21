package com.pl.data.common.api;

import cn.hutool.json.JSONUtil;

public class Caller {
    public static <Q,P> P byPostJson(String url, Q q, Class pt){
        String qs = JSONUtil.toJsonStr(q);
        String r = HttpUtil.postJson(url, qs);
        P p=(P)JSONUtil.toBean(r,pt);
        return p;
    }
}
