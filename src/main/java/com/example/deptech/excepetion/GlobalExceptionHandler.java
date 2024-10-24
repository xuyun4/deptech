package com.example.deptech.excepetion;

import com.example.deptech.entity.Result;
import com.example.deptech.excepetion.CustomException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

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
@ControllerAdvice
@RestController
//全局异常处理类,捕获所有异常
public class GlobalExceptionHandler {

    //捕获自定义CustomException异常，通常为用户输入错误，返回http状态码为400
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), ex.getCode());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //捕获其他异常，通常为程序错误，返回http状态码为500
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), 500);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //捕获由Spring Validation验证失败抛出的异常，返回400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        ErrorResponse error = new ErrorResponse(errorMessage, 400);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    //内置类ErrorResponse
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorResponse {
        private String message;
        private int code;
    }
}

