package com.summer.springboot.mybatis.demo.service;

import com.summer.springboot.mybatis.demo.dao.ProductMapper;
import com.summer.springboot.mybatis.demo.pojo.Product;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ProductService {
    @Resource
    private ProductMapper productMapper;

    public int create(Product product) {
        return productMapper.create(product);
    }
}
