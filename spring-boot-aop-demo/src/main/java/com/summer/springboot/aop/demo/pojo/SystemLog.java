package com.summer.springboot.aop.demo.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class SystemLog {

    private Long id;

    /**
     * 请求地址
     */
    private String url;

    /**
     * URI
     */
    private String uri;

    /**
     * IP地址
     */
    private String ip;


    /**
     * 请求类型
     */
    private String method;

    /**
     * 操作时间
     */
    private Timestamp startTime;

    /**
     * 消耗时间
     */
    private Long spendTime;

    private String param;

    private String result;

}
