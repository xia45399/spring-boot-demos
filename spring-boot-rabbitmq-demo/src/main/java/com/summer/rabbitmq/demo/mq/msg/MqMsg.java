package com.summer.rabbitmq.demo.mq.msg;

import lombok.Data;

import java.io.Serializable;

@Data
public class MqMsg implements Serializable {
    private String name;
    private Integer age;
    private Long time;
}
