package com.summer.springboot.mybatis.demo.dao;

import com.summer.springboot.mybatis.demo.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    int create(Product product);
}
