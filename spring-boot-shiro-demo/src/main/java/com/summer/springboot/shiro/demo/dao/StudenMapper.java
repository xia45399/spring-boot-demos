package com.summer.springboot.shiro.demo.dao;


import com.summer.springboot.shiro.demo.pojo.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudenMapper {

    List<Student> qryAll();

    Student getStudentById(Long id);

    int insertStudent(Student student);
}
