package com.summer.springboot.aop.demo.dao;

import com.summer.springboot.aop.demo.pojo.SystemLog;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemLogMapper {
    int insert(SystemLog log);
}
