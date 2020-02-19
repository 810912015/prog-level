package com.pl.data.mapper;

import com.pl.data.model.URolePermission;
import com.pl.data.model.URolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface URolePermissionMapper {
    long countByExample(URolePermissionExample example);

    int deleteByExample(URolePermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(URolePermission record);

    int insertSelective(URolePermission record);

    List<URolePermission> selectByExample(URolePermissionExample example);

    URolePermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") URolePermission record, @Param("example") URolePermissionExample example);

    int updateByExample(@Param("record") URolePermission record, @Param("example") URolePermissionExample example);

    int updateByPrimaryKeySelective(URolePermission record);

    int updateByPrimaryKey(URolePermission record);
}