package com.summer.springboot.stop.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig {

    @Bean
    Queue listenQueue() {
        return new Queue("listenQueue", true);
    }

    @Bean
    Queue respQueue() {
        return new Queue("respQueue", true);
    }


}
