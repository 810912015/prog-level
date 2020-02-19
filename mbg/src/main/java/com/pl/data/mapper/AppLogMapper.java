package com.pl.data.mapper;

import com.pl.data.model.AppLog;
import com.pl.data.model.AppLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppLogMapper {
    long countByExample(AppLogExample example);

    int deleteByExample(AppLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppLog record);

    int insertSelective(AppLog record);

    List<AppLog> selectByExampleWithBLOBs(AppLogExample example);

    List<AppLog> selectByExample(AppLogExample example);

    AppLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppLog record, @Param("example") AppLogExample example);

    int updateByExampleWithBLOBs(@Param("record") AppLog record, @Param("example") AppLogExample example);

    int updateByExample(@Param("record") AppLog record, @Param("example") AppLogExample example);

    int updateByPrimaryKeySelective(AppLog record);

    int updateByPrimaryKeyWithBLOBs(AppLog record);

    int updateByPrimaryKey(AppLog record);
}