package com.pl.admin.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleImporterTest {

    @Autowired
    private ArticleImporter ai;
    @Test
    void save() {
        String d="D:\\proj\\ppt\\result\\2020-5-15\\";
        String[] d1=new String[]{
//                "lobste_rs",
//                "betterdev_link",
//                "stratechery_com",
//                "summary",
                "content",
//                "content1"
        };
        for(String td:d1){
            String s=d+td+".txt";
            List<ArticleImporter.Item> il=ai.read(s);
            assertNotNull(il);
            ai.save(il);
            assertNotNull(ai);
        }
    }
}