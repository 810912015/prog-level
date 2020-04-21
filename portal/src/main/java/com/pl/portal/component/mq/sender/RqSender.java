package com.pl.portal.component.mq.sender;

import com.pl.portal.service.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RqSender implements IQSender {
    protected static final Logger logger= LoggerFactory.getLogger(RqSender.class);
    protected AmqpTemplate atpl;


    @Autowired
    public void setAtpl(AmqpTemplate atpl) {
        this.atpl = atpl;
    }

    @Override
    public void send(String exchange,String route,Object msg){

        atpl.convertAndSend(exchange,route,msg);
    }


}
