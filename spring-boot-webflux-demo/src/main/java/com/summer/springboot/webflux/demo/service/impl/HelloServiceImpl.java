package com.summer.springboot.webflux.demo.service.impl;

import com.summer.springboot.webflux.demo.service.HelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello() {
        return "this is say hello";
    }

}
