package com.pl.data.mapper;

import com.pl.data.model.ExamQuestion;
import com.pl.data.model.ExamQuestionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamQuestionMapper {
    long countByExample(ExamQuestionExample example);

    int deleteByExample(ExamQuestionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ExamQuestion record);

    int insertSelective(ExamQuestion record);

    List<ExamQuestion> selectByExample(ExamQuestionExample example);

    ExamQuestion selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ExamQuestion record, @Param("example") ExamQuestionExample example);

    int updateByExample(@Param("record") ExamQuestion record, @Param("example") ExamQuestionExample example);

    int updateByPrimaryKeySelective(ExamQuestion record);

    int updateByPrimaryKey(ExamQuestion record);
}