package com.summer.springboot.shiro.demo.service;

import com.summer.springboot.shiro.demo.dao.StudenMapper;
import com.summer.springboot.shiro.demo.pojo.Student;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {
    @Resource
    private StudenMapper studenMapper;

    public List<Student> qryAll() {
        return studenMapper.qryAll();
    }

    @Cacheable(value = "data", key = "'Student-' + #id")
    public Student getStudentById(Long id) {
        return studenMapper.getStudentById(id);
    }

    public int insertStudent(Student student) {
        return studenMapper.insertStudent(student);
    }
}
