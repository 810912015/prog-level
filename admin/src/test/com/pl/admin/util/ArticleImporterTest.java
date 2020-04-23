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
        List<ArticleImporter.Item> il=ai.read();
        assertNotNull(il);
        ai.save(il);
        assertNotNull(ai);
    }
}