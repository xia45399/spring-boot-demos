package com.summer.springboot.shiro.demo.service;

import com.summer.springboot.shiro.demo.dao.UserMapper;
import com.summer.springboot.shiro.demo.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public Set<String> listUserPermissions(String username) {
        List<String> list = userMapper.listUserPermissions(username);
        Set<String> set = new HashSet<>(list);
        return set;
    }
}
