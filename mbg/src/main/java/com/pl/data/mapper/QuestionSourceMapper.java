package com.pl.data.mapper;

import com.pl.data.model.QuestionSource;
import com.pl.data.model.QuestionSourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface QuestionSourceMapper {
    long countByExample(QuestionSourceExample example);

    int deleteByExample(QuestionSourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(QuestionSource record);

    int insertSelective(QuestionSource record);

    List<QuestionSource> selectByExampleWithBLOBs(QuestionSourceExample example);

    List<QuestionSource> selectByExample(QuestionSourceExample example);

    QuestionSource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") QuestionSource record, @Param("example") QuestionSourceExample example);

    int updateByExampleWithBLOBs(@Param("record") QuestionSource record, @Param("example") QuestionSourceExample example);

    int updateByExample(@Param("record") QuestionSource record, @Param("example") QuestionSourceExample example);

    int updateByPrimaryKeySelective(QuestionSource record);

    int updateByPrimaryKeyWithBLOBs(QuestionSource record);

    int updateByPrimaryKey(QuestionSource record);
}