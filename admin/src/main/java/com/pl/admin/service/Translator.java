package com.pl.admin.service;

import cn.hutool.json.JSONUtil;
import com.pl.admin.util.ArticleImporter;
import com.pl.data.common.api.CommonResult;
import com.pl.data.common.api.HttpUtil;
import com.pl.data.mapper.TArticleMapper;
import com.pl.data.model.TArticle;
import com.pl.data.model.TArticleExample;
import com.pl.data.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class Translator implements ITranslator {
    static Logger logger= LoggerFactory.getLogger(Translator.class);

    @Autowired
    private RedisService redisService;
    @Autowired
    private TArticleMapper articleMapper;
    @Autowired
    private ArticleImporter ai;
    @Value("${pl.trans.ip}")
    private String ip;

    @Autowired
    QProvider qProvider;

    private boolean existsByUrl(Link link){
        TArticleExample tae=new TArticleExample();
        tae.createCriteria().andNameEqualTo(link.getUrl());
        long c=articleMapper.countByExample(tae);
        return c>0;
    }

    /**
     * 保证任何时候只有给定数量的翻译在进行，多余的进入队列，当队列变化时执行可能的翻译操作
     */
    @Component
    public static class QProvider{
        @Autowired
        private RedisService redisService;
        private static final String KEY="t_q";
        private static final int FULL=3;
        private boolean shouldCallNow(){
            Long r=redisService.getRedis().opsForHash().size(KEY);
            return r==null||r<FULL;
        }

        public boolean exist(String key){
            return redisService.getRedis().opsForHash().hasKey(KEY,key);
        }

        public void add(Link link,Function<Link,Boolean> f){
            if(exist(link.runningKey())) return;
            redisService.getRedis().opsForHash().put(KEY,link.runningKey(),JSONUtil.toJsonStr(link));
            onChange(f);
        }
        public void remove(String key,Function<Link,Boolean> f){
            redisService.getRedis().opsForHash().delete(KEY,key);
            onChange(f);
        }

        private void onChange(Function<Link,Boolean> f){
            if(f==null) return;
            if(!shouldCallNow()) return;
            Set<Object> r = redisService.getRedis().opsForHash().keys(KEY);
            if (r == null || r.isEmpty()) return;
            for(Object o:r){
                if(o==null) continue;
                Object or=redisService.getRedis().opsForHash().get(KEY,o);
                Link l=JSONUtil.toBean(or.toString(),Link.class);
                if(f.apply(l)){
                    break;
                }
            }
        }
    }

    public boolean callOne(Link link1){
        Boolean exists=redisService.getRedis().opsForValue().setIfAbsent(link1.runningKey(),
                Long.toString(new Date().getTime()),
                Duration.ofHours(4));
        if(exists) {
            CommonResult<String> r = callSvc("http://" + ip + ":7070/translate", new CmdPrm(link1, ip));
            if (!r.isSuccess()) {
                redisService.getRedis().delete(link1.runningKey());
                return false;
            }else{
                return true;
            }
        }
        return false;
    }

    @Override
    public CommonResult<List<TArticle>> translate(Link link) {
        if(existsByUrl(link)){
            return CommonResult.success(getArticle(link),"此文章已翻译");
        }
        if(qProvider.exist(link.runningKey())){
            return CommonResult.success(new ArrayList<>(),"此url正在翻译中");
        }

        qProvider.add(link,this::callOne);
        return CommonResult.success(new ArrayList<>());
    }

    @Override
    public CommonResult<String> clearLock(Link link) {
        String k=link.runningKey();
        qProvider.remove(k,this::callOne);
        String s=redisService.get(k);
        if(StringUtils.isEmpty(s)){
            return CommonResult.failed("此url无锁");
        }
        redisService.getRedis().delete(k);
        return CommonResult.success("已清除。加锁时间"+s);
    }

    @Override
    public CommonResult<String> queryLock(Link link) {
        String s=redisService.get(link.runningKey());
        return CommonResult.success(s);
    }

    private List<TArticle> getArticle(Link link){
        String rk=link.resultKey();
        List<String> rl=redisService.getRedis().opsForList().range(rk,0,100);
        if(rl==null||rl.isEmpty()) return new ArrayList<>();
        TArticleExample tae=new TArticleExample();
        tae.createCriteria().andIdIn(rl.stream().map(a->Integer.parseInt(a)).collect(Collectors.toList()));
        return articleMapper.selectByExample(tae);
    }

    /**
     * 调用翻译服务
     * @param url
     * @param prm
     * @return
     */
    private CommonResult<String> callSvc(String url,CmdPrm prm){
        try {
            String r = HttpUtil.postJson(url, JSONUtil.toJsonStr(prm));
            return CommonResult.success(r);
        }catch (Exception e){
            logger.error("",e);
            return CommonResult.failed(e.getMessage());
        }
    }


    @Override
    public CommonResult<List<TArticle>> query(Link link) {
        return CommonResult.success(getArticle(link));
    }

    @Override
    public CommonResult<String> done(Result result) {
        List<TArticle> ra=ai.save(ai.fromStr(result.getJson()));
        if(ra==null||ra.isEmpty()) return CommonResult.success("");
        String rk=result.resultKey();
        redisService.getRedis()
                .opsForList().
                rightPushAll(rk,ra.stream().map(a->Integer.toString(a.getId())).collect(Collectors.toList()));
        redisService.expire(rk,24*60*60*1000);
        return CommonResult.success("");
    }

    @Override
    public CommonResult<String> finish(Link link) {
        redisService.getRedis().delete(link.runningKey());
        qProvider.remove(link.runningKey(),this::callOne);
        return CommonResult.success("");
    }
}
