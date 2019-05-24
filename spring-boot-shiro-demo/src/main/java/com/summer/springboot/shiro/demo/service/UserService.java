package com.summer.springboot.shiro.demo.service;

import com.summer.springboot.shiro.demo.dao.UserMapper;
import com.summer.springboot.shiro.demo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
