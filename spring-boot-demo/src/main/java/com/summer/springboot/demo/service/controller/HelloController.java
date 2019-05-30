package com.summer.springboot.demo.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {
    @RequestMapping("a")
    public String a(){
        return "hello-a";
    }

    @RequestMapping("b")
    public String b(){
        return "hello-b";
    }
}
