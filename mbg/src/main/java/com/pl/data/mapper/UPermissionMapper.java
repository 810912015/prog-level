package com.pl.data.mapper;

import com.pl.data.model.UPermission;
import com.pl.data.model.UPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UPermissionMapper {
    long countByExample(UPermissionExample example);

    int deleteByExample(UPermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UPermission record);

    int insertSelective(UPermission record);

    List<UPermission> selectByExample(UPermissionExample example);

    UPermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UPermission record, @Param("example") UPermissionExample example);

    int updateByExample(@Param("record") UPermission record, @Param("example") UPermissionExample example);

    int updateByPrimaryKeySelective(UPermission record);

    int updateByPrimaryKey(UPermission record);
}