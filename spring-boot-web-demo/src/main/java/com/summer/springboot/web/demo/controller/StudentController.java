package com.summer.springboot.web.demo.controller;

import com.summer.springboot.web.demo.pojo.Student;
import com.summer.springboot.web.demo.service.StudentService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("student")
public class StudentController {
    @Resource
    private StudentService studentService;

    @RequestMapping("/qryAll")
    public Object qryAll() {
        return studentService.qryAll();
    }

    @RequestMapping("/getStudentById")
    public Student getStudentById(long id) {
        return studentService.getStudentById(id);
    }

    @RequestMapping("/addStudent")
    public int c(@Validated @RequestBody Student student) {
        int count = studentService.insertStudent(student);
        return count;
    }
}
