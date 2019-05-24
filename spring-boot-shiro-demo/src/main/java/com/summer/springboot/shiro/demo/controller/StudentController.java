package com.summer.springboot.shiro.demo.controller;

import com.summer.springboot.shiro.demo.pojo.Student;
import com.summer.springboot.shiro.demo.service.StudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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

    @RequestMapping("/testAnon")
    public Object testAnon() {
        return "该接口拦截但是不鉴权";
    }

    @RequiresPermissions("student:getStudentById")
    @RequestMapping("/getStudentById")
    public Student getStudentById(long id) {
        return studentService.getStudentById(id);
    }

    @RequiresPermissions("student:qryAll")
    @RequestMapping("/qryAll")
    public Object qryAll() {
        return studentService.qryAll();
    }

    @RequiresPermissions("student:addStudent")
    @RequestMapping("/addStudent")
    public int addStudent(@Validated @RequestBody Student student) {
        int count = studentService.insertStudent(student);
        return count;
    }
}
