package com.pl.search.dao;

import com.pl.search.domain.EsBook;
import com.pl.search.domain.EsBook2;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EsBookDao {
    @Select(" SELECT \n" +
            "    a.`id`,\n" +
            "    a.`bookId`,\n" +
            "    a.`name`,\n" +
            "    a.`author`,\n" +
            "    a.`brief`,\n" +
            "    a.`category`,\n" +
            "    a.`categoryId`,\n" +
            "    a.`category_id`,\n" +
            "    a.`keyWords`,\n" +
            "    a.`cover`,\n" +
            "    a.`completeStatus`,\n" +
            "    a.`displayName`,\n" +
            "    a.`partnerName`,\n" +
            "    a.`salePrice`,\n" +
            "    a.`startChargeChapter`,\n" +
            "    a.`wordCount`,\n" +
            "    a.`k1Price`,\n" +
            "    a.`create_time`,\n" +
            "    a.`update_time`,\n" +
            "    a.`launch_time`,\n" +
            "    a.`billPattern`,\n" +
            "    a.`copyrightId`,\n" +
            "    a.`copyrightName`,\n" +
            "    a.`cooeeId`,\n" +
            "    a.`chapterCount`,\n" +
            "    a.`source`,\n" +
            "    a.`dataChangeLast_time`,\n" +
            "    a.`bookStatus`,\n" +
            "    b.`intro`,\n" +
            "    b.`mainTag`,\n" +
            "    b.`headTag`,\n" +
            "    b.`score`,\n" +
            "    b.`cat1`,\n" +
            "    b.`cat2`,\n" +
            "    b.`readCount`,\n" +
            "    b.`searchCount`,\n" +
            "    b.`remain`,\n" +
            "    b.`keyword1`,\n" +
            "    b.`keyword2`,\n" +
            "    b.`keyword3`,\n" +
            "    b.`added`\n" +
            "FROM\n" +
            "    `reader`.`bms_book` a\n" +
            "        LEFT JOIN\n" +
            "    `reader`.`bms_book_attr` b ON a.id = b.bookId where a.bookStatus in('','A');")
    List<EsBook> getAll();

    @Select(" SELECT \n" +
            "    a.`id`,\n" +
            "    a.`bookId`,\n" +
            "    a.`name`,\n" +
            "    a.`author`,\n" +
            "    a.`brief`,\n" +
            "    a.`category`,\n" +
            "    a.`categoryId`,\n" +
            "    a.`category_id`,\n" +
            "    a.`keyWords`,\n" +
            "    a.`cover`,\n" +
            "    a.`completeStatus`,\n" +
            "    a.`displayName`,\n" +
            "    a.`partnerName`,\n" +
            "    a.`salePrice`,\n" +
            "    a.`startChargeChapter`,\n" +
            "    a.`wordCount`,\n" +
            "    a.`k1Price`,\n" +
            "    a.`create_time`,\n" +
            "    a.`update_time`,\n" +
            "    a.`launch_time`,\n" +
            "    a.`billPattern`,\n" +
            "    a.`copyrightId`,\n" +
            "    a.`copyrightName`,\n" +
            "    a.`cooeeId`,\n" +
            "    a.`chapterCount`,\n" +
            "    a.`source`,\n" +
            "    a.`dataChangeLast_time`,\n" +
            "    a.`bookStatus`,\n" +
            "    b.`intro`,\n" +
            "    b.`mainTag`,\n" +
            "    b.`headTag`,\n" +
            "    b.`score`,\n" +
            "    b.`cat1`,\n" +
            "    b.`cat2`,\n" +
            "    b.`readCount`,\n" +
            "    b.`searchCount`,\n" +
            "    b.`remain`,\n" +
            "    b.`keyword1`,\n" +
            "    b.`keyword2`,\n" +
            "    b.`keyword3`,\n" +
            "    b.`added`,\n" +
            "    b.`favor_count` as favorCount,\n" +
            "    b.`favor_time` as favorTime,\n" +
            "    b.`favor_plain` as favorPlain,\n" +
            "    b.`favor_cal` as favorCal\n"+
            "FROM\n" +
            "    `reader`.`bms_book` a\n" +
            "        LEFT JOIN\n" +
            "    `reader`.`bms_book_attr` b ON a.id = b.bookId where a.bookStatus in('','A');")
    List<EsBook2> getAll2();

    @Select(" SELECT \n" +
            "    a.`id`,\n" +
            "    a.`bookId`,\n" +
            "    a.`name`,\n" +
            "    a.`author`,\n" +
            "    a.`brief`,\n" +
            "    a.`category`,\n" +
            "    a.`categoryId`,\n" +
            "    a.`category_id`,\n" +
            "    a.`keyWords`,\n" +
            "    a.`cover`,\n" +
            "    a.`completeStatus`,\n" +
            "    a.`displayName`,\n" +
            "    a.`partnerName`,\n" +
            "    a.`salePrice`,\n" +
            "    a.`startChargeChapter`,\n" +
            "    a.`wordCount`,\n" +
            "    a.`k1Price`,\n" +
            "    a.`create_time`,\n" +
            "    a.`update_time`,\n" +
            "    a.`launch_time`,\n" +
            "    a.`billPattern`,\n" +
            "    a.`copyrightId`,\n" +
            "    a.`copyrightName`,\n" +
            "    a.`cooeeId`,\n" +
            "    a.`chapterCount`,\n" +
            "    a.`source`,\n" +
            "    a.`dataChangeLast_time`,\n" +
            "    a.`bookStatus`,\n" +
            "    b.`intro`,\n" +
            "    b.`mainTag`,\n" +
            "    b.`headTag`,\n" +
            "    b.`score`,\n" +
            "    b.`cat1`,\n" +
            "    b.`cat2`,\n" +
            "    b.`readCount`,\n" +
            "    b.`searchCount`,\n" +
            "    b.`remain`,\n" +
            "    b.`keyword1`,\n" +
            "    b.`keyword2`,\n" +
            "    b.`keyword3`,\n" +
            "    b.`added`\n" +
            "FROM\n" +
            "    `reader`.`bms_book` a\n" +
            "        LEFT JOIN\n" +
            "    `reader`.`bms_book_attr` b ON a.id = b.bookId where a.id=#{id} and a.bookStatus in('','A');")
    List<EsBook> getById(@Param("id") long id);

    @Update("update bms_book_attr set score=#{score} where bookid=#{bookId}")
    int updateScore(@Param("bookId") Long bookId,@Param("score") Integer score);
}
