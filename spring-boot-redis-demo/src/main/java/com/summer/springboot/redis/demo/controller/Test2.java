package com.summer.springboot.redis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis2")
public class Test2 {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/setStr")
    public void setStr(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @RequestMapping("/getStr")
    public String getStr(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
