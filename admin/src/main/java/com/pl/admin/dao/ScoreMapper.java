package com.pl.admin.dao;

import com.pl.admin.dto.ScoreDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ScoreMapper {
    @Select("SELECT \n" +
            "    ex.name AS ename,\n" +
            "    iv.name AS iname,\n" +
            "    iv.email,\n" +
            "    SUM(p.weight) AS weight,\n" +
            "    SUM(p.score) AS score,\n" +
            "    p.Dclt\n" +
            "FROM\n" +
            "    exam_invite ei\n" +
            "        JOIN\n" +
            "    pass p ON ei.iid = p.eiid\n" +
            "        JOIN\n" +
            "    invitee iv ON ei.vUserId = iv.id\n" +
            "        JOIN\n" +
            "    exam ex ON ei.eid = ex.id\n" +
            "        JOIN\n" +
            "    question q ON q.id = p.qid\n" +
            "WHERE\n" +
            "    ei.firmid = #{fid} AND p.stage = 'T'\n" +
            "        AND p.remarks = 'T' and iv.name like #{iname} and ex.name like #{ename}\n" +
            "GROUP BY ex.name , iv.email limit #{offset},#{take} ")
    List<ScoreDto.Firm> getByFirmId(@Param("fid") int fid, @Param("iname") String iname,
                                    @Param("ename") String ename, @Param("offset") int offset, @Param("take") int take);

    @Select("select count(1) from ( SELECT ex.name AS ename\n" +

            "FROM\n" +
            "    exam_invite ei\n" +
            "        JOIN\n" +
            "    pass p ON ei.iid = p.eiid\n" +
            "        JOIN\n" +
            "    invitee iv ON ei.vUserId = iv.id\n" +
            "        JOIN\n" +
            "    exam ex ON ei.eid = ex.id\n" +
            "        JOIN\n" +
            "    question q ON q.id = p.qid\n" +
            "WHERE\n" +
            "    ei.firmid = #{fid} AND p.stage = 'T'\n" +
            "        AND p.remarks = 'T' and iv.name like #{iname} and ex.name like #{ename}\n" +
            "GROUP BY ex.name , iv.email ) a")
    Integer countByFirmId(@Param("fid") int fid, @Param("iname") String iname, @Param("ename") String ename);

    @Select("SELECT \n" +
            "    q.id as qid,\n" +
            "    q.name as qname,\n" +
            "    q.title as qtitle,\n" +
            "    p.groupId,\n" +
            "    p.Dclt,\n" +
            "    SUM(p.weight) AS score\n" +
            "FROM\n" +
            "    question q\n" +
            "        JOIN\n" +
            "    pass p ON p.qid = q.id\n" +
            "WHERE\n" +
            "    p.uid = #{uid} AND p.stage = 'T'\n" +
            "        AND p.remarks = 'T'\n" +
            "GROUP BY uid , eid , qid , groupid\n" +
            "ORDER BY p.id DESC")
    List<ScoreDto> getScore(int uid);

    @Select("SELECT \n" +
            "    eq.qid\n" +
            "FROM\n" +
            "    exam_question eq\n" +
            "        JOIN\n" +
            "    exam_invite i ON eq.eid = i.eid\n" +
            "WHERE\n" +
            "    i.iid = #{iid}")
    List<Integer> getQid(String iid);
}
