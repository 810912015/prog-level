package com.pl.data.common.api;

public interface Mailer {
    Result send(String[] addr,String subject,String content);
    Result send(String addr,String subject,String content);

    static final String TPL="<html lang=\"zh-cmn-Hans\">\n" +
            "<head>" +
            "<title>通知邮件</title>\n" +
            "<meta charset=\"utf-8\"/>" +
            "</head>\n" +
            "<body><code>\n" +
            "%s\n" +
            "</code></body>\n" +
            "</html>";

}
