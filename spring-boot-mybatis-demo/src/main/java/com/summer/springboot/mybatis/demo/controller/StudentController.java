package com.summer.springboot.mybatis.demo.controller;

import com.summer.springboot.mybatis.demo.service.StudentService;
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
}
