package com.pl.admin.service;

import com.pl.admin.util.ArticleImporter;
import com.pl.data.common.api.CmdRunner;
import com.pl.data.common.api.CommonResult;
import com.pl.data.mapper.TArticleMapper;
import com.pl.data.model.TArticle;
import com.pl.data.model.TArticleExample;
import com.pl.data.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
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
    @Value("${pl.trans.cmd}")
    private String preCmd;

    private boolean existsByUrl(Link link){
        TArticleExample tae=new TArticleExample();
        tae.createCriteria().andNameEqualTo(link.getUrl());
        long c=articleMapper.countByExample(tae);
        return c>0;
    }

    @Override
    public CommonResult<List<TArticle>> translate(Link link) {
        if(existsByUrl(link)){
            return CommonResult.success(getArticle(link),"此文章已翻译");
        }

        Boolean exists=redisService.getRedis().opsForValue().setIfAbsent(link.runningKey(),"", Duration.ofHours(2));
        if(exists==null||!exists) {
            return CommonResult.success(getArticle(link),"此url正在翻译中");
        }
        String cmd=preCmd+link.toCmd();
        CommonResult<String> r=callTrans(cmd);
        return CommonResult.copy(r,getArticle(link));
    }
    private List<TArticle> getArticle(Link link){
        String rk=link.resultKey();
        List<String> rl=redisService.getRedis().opsForList().range(rk,0,100);
        if(rl==null||rl.isEmpty()) return new ArrayList<>();
        TArticleExample tae=new TArticleExample();
        tae.createCriteria().andIdIn(rl.stream().map(a->Integer.parseInt(a)).collect(Collectors.toList()));
        return articleMapper.selectByExample(tae);
    }

    private CommonResult<String> callTrans(String cmd){
        CmdRunner.runCmd(cmd);
        return CommonResult.success("");
    }
    @Override
    public CommonResult<List<TArticle>> query(Link link) {
        return CommonResult.success(getArticle(link));
    }

    @Override
    public CommonResult<String> done(Result result) {
        redisService.getRedis().delete(result.runningKey());
        List<TArticle> ra=ai.save(ai.fromStr(result.getJson()));
        if(ra==null||ra.isEmpty()) return CommonResult.success("");
        String rk=result.resultKey();
        redisService.getRedis()
                .opsForList().
                rightPushAll(rk,ra.stream().map(a->Integer.toString(a.getId())).collect(Collectors.toList()));
        redisService.expire(rk,24*60*60*1000);
        return CommonResult.success("");
    }


}
