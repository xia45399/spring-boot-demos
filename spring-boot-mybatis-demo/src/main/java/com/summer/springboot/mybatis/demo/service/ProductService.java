package com.summer.springboot.mybatis.demo.service;

import com.summer.springboot.mybatis.demo.dao.ProductMapper;
import com.summer.springboot.mybatis.demo.pojo.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductService {
    @Resource
    private ProductMapper productMapper;

    @Transactional
    public int create(Product product) {
        int num = productMapper.insert(product);
        long now = System.currentTimeMillis() / 1000;
        productMapper.insertProductDetail(product.getId(), now);
        return num;
    }

    public List<Product> list(Product product) {
        return productMapper.list(product);
    }
}
