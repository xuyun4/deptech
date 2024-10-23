/*
package com.example.deptech.excepetion;

import com.example.system.response.BaseResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常处理类,捕获所有异常
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)//捕获参数异常
    public BaseResponse<Void> argsException(MethodArgumentNotValidException exception) {
        //输出到控制台
        exception.printStackTrace();
        //返回参数异常信息到前端
        BaseResponse<Void> voidBaseResponse = new BaseResponse<Void>().construct("参数异常", false);
        return voidBaseResponse;
    }

    @ExceptionHandler(Exception.class)//捕获所有异常
    public BaseResponse<Void> Exception(Exception exception) {
        //输出到控制台
        exception.printStackTrace();
        //返回操作错误信息到前端
        BaseResponse<Void> voidBaseResponse = new BaseResponse<Void>().construct("操作失败", false);
        return voidBaseResponse;
    }
}
*/
