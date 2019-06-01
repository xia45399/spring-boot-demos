package com.summer.springboot.mybatis.demo.controller;

import com.summer.springboot.mybatis.demo.pojo.Product;
import com.summer.springboot.mybatis.demo.service.ProductService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("product")
public class ProductController {
    @Resource
    private ProductService productService;

    @RequestMapping("/create")
    public Object create(@RequestBody Product product) {
        int a = productService.create(product);
        return a;
    }
}
