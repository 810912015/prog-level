package com.pl.portal.component.mq.handler;

import com.pl.portal.component.mq.sender.IQSender;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ReqMsgHandler {
    @RabbitListener(queues = IQSender.Types.REQUEST)
    public void process(IQSender.ReqMsg msg){
        System.out.println(msg);
    }
}
