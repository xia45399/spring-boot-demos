package com.summer.springboot.demo.service.controller;

import com.summer.springboot.demo.service.HelloService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping
public class HelloController {
    @Resource
    private HelloService helloService;
    @RequestMapping("hello")
    public String hello(){
       return helloService.sayHello();
    }
}
