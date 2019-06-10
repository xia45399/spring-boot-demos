package com.summer.springboot.mybatis.demo.common;

/**
 * 错误码枚举类
 */
public enum ResultCode {
    SUCCESS("200", "操作成功"),
    FAILED("500", "操作失败");

    private String code;
    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
