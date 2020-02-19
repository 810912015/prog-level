package com.pl.data.mapper;

import com.pl.data.model.UUserRole;
import com.pl.data.model.UUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UUserRoleMapper {
    long countByExample(UUserRoleExample example);

    int deleteByExample(UUserRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UUserRole record);

    int insertSelective(UUserRole record);

    List<UUserRole> selectByExample(UUserRoleExample example);

    UUserRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UUserRole record, @Param("example") UUserRoleExample example);

    int updateByExample(@Param("record") UUserRole record, @Param("example") UUserRoleExample example);

    int updateByPrimaryKeySelective(UUserRole record);

    int updateByPrimaryKey(UUserRole record);
}