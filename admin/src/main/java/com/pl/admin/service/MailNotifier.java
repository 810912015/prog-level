package com.pl.admin.service;


import com.pl.admin.dto.Result;
import com.pl.data.common.api.Mailer;
import com.pl.data.common.api.QQMailer;
import org.springframework.stereotype.Component;

@Component
public class MailNotifier implements Notifier {


    private static final String REGISTER=  "<p>这是分码网的注册激活码,请在注册表单中输入.</p>\n" +
            "\n" +
            "<h1>%s</h1>\n" ;
    @Override
    public Result sendRegister(String addr, String sid, String code) {
        String c0=String.format(REGISTER,code);
        String c=String.format(Mailer.TPL,c0);
        com.pl.data.common.api.Result r= mailer.send(addr,"注册激活码",c);
        return new Result(r.isSuccess(),r.getMsg());
    }

    private static final String INVITE="<p>这是分码网的做题邀请.请点击下面链接做题</p><a href='%s'><h1>%s</h1></a>";
    @Override
    public Result sendInvite(String add, String invideId) {
        String s=String.format("http://www.proglevel.com/invite/%s",invideId);
        String c=String.format(INVITE,s,s);
        String content=String.format(Mailer.TPL,c);
        com.pl.data.common.api.Result r= mailer.send(add,"分码网的做题邀请",content);
        return new Result(r.isSuccess(),r.getMsg());
    }

    private Mailer mailer=new QQMailer();
}
