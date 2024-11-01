package com.example.daptech.excepetion;

//自定义Custom异常，处理用户输入异常问题
public class CustomException extends RuntimeException {
    private int code;

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }
    public int getCode() {
        return code;
    }
}
