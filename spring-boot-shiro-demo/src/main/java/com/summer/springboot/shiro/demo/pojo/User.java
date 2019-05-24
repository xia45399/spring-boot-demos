package com.summer.springboot.shiro.demo.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private String username;
    private String password;
}
