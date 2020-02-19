package com.pl.admin.dao;

import com.pl.admin.dto.ProfileDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AuthMapper {
    @Update("UPDATE `wpd`.`u_user`\n" +
            "SET\n" +
            "`petname` = #{p.name},\n" +
            "`gender` = #{p.gender},\n" +
            "`birthday` = #{p.birthday},\n" +
            "`photoUrl` = #{p.imgurl},\n" +
            "`intro` = #{p.desc}\n" +
            "WHERE `id` = #{uid};")
    int updateProfile(@Param("p") ProfileDto d, @Param("uid") long uid);
}
