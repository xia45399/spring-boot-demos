package com.summer.springboot.demo;

import com.summer.springboot.demo.service.HelloService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloProxy implements InvocationHandler {

    private Object object;

    public HelloProxy(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before say hello");
        method.invoke(object);
        System.out.println("after say hello");
        return null;
    }
}
