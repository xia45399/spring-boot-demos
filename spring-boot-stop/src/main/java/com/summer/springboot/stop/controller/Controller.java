package com.summer.springboot.stop.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class Controller {
    @Resource
    private RabbitTemplate rabbitTemplate;

    @RequestMapping("batchSend")
    public void batchSend(int count) {
        while (count > 0) {
            String msg = "msg" + count--;
            rabbitTemplate.convertAndSend("listenQueue", msg);
        }
    }
}
