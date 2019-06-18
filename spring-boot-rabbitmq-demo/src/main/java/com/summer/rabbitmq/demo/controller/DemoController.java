package com.summer.rabbitmq.demo.controller;

import com.summer.rabbitmq.demo.mq.msg.MqMsg;
import com.summer.rabbitmq.demo.mq.util.MqUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class DemoController {
    @Resource
    private MqUtils mqUtils;

    @RequestMapping("/mq/batchSend")
    public void batchSend(int count) {
        while (count-- > 0) {
            send();
        }
    }

    @RequestMapping("/mq/send")
    public void send() {
        MqMsg mqMsg = new MqMsg();
        mqMsg.setName("哈哈");
        mqMsg.setTime(System.currentTimeMillis());
        mqUtils.sendMsg(mqMsg);
    }

    @RequestMapping("/mq/sendAndReceive")
    public Object sendAndReceive() {
        MqMsg mqMsg = new MqMsg();
        mqMsg.setName("哈哈");
        mqMsg.setTime(System.currentTimeMillis());
        return mqUtils.sendAndReveive(mqMsg);
    }

    @RequestMapping("/mq/receive")
    public void receive() {
        mqUtils.receiveMsg();
    }

    @RequestMapping("req/send")
    public void reqSend() {
        MqMsg reqMsg = new MqMsg();
        reqMsg.setName("123");
    }
}
