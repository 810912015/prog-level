package com.pl.data.mapper;

import com.pl.data.model.Pass;
import com.pl.data.model.PassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PassMapper {
    long countByExample(PassExample example);

    int deleteByExample(PassExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Pass record);

    int insertSelective(Pass record);

    List<Pass> selectByExampleWithBLOBs(PassExample example);

    List<Pass> selectByExample(PassExample example);

    Pass selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Pass record, @Param("example") PassExample example);

    int updateByExampleWithBLOBs(@Param("record") Pass record, @Param("example") PassExample example);

    int updateByExample(@Param("record") Pass record, @Param("example") PassExample example);

    int updateByPrimaryKeySelective(Pass record);

    int updateByPrimaryKeyWithBLOBs(Pass record);

    int updateByPrimaryKey(Pass record);
}