package com.pl.portal.dao;

import com.pl.data.model.TArticle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ArticleDao {
    @Select("select id,name,c_name as cName,source_url as sourceUrl,c_html as cHtml from t_article where id=#{id}")
    public TArticle getWithCtextById(@Param("id") Integer id);

}
