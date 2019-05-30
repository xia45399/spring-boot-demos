package com.summer.springboot.mybatis.demo.pojo;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String productName;
    private Long price;
}
