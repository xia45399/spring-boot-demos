package com.summer.springboot.web.demo.controller;


import com.summer.springboot.web.demo.mq.send.MsgSender;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("mq")
public class MqController {

    @Resource
    private MsgSender msgSender;

    @RequestMapping("/sendMsg")
    public Object sendMsg() {
        String msg = new DateTime().toString("yyyy-MM-dd HH:mm:ss");
        String routerKey = "testQueue";
        msgSender.sendMsg(routerKey, msg);
        return msg;
    }
}
