package com.summer.ssm.controller;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cgi/test")
public class TestController {

    @RequestMapping("test1")
    public Object test() {
        return "a";
    }

    @RequestMapping("test2")
    public Object test2(Long a) {
        return a;
    }

    @RequestMapping("test3")
    public Object test3() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "中文");
        map.put("c", 1);
        return map;
    }

    @Resource
    private DruidDataSource druidDataSource;

    @RequestMapping("testDbPool")
    public Object testDbPool() throws SQLException {
        Connection con = druidDataSource.getConnection();
        long closeCount = druidDataSource.getCloseCount();
        con.close();
        long count = druidDataSource.getConnectCount();
        return count + "=" + closeCount;
    }
}
