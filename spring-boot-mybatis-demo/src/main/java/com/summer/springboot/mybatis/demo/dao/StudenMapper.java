package com.summer.springboot.mybatis.demo.dao;

import com.summer.springboot.mybatis.demo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudenMapper {

    List<Student> qryAll();
}
