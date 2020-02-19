package com.pl.data.common.api;


import com.google.common.base.Throwables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.util.Date;
import java.util.Properties;

@Component
@Qualifier("qqmail")
@Primary
public class QQMailer implements Mailer {
    private static final Logger loger = LoggerFactory.getLogger(QQMailer.class);

    // 发件人的邮箱地址和密码

    private String sendEmailAccount;

    //如果有授权码，此处填写授权码

    private String sendEmailPassword;
    private Properties props;
    private boolean debug;

    // 发件人邮箱的 SMTP 服务器地址, 可以登录web邮箱查询

    private static String sendEmailSMTPHost = "smtp.qq.com";

    public QQMailer(String sendAccount, String sendPassword) {
        this.sendEmailAccount = sendAccount;
        this.sendEmailPassword = sendPassword;

        debug=true;

        props = new Properties();
        props.setProperty("mail.transport.protocol", "smtp");
        props.setProperty("mail.smtp.host", sendEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
    }

    public QQMailer() {
        this("810912015@qq.com", "pjldrrlmkxsmbebb");
    }


    @Override
    public Result send(String[] addr, String subject, String content) {
        try {
            // 根据配置创建会话对象, 用于和邮件服务器交互
            Session session = Session.getDefaultInstance(props);

            // 设置为debug模式, 可以查看详细的发送 log
            session.setDebug(this.debug);

            // 创建一封邮件
            Message message = createMimeMessage(session, sendEmailAccount, addr, subject, content);

            // 根据 Session 获取邮件传输对象
            Transport transport = session.getTransport();

            // 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则会报错
            transport.connect(sendEmailAccount, sendEmailPassword);

            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());

            // 关闭连接
            transport.close();
            return new Result(true, "发送成功");
        } catch (Exception e) {
            loger.error(e.getMessage() + Throwables.getStackTraceAsString(e));
            return new Result(false, e.getMessage());
        }
    }

    @Override
    public Result send(String addr, String subject, String content) {
        return send(new String[]{addr},subject,content);
    }

    /**
     * 创建一封简单邮件
     */

    private Message createMimeMessage(Session session, String sendMail, String[] receiveMail, String subject, String content) throws Exception {
        Message message = new MimeMessage(session);

        InternetAddress[] ia= new InternetAddress[receiveMail.length];
        for(int i=0;i<receiveMail.length;i++){
            ia[i]=new InternetAddress(receiveMail[i]);
        }

        message.setFrom(new InternetAddress(sendMail));
        message.setRecipients(MimeMessage.RecipientType.TO, ia);

        // 设置邮件标题
        message.setSubject(subject);

        MimeMultipart multipart = new MimeMultipart();

        BodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(content, "text/html;charset=utf-8");
        multipart.addBodyPart(htmlPart);


        // 设置邮件正文
        message.setContent(multipart);
        message.setSentDate(new Date());

        //保存设置
        message.saveChanges();
        return message;

    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }
}
