package com.example.daptech.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//这是返回前端的统一返回类
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    public static final String SUCCESS_MSG="success";

    private Integer code;//响应码，1 代表成功; 0 代表失败 (2 代表用户名密码等格式错误)
    private String msg;  //响应信息 描述字符串
    private T data; //返回的数据

    //成功响应 不返回数据
    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 1;
        result.msg = SUCCESS_MSG;
        return result;
    }

    //成功响应 返回数据
    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<T>();
        result.code = 1;
        result.msg = SUCCESS_MSG;
        result.data = data;
        return result;
    }

    //失败响应
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<T>();
        result.code = 0;
        result.msg = msg;
        return result;
    }

    //格式错误响应
    public static <T> Result<T> formatError(String msg) {
        Result<T> result = new Result<T>();
        result.code = 2;
        result.msg = msg;
        return result;
    }
}