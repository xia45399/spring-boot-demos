package com.summer.springboot.redis.demo.controller;

import com.summer.springboot.redis.demo.utils.RedisUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("redis")
public class RedisController {
    @Resource
    private RedisUtils redisUtils;

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

    @RequestMapping("string")
    public void string() {
        System.out.println(redisUtils.get("stringKey"));
        System.out.println(redisUtils.set("stringKey", "fsd发多少更多官方第三个是翻"));
        System.out.println(redisUtils.get("stringKey"));
        System.out.println(redisUtils.set("stringKey", "fsd发多少更多官方第三个是翻", 100));
        System.out.println("stringKey 过期时间 " + redisUtils.getExpire("stringKey"));
        System.out.println("stringKey 设置过期时间 " + redisUtils.expire("stringKey", 200));

        System.out.println("\n自增测试");
        System.out.println(redisUtils.set("stringIntKey", "3"));
        System.out.println(redisUtils.get("stringIntKey"));
        System.out.println(redisUtils.incr("stringIntKey", 4));
        System.out.println(redisUtils.get("stringIntKey"));
        System.out.println(redisUtils.decr("stringIntKey", 5));
        System.out.println(redisUtils.get("stringIntKey"));
    }

    @RequestMapping("hash")
    public void hash() {
        System.out.println(redisUtils.hGet("hashKey", "name"));
        Map<Object, Object> map = redisUtils.hGetAll("hashKey");

        Map<String, Object> nMap = new HashMap<>();
        nMap.put("aaa", "方式发");
        nMap.put("bbb", "方式发111");
        redisUtils.hmSet("hashKey", nMap);
        redisUtils.hSet("hashKey", "fag", "方式");
        System.out.println("");
    }
}
