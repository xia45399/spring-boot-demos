package com.summer.springboot.web.demo.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    @Bean
    Queue testQueue() {
        return new Queue("testQueue", true, false, true);
    }
}
