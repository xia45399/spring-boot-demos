package com.summer.springboot.redis.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis1")
public class Test1 {
    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @RequestMapping("/setStr")
    public void setStr(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @RequestMapping("/getStr")
    public Object getStr(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
