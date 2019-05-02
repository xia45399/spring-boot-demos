package com.summer.springboot.web.demo.task;


import com.summer.springboot.web.demo.mq.receive.MsgReceiver;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@EnableScheduling
public class MqTask {
    private static Log logger = LogFactory.getLog(MsgReceiver.class);
    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private Queue testQueue;

    //每秒执行4次
    @Scheduled(fixedDelay = 250)
    public void sendMsg() {
        String msg = "Auto Msg " + System.currentTimeMillis();
        rabbitTemplate.convertAndSend(testQueue.getName(), msg);
    }
}
