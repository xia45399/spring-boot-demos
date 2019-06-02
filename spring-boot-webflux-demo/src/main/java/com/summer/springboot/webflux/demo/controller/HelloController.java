package com.summer.springboot.webflux.demo.controller;

import com.summer.springboot.webflux.demo.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.RouterFunctions;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.Callable;

@RestController
@RequestMapping
public class HelloController {
    @Resource
    private HelloService helloService;
    @GetMapping("/hello")
    public Mono<String> hello() {
        System.out.println(new Date());
        return Mono.just("Welcome to reactive world ~");
    }

    public Mono<String> helloj() {
        Mono<String> result = Mono.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                System.out.println("请求时间=" + new Date());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return helloService.sayHello();
            }
        });
        return result;
    }
}
