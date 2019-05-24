package com.summer.springboot.shiro.demo.dao;

import com.summer.springboot.shiro.demo.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    User getUserByUsername(String username);
}
