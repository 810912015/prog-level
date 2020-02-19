package com.pl.data.mapper;

import com.pl.data.model.Validation;
import com.pl.data.model.ValidationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ValidationMapper {
    long countByExample(ValidationExample example);

    int deleteByExample(ValidationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Validation record);

    int insertSelective(Validation record);

    List<Validation> selectByExample(ValidationExample example);

    Validation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Validation record, @Param("example") ValidationExample example);

    int updateByExample(@Param("record") Validation record, @Param("example") ValidationExample example);

    int updateByPrimaryKeySelective(Validation record);

    int updateByPrimaryKey(Validation record);
}