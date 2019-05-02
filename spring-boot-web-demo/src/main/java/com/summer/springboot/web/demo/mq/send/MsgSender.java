package com.summer.springboot.web.demo.mq.send;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MsgSender {
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String routerKey, String msg) {
        rabbitTemplate.convertAndSend(routerKey, msg);
    }
}
