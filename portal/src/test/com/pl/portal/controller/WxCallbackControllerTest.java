package com.pl.portal.controller;

import com.riversoft.weixin.common.message.XmlMessageHeader;
import com.riversoft.weixin.common.message.xml.NewsXmlMessage;
import com.riversoft.weixin.mp.message.MpXmlMessages;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WxCallbackControllerTest {

    @Autowired WxCallbackController c;
    @Test
    void mpDispatch() {
        XmlMessageHeader h=new XmlMessageHeader();
        h.setToUser("to");
        h.setFromUser("from");
        h.setCreateTime(new Date());
        XmlMessageHeader r=c.mpDispatch(h);
        assertTrue(r instanceof NewsXmlMessage);
        String s= MpXmlMessages.toXml(r);
        assertNotNull(s);
    }
}