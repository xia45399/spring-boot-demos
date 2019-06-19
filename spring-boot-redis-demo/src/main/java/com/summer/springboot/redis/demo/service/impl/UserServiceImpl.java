package com.summer.springboot.redis.demo.service.impl;

import com.summer.springboot.redis.demo.pojo.User;
import com.summer.springboot.redis.demo.service.UserService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    @Cacheable(value = "data", key = "'User-' + #id")
    public User getUser(Long id) {
        System.out.println("只能看到一次,再打印就不科学了");
        return new User(id, "name" + id, 12, id % 2 == 1);
    }

}
