package com.summer.springboot.shiro.demo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private Integer sex;
    private String className;
}
