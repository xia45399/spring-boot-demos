package com.summer.springboot.redis.demo.controller;

import com.summer.springboot.redis.demo.config.RedisUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("redis")
public class RedisController {
    @Resource
    private RedisUtils redisUtils;

    @RequestMapping("string")
    public void string() {

    }

    @RequestMapping("common")
    public void common() {
        redisUtils.set("aaa", "aaa");

        System.out.println(redisUtils.expire("aaa", 100));
        System.out.println("aaa 过期时间" + redisUtils.getExpire("aaa"));
        System.out.println("hasKey " + redisUtils.hasKey("aaa"));

        redisUtils.set("willDetele", "affect方法");
        System.out.println(redisUtils.get("willDetele"));
        System.out.println("before delete hasKey " + redisUtils.hasKey("willDetele"));
        redisUtils.del("willDetele");
        System.out.println("after delete hasKey" + redisUtils.hasKey("willDetele"));
    }
}
