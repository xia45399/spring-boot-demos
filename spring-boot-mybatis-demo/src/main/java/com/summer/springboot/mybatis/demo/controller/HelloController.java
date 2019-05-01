package com.summer.springboot.mybatis.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("hello")
public class HelloController {

    @RequestMapping("test")
    public Object test() {
        return "test" + new Date();
    }
}
