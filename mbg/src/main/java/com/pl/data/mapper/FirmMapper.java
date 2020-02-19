package com.pl.data.mapper;

import com.pl.data.model.Firm;
import com.pl.data.model.FirmExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FirmMapper {
    long countByExample(FirmExample example);

    int deleteByExample(FirmExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Firm record);

    int insertSelective(Firm record);

    List<Firm> selectByExample(FirmExample example);

    Firm selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Firm record, @Param("example") FirmExample example);

    int updateByExample(@Param("record") Firm record, @Param("example") FirmExample example);

    int updateByPrimaryKeySelective(Firm record);

    int updateByPrimaryKey(Firm record);
}