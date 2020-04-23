package com.pl.admin.p3;

import cn.hutool.json.JSONUtil;
import com.alibaba.druid.support.json.JSONUtils;
import springfox.documentation.spring.web.json.Json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransApi {

    private static final String TRANS_API_HOST = "http://api.fanyi.baidu.com/api/trans/vip/translate";

    private String appid;
    private String securityKey;

    public TransApi(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public TransResult translate(String query, String from, String to){
        String r=getTransResult(query,from,to);
        String r1=org.apache.commons.lang.StringEscapeUtils.unescapeJava(r);
        TransResult o= JSONUtil.toBean(r1,TransResult.class);
        return o;
    }

    public String getTransResult(String query, String from, String to) {
        Map<String, String> params = buildParams(query, from, to);
        return HttpGet.get(TRANS_API_HOST, params);
    }

    private Map<String, String> buildParams(String query, String from, String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);

        params.put("appid", appid);

        // 随机数
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);

        // 签名
        String src = appid + query + salt + securityKey; // 加密前的原文
        params.put("sign", MD5.md5(src));

        return params;
    }
    public static class TransResultItem{
        private String src;
        private String dst;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }
    }

    public static class TransResult{
        private String from;
        private String to;
        private List<TransResultItem> trans_result;
        private String error_code;
        private String error_msg;

        public String getError_code() {
            return error_code;
        }

        public void setError_code(String error_code) {
            this.error_code = error_code;
        }

        public String getError_msg() {
            return error_msg;
        }

        public void setError_msg(String error_msg) {
            this.error_msg = error_msg;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public List<TransResultItem> getTrans_result() {
            return trans_result;
        }

        public void setTrans_result(List<TransResultItem> trans_result) {
            this.trans_result = trans_result;
        }
    }
}
