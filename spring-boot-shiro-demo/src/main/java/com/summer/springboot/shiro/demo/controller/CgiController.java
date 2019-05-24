package com.summer.springboot.shiro.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cgi")
public class CgiController {
    @RequestMapping("/version")
    public String getVersion() {
        return "1.0.0";
    }
}
