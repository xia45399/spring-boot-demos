package com.summer.springboot.demo.service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/word")
public class WordController {
    @RequestMapping("a")
    public String a() {
        return "word-a";
    }

    @RequestMapping("b")
    public String b() {
        return "word-b";
    }
}
