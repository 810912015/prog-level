package com.pl.admin.service;

import com.pl.admin.dto.Bound;
import com.pl.data.common.api.CommonResult;
import com.pl.data.mapper.TArticleMapper;
import com.pl.data.model.TArticle;
import com.pl.data.model.TArticleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.Function;

@Component
public class ArticleService  implements IArticleService{

    @Override
    public CommonResult save(TArticle art) {
        if(art==null||art.getId()==null) return CommonResult.failed("对象和id都不能为空");
        artConverter.convert(art);
        return CommonResult.success(
                articleMapper.updateByPrimaryKeySelective(art));
    }

    /**
     * 传回的内容中的cText字段是中英对照的翻译，即奇数行英文，偶数行中文，pre和img含标签
     * 格式：
     * 第一行 名称
     * 第二行 作者
     * 第三行 日期
     * 第四行如果以----开头 空格隔开的tag
     * 余下是正文
     */
    @Component
    public static class ArtConverter{
        public void convert(TArticle art){
            if(!StringUtils.isEmpty(art.getcText())) {
                split(art);
            }
        }

        public void split(TArticle art){
            StringBuilder ensb=new StringBuilder();
            StringBuilder chsb=new StringBuilder();
            StringBuilder msb=new StringBuilder();
            ensb.append("<div class='t-art-en'>");
            chsb.append("<div class='t-art-ch'>");
            msb.append("<div class='t-art-mix'>");
            String[] sa=art.getcText().split("\n");
            boolean code=false;
            int ln=0;
            for(String t:sa){
                boolean isCodeStart=t.startsWith("<code>")||t.startsWith("<pre>");
                boolean isCodeEnd=t.startsWith("</code")||t.startsWith("</pre>");

                if(isCodeStart){
                    //内嵌的代码块不计算在内
                    if(!code) {
                        code = true;
                    }
                }else if(isCodeEnd){
                    code=false;
                }
                if(code||isCodeEnd){
                    ensb.append(t);
                    chsb.append(t);
                    msb.append(t);
                }else {
                    boolean isEn = ln % 2 == 0;
                    String lang=isEn?"t-en":"t-ch";
                    StringBuilder sb = isEn ? ensb : chsb;

                    if (ln < 2) {
                        if (isEn) art.setName(t);
                        else art.setcName(t);
                        sb.append("<p class='t-name "+lang+"'>");
                        sb.append(t);
                        sb.append("</p>");
                    } else if (ln < 4) {
                        if (!isEn) {
                            art.setAuth(t);
                        }
                        sb.append("<p class='t-auth "+lang+"'>");
                        sb.append(t);
                        sb.append("</p>");
                    } else if (ln < 6) {
                        if (isEn) {

                        }
                        sb.append("<p class='t-pub "+lang+"'>");
                        sb.append(t);
                        sb.append("</p>");
                    } else if(ln<8){
                        if(t.startsWith("----")){
                            String tag=t.substring(4);
                            if(!isEn){
                                art.setTag(t.substring(4));
                            }
                            sb.append("<p class='t-tag "+lang+"'>");
                        }else{
                            sb.append("<p>");
                        }
                        sb.append(t);
                        sb.append("</p>");
                    }else{
                        sb.append("<p class='"+lang+"'>");
                        sb.append(t);
                        sb.append("</p>");
                    }
                    msb.append("<p class='"+lang+"'>");
                    msb.append(t);
                    msb.append("</p>");
                    ln++;
                }
            }
            String tail="</div>";
            ensb.append(tail);
            chsb.append(tail);
            msb.append(tail);
            art.setText(ensb.toString());
            art.setcHtml(chsb.toString());
            art.setmText(msb.toString());
        }
    }



    @Override
    public CommonResult del(Integer id) {
        if(id==null) return CommonResult.failed("id不能为空");
        return CommonResult.success(articleMapper.deleteByPrimaryKey(id));
    }

    @Override
    public CommonResult<TArticle> detail(Integer id) {
        return CommonResult.success(articleMapper.selectByPrimaryKey(id));
    }

    @Override
    public CommonResult<List<TArticle>> list(Bound bound) {
        TArticleExample ae=new TArticleExample();
        if(bound==null){
            bound=new Bound();
        }
        bound.normalize();
        bound.setSize(100);
        ae.handleQueryArgs(bound);
        return CommonResult.success(articleMapper.selectByExample(ae));
    }

    @Autowired
    private TArticleMapper articleMapper;
    @Autowired
    private ArtConverter artConverter;
}
