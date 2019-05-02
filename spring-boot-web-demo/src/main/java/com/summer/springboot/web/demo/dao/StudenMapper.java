package com.summer.springboot.web.demo.dao;

import com.summer.springboot.web.demo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudenMapper {

    List<Student> qryAll();
}
