package com.pl.portal.controller;


import com.pl.portal.component.wx.DuplicatedMessageChecker;
import com.pl.portal.service.IWxArticleService;
import com.riversoft.weixin.common.decrypt.AesException;
import com.riversoft.weixin.common.decrypt.MessageDecryption;
import com.riversoft.weixin.common.decrypt.SHA1;
import com.riversoft.weixin.common.event.EventRequest;
import com.riversoft.weixin.common.exception.WxRuntimeException;
import com.riversoft.weixin.common.message.XmlMessageHeader;
import com.riversoft.weixin.common.message.xml.NewsXmlMessage;
import com.riversoft.weixin.mp.base.AppSetting;
import com.riversoft.weixin.mp.care.CareMessages;
import com.riversoft.weixin.mp.message.MpXmlMessages;
import com.riversoft.weixin.mp.user.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by exizhai on 10/7/2015.
 */
@Controller
public class WxCallbackController {

    private static Logger logger = LoggerFactory.getLogger(WxCallbackController.class);

    @Autowired
    private DuplicatedMessageChecker duplicatedMessageChecker;

    public void setDuplicatedMessageChecker(DuplicatedMessageChecker duplicatedMessageChecker) {
        this.duplicatedMessageChecker = duplicatedMessageChecker;
    }

    /**
     * 公众号回调接口
     * 这里为了演示方便使用单个固定URL，实际使用的时候一个一个系统可能有多个公众号，这样的话需要有个分发逻辑：
     * 比如callback url可以定义为  /wx/mp/[公众号的appId]，通过appId构造不同的AppSetting
     *
     * @param signature
     * @param msg_signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @param encrypt_type
     * @param content
     * @return
     */
    @RequestMapping("/wx/mp")
    @ResponseBody
    public String mp(@RequestParam(value="signature") String signature,
                     @RequestParam(value="msg_signature", required = false) String msg_signature,
                     @RequestParam(value="timestamp") String timestamp,
                     @RequestParam(value="nonce") String nonce,
                     @RequestParam(value="echostr", required = false) String echostr,
                     @RequestParam(value="encrypt_type", required = false) String encrypt_type,
                     @RequestBody(required = false) String content) {

        logger.info("signature={}, msg_signature={}, timestamp={}, nonce={}, echostr={}, encrypt_type={}", signature, msg_signature, timestamp, nonce, echostr, encrypt_type);

        AppSetting appSetting = AppSetting.defaultSettings();
        try {
            if(!SHA1.getSHA1(appSetting.getToken(), timestamp, nonce).equals(signature)) {
                logger.warn("非法请求.");
                return "非法请求.";
            }
        } catch (AesException e) {
            logger.error("check signature failed:", e);
            return "非法请求.";
        }

        if (!StringUtils.isEmpty(echostr)) {
            return echostr;
        }

        XmlMessageHeader xmlRequest = null;
        if("aes".equals(encrypt_type)) {
            try {
                MessageDecryption messageDecryption = new MessageDecryption(appSetting.getToken(), appSetting.getAesKey(), appSetting.getAppId());
                xmlRequest = MpXmlMessages.fromXml(messageDecryption.decrypt(msg_signature, timestamp, nonce, content));
                XmlMessageHeader xmlResponse = mpDispatch(xmlRequest);

                if(xmlResponse != null) {
                    try {
                        return messageDecryption.encrypt(MpXmlMessages.toXml(xmlResponse), timestamp, nonce);
                    } catch (WxRuntimeException e) {

                    }
                }
            } catch (AesException e) {
            }
        } else {
            xmlRequest = MpXmlMessages.fromXml(content);
            XmlMessageHeader xmlResponse = mpDispatch(xmlRequest);
            if(xmlResponse != null) {
                try {
                    return MpXmlMessages.toXml(xmlResponse);
                } catch (WxRuntimeException e) {
                }
            }
        }

        return "";
    }

    XmlMessageHeader mpDispatch(XmlMessageHeader xmlRequest) {
        //比较简单，直接返回文章列表
        if(!duplicatedMessageChecker.isDuplicated(xmlRequest.getFromUser() + xmlRequest.getCreateTime().getTime())) {
            NewsXmlMessage r=wxArticleService.getMsg(xmlRequest);
            return r;
        } else {
            logger.warn("Duplicated message: {} @ {}", xmlRequest.getMsgType(), xmlRequest.getFromUser());
            return null;
        }
    }

    @Autowired
    private IWxArticleService wxArticleService;
}
