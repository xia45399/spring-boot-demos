package com.summer.springboot.aop.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.summer.springboot.aop.demo.pojo.Demo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("param")
@RequestMapping
public class ParamController {

    @RequestMapping("base")
    public String base(Long lon, Integer inte, String str) {
        System.out.println("基本参数类型方法，返回值为String");
        return str + lon + inte;
    }

    @RequestMapping("demo")
    public Demo demo(Demo demo) {
        System.out.println("pojo类型方法，返回值为Demo");
        demo.setInte(1);
        demo.setLon(1L);
        demo.setStr("after");
        return demo;
    }

    @PostMapping("json")
    public JSONObject json(@RequestBody JSONObject jsonObject) {
        JSONObject result = new JSONObject();
        result.put("aaa", "方法返回参数");
        return jsonObject;
    }
}
