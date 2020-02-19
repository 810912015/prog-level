package com.pl.admin.dao;

import com.pl.admin.dto.QuestionItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface QuestionItemMapper {
    @Select("select q.id,q.name,q.title,q.source,v.id as vid,v.input,v.output,v.weight,q.tag1,q.tag2,q.otherTag,q.level,q.userid,q.lang,q.Category\n" +
            "        from question q left join validation v on v.qid=q.id")
    List<QuestionItem> getAll();
    @Select("select q.id,q.name,q.title,q.source,v.id as vid,v.input,v.output,v.weight,q.tag1,q.tag2,q.otherTag,q.level,q.userid,q.lang,q.Category\n" +
            "        from question q left join validation v on v.qid=q.id where q.id=#{id}")
    List<QuestionItem> getById(int id);
    @Update(" update pass set stage='T' where groupId=#{gid}")
    int done(String gid);
}
