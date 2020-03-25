package com.pl.admin.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpiderDataImporterTest {

    @Autowired
    SpiderDataImporter sdi;
    @Test
    void handle() {
        sdi.handle();
    }

    @Test
    void sub(){
        String f="123.abc";
        String r=f.substring(f.indexOf(".")+1);
        assertEquals("abc",r);
    }
}
