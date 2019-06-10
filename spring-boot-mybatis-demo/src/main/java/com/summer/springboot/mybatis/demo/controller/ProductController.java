package com.summer.springboot.mybatis.demo.controller;

import com.github.pagehelper.PageHelper;
import com.summer.springboot.mybatis.demo.common.CommonPage;
import com.summer.springboot.mybatis.demo.common.CommonResult;
import com.summer.springboot.mybatis.demo.pojo.Product;
import com.summer.springboot.mybatis.demo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Resource
    private ProductService productService;

    @PostMapping("/create")
    public Object create(@RequestBody Product product) {
        int a = productService.create(product);
        return a;
    }

    @PostMapping("/pageList")
    public Object pageList(Product product, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> list = productService.list(product);
        CommonPage<Product> result = CommonPage.restPage(list);
        return CommonResult.success(result);
    }
}
