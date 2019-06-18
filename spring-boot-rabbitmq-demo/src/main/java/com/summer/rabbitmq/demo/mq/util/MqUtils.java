package com.summer.rabbitmq.demo.mq.util;

import com.summer.rabbitmq.demo.mq.msg.MqMsg;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class MqUtils {

    private String queueName = "testQueue";
    private String routerKey = queueName;
    @Resource
    private RabbitTemplate rabbitTemplate;

    public void receiveMsg() {
        ParameterizedTypeReference<MqMsg> typeReference = ParameterizedTypeReference.forType(MqMsg.class);
        MqMsg aa = (MqMsg) rabbitTemplate.receiveAndConvert(queueName);
        System.out.println(aa);
    }

    public void sendMsg(MqMsg msg) {
        //发到默认exchange 默认exchange的name为空
        rabbitTemplate.convertAndSend(routerKey, msg);
    }

    public Object sendAndReveive(MqMsg msg) {
        return rabbitTemplate.convertSendAndReceive(routerKey, msg);
    }
}
