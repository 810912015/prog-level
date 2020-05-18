package com.pl.portal.dao;

import com.pl.data.model.TArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleDao {
    @Select("select id,name,c_name as cName,source_url as sourceUrl,m_text as cHtml from t_article where id=#{id}")
    TArticle getMixById(@Param("id") Integer id);

    @Select("select id,name,c_name as cName,source_url as sourceUrl,c_html as cHtml from t_article where id=#{id}")
    TArticle getChById(@Param("id") Integer id);

    @Select("select id,name,c_name as cName,source_url as sourceUrl,text as cHtml from t_article where id=#{id}")
    TArticle getEnById(@Param("id") Integer id);

    @Select("select id,c_name as cName,auth from t_article where use_level>0 order by id desc limit 5")
    List<TArticle> getLatest5();

}
