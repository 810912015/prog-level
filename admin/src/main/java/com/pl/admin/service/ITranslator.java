package com.pl.admin.service;

import com.pl.admin.dto.Result;
import com.pl.data.common.api.CommonResult;
import com.pl.data.model.TArticle;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface ITranslator {
    class Link{
        private String url;
        /**
         * s-聚合链接，c-内容链接
         */
        private String type;
        private String linkerContainerPattern;

        public String toCmd(){
            if(!StringUtils.isEmpty(linkerContainerPattern)) {
                return String.format("node betterdev.js -t -r -to=192.168.16.102 -%s %s %s", type, url, linkerContainerPattern);
            }else{
                return String.format("node betterdev.js -t -r -to=192.168.16.102 -%s %s", type, url);
            }
        }

        public CommonResult<String> valid(){
            if(StringUtils.isEmpty(url)) return CommonResult.failed("url不能为空");
            if(StringUtils.isEmpty(type)) return CommonResult.failed("类型不能为空");
            boolean r="c".equals(type)||"s".equals(type);
            if(!r) return CommonResult.failed("无法识别的类型");
            return CommonResult.success(null);
        }

        private String makeId(String key){
            return String.format("%s_%s_%s",key,url,
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        }
        public String runningKey(){
            return makeId("t_running_");
        }
        public String resultKey(){
            return makeId("t_result_");
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getLinkerContainerPattern() {
            return linkerContainerPattern;
        }

        public void setLinkerContainerPattern(String linkerContainerPattern) {
            this.linkerContainerPattern = linkerContainerPattern;
        }
    }

    class CmdPrm{
        private String url;
        private String type;
        private String to;
        private String pattern;

        public CmdPrm(){

        }
        public CmdPrm(Link l,String ip){
            this.url=l.url;
            this.type=l.type;
            this.pattern=l.linkerContainerPattern;
            this.to=ip;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getPattern() {
            return pattern;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }
    }

    class Result extends Link{
        private String json;

        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }
    }

    /**
     * 翻译
     * @param link 要翻译的链接
     * @return 翻译操作id
     */
    CommonResult<List<TArticle>> translate(Link link);

    /**
     * 查询翻译结果
     * @param link 要翻译的链接
     * @return 翻译文章结果
     */
    CommonResult<List<TArticle>> query(Link link);
    CommonResult<String> done(Result result);
}
