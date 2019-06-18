package com.summer.springboot.redis.demo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Long id;
    private String name;
    private Integer age;
    private Boolean delete;

    public User(Long id, String name, int age, boolean delete) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.delete = delete;
    }
}
