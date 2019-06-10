package com.summer.springboot.mybatis.demo.dao;

import com.summer.springboot.mybatis.demo.pojo.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    int insert(Product product);

    int insertProductDetail(Long id, long time);

    List<Product> list(Product pro);

}
