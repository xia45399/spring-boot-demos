package com.summer.springboot.demo;

import com.summer.springboot.demo.service.HelloService;
import com.summer.springboot.demo.service.impl.HelloServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        HelloServiceImpl helloServiceImpl = new HelloServiceImpl();
        InvocationHandler handler = new HelloProxy(helloServiceImpl);

        HelloService helloService1 = (HelloService) Proxy.newProxyInstance(handler.getClass().getClassLoader(), helloServiceImpl.getClass().getInterfaces(), handler);
        helloService1.aaa();

        SpringApplication.run(DemoApplication.class, args);
    }

}
