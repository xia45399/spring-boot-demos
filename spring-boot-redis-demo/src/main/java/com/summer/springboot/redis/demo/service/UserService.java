package com.summer.springboot.redis.demo.service;

import com.summer.springboot.redis.demo.pojo.User;

public interface UserService {
    User getUser(Long id);
}
