package com.summer.springboot.redis.demo.controller;

import com.summer.springboot.redis.demo.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("getUser")
    public Long getUser() {
        userService.getUser(1L);
        userService.getUser2(2L);
        return System.currentTimeMillis();
    }

}
