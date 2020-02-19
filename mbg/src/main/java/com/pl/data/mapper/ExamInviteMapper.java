package com.pl.data.mapper;

import com.pl.data.model.ExamInvite;
import com.pl.data.model.ExamInviteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ExamInviteMapper {
    long countByExample(ExamInviteExample example);

    int deleteByExample(ExamInviteExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ExamInvite record);

    int insertSelective(ExamInvite record);

    List<ExamInvite> selectByExample(ExamInviteExample example);

    ExamInvite selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ExamInvite record, @Param("example") ExamInviteExample example);

    int updateByExample(@Param("record") ExamInvite record, @Param("example") ExamInviteExample example);

    int updateByPrimaryKeySelective(ExamInvite record);

    int updateByPrimaryKey(ExamInvite record);
}