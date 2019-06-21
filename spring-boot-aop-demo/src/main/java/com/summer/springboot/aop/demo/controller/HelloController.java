package com.summer.springboot.aop.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public Long hello() {
        System.out.println("333");
        return System.currentTimeMillis();
    }

    @RequestMapping("/hello1")
    public Long hello1() {
        throw new RuntimeException("哈哈哈");
    }
}
