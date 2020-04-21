package com.pl.portal.dao;


import com.pl.portal.dto.Bound;
import com.pl.portal.dto.ExamDto2;
import com.pl.portal.dto.PassParam;
import com.pl.portal.dto.ThinQuestion;
import com.pl.data.model.Invitee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface MyQuestionMaper {
    @Update("update u_user set firmId=#{map.firmId} where id=#{map.uid}")
    int updateUserFirm(@Param("map") Map<String, Object> map);


    @Update("UPDATE pass \n" +
            "SET \n" +
            "    score = #{p.score},\n" +
            "    scoreReason = #{p.reason}\n" +
            "WHERE\n" +
            "    groupid = #{p.gid}\n" +
            "        AND id > 0")
    int updatePassScore(@Param("p") PassParam.PassScore p);
    @Select("<script>\n" +
            "SELECT \n" +
            "    distinct(groupid)\n" +
            "FROM\n" +
            "    pass\n" +
            "where stage='T' " +
            "and qid>46 \n" +//应删除
            "    <if test=\"p.qid!=null\">\n" +
            "    and qid = #{p.qid} \n" +
            "    </if>\n" +
            "    <if test=\"p.eid!=null\">\n" +
            "    AND eid = #{p.eid} \n" +
            "    </if>\n" +
            "    <if test=\"p.uid!=null\">\n" +
            "    AND uid = #{p.uid}\n" +
            "    </if> " +
            "order by id desc \n" +//应删除
            "</script>")
    List<String> getPass(@Param("p") PassParam p);

    @Select("SELECT `question`.`id`,\n" +
            "    `question`.`Name`,\n" +
            "    `question`.`Title`,\n" +
            "    `question`.`Category`,\n" +
            "    `question`.`Remarks`,\n" +
            "    `question`.`lang`,\n" +
            "    `question`.`level`,\n" +
            "    `question`.`tag1`,\n" +
            "    `question`.`tag2`,\n" +
            "    `question`.`otherTag`\n" +
            "FROM `wpd`.`question`\n" +
            "where id < #{minId}\n" +
            "order by id desc\n" +
            "limit #{size};")
    List<ThinQuestion> getAllQuestion(@Param("maxId") Long maxId, @Param("minId") Long minId, @Param("size") Integer size);


    @Select("SELECT `question`.`id`,\n" +
            "    `question`.`Name`,\n" +
            "    `question`.`Title`,\n" +
            "    `question`.`Category`,\n" +
            "    `question`.`Remarks`,\n" +
            "    `question`.`lang`,\n" +
            "    `question`.`level`,\n" +
            "    `question`.`tag1`,\n" +
            "    `question`.`tag2`,\n" +
            "    `question`.`otherTag`\n" +
            "FROM `wpd`.`question`\n" +
            "where userId=#{uid} and (id < #{minId})\n" +
            "order by id desc\n" +
            "limit #{size};")
    List<ThinQuestion> getMyQuestion(@Param("uid") long uid, @Param("maxId") Long maxId, @Param("minId") Long minId, @Param("size") Integer size);


    @Select("SELECT \n" +
            "    e.id,\n" +
            "    e.name,\n" +
            "    e.title,\n" +
            "    e.tag,\n" +
            "    e.Category,\n" +
            "    e.duration,\n" +
            "    e.start,\n" +
            "    COUNT(q.id) AS qCount,\n" +
            "    SUM(q.weight) AS weight\n" +
            "FROM\n" +
            "    exam e\n" +
            "        JOIN\n" +
            "    exam_question q ON e.id = q.eid\n" +
            "WHERE\n" +
            "    e.id < #{minId}\n" +
            "GROUP BY e.id\n" +
            "ORDER BY e.id DESC\n" +
            "LIMIT #{size}")
    List<ExamDto2> getAllExam(@Param("maxId") Long maxId, @Param("minId") Long minId, @Param("size") Integer size);

    @Select("SELECT \n" +
            "    e.id,\n" +
            "    e.name,\n" +
            "    e.title,\n" +
            "    e.tag,\n" +
            "    e.Category,\n" +
            "    e.duration,\n" +
            "    e.start,\n" +
            "    COUNT(q.id) AS qCount,\n" +
            "    SUM(q.weight) AS weight\n" +
            "FROM\n" +
            "    exam e\n" +
            "       left JOIN\n" +
            "    exam_question q ON e.id = q.eid\n" +
            "WHERE\n" +
            "   e.userId=#{uid} and (e.id < #{minId})\n" +
            "GROUP BY e.id\n" +
            "ORDER BY e.id DESC\n" +
            "LIMIT #{size}")
    List<ExamDto2> getMyExam(@Param("uid") long uid, @Param("maxId") Long maxId, @Param("minId") Long minId, @Param("size") Integer size);

    @Select("SELECT \n" +
            "    `invitee`.`id`,\n" +
            "    `invitee`.`firmId`,\n" +
            "    `invitee`.`name`,\n" +
            "    `invitee`.`email`,\n" +
            "    `invitee`.`cat1`,\n" +
            "    `invitee`.`cat2`,\n" +
            "    `invitee`.`cat3`,\n" +
            "    `invitee`.`cat4`,\n" +
            "    `invitee`.`cat5`,\n" +
            "    `invitee`.`Remarks`,\n" +
            "    `invitee`.`Dclt`\n" +
            "FROM\n" +
            "    `wpd`.`invitee`\n" +
            "WHERE\n" +
            "    firmId = #{firmId} AND (id < #{b.minId})\n" +
            "        AND (name LIKE '%#{key}%' OR email LIKE '%#{key}%'\n" +
            "        OR cat1 LIKE '%#{key}%'\n" +
            "        OR cat2 LIKE '%#{key}%'\n" +
            "        OR cat3 LIKE '%#{key}%'\n" +
            "        OR cat4 LIKE '%#{key}%'\n" +
            "        OR cat5 LIKE '%#{key}%')")
    List<Invitee> queryByKey(@Param("key") String key, @Param("b") Bound b, @Param("firmId") int firmId);
}
