package com.summer.springboot.redis.demo.controller;

import com.summer.springboot.redis.demo.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;

@RestController
@RequestMapping
public class HelloController {
    @Resource
    private HelloService helloService;

    @RequestMapping("hello")
    public String hello() {
        System.out.println("请求时间=" + new Date());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return helloService.sayHello();
    }
}
