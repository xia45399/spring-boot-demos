package com.summer.rabbitmq.demo.mq.send;

import com.summer.rabbitmq.demo.mq.msg.MqMsg;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MsgSender {
    private String routerKey = "testQueue";

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(MqMsg msg) {
        //发到默认exchange 默认exchange的name为空
        rabbitTemplate.convertAndSend(routerKey, msg);
    }
}
