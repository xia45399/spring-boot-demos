package com.summer.springboot.web.demo.pojo;

import com.summer.springboot.web.demo.validator.FlagValidator;
import lombok.Data;

import java.io.Serializable;

@Data
public class Student implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    @FlagValidator(value = {"0", "1", "2"}, message = "性别只能为0,1,2")
    private Integer sex;
    private String className;
}
