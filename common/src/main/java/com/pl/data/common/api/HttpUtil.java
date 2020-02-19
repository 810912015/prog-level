package com.pl.data.common.api;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class HttpUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    public static String postNvp(String url, Map<String, String> args) {
        HttpEntity he = null;
        if (null != args) {
            List<NameValuePair> nvps = new ArrayList<>();
            for (String name : args.keySet()) {
                nvps.add(new BasicNameValuePair(name, args.get(name)));
            }
            try {
                he = (new UrlEncodedFormEntity(nvps));
            } catch (Exception e) {
                logger.error("postNvp", e);
                return "";
            }
        }
        return post(url, he);
    }

    public static String postJson(String url, String json) {
        StringEntity s = null;
        try {
            s = new StringEntity(json);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        s.setContentEncoding("UTF-8");
        s.setContentType("application/json");
        return post(url, s);
    }

    private static String post(String url, HttpEntity entity) {
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpClientContext context = HttpClientContext.create();
            HttpPost httpPost = new HttpPost(url);
            if (null != entity) {
                httpPost.setEntity(entity);
            }

            CloseableHttpResponse response = httpClient.execute(httpPost, context);
            response.setLocale(Locale.CHINESE);
            String html = EntityUtils.toString(response.getEntity(), "UTF-8");
            response.close();
            logger.info(url + "\n" + html);
            return html;

        } catch (Exception e) {
            logger.error("postNvp", e);
            return "";
        }
    }

    public static String md5(String plainText) {
        return encrypt(plainText, Alg.MD5);
    }

    public static String sha1(String s) {
        return encrypt(s, Alg.SHA1);
    }

    public static class Alg {
        private Alg() {
        }

        public static final String MD5 = "MD5";
        public static final String SHA1 = "SHA1";
    }

    private static String encrypt(String plainText, String alg) {
        byte[] secretBytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance(alg);
            md.update(plainText.getBytes());
            secretBytes = md.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("没有这个算法" + alg);
        }
        String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
        int delta = 32 - md5code.length();
        for (int i = 0; i < delta; i++) {
            md5code = "0" + md5code;
        }
        return md5code;
    }
}
