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
        String s="D:\\proj\\ppt\\better-dev-20200423-9.txt";
        List<ArticleImporter.Item> il=ai.read(s);
        assertNotNull(il);
        ai.save(il);
        assertNotNull(ai);
    }
}