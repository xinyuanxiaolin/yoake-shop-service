package com.shop.service.exception;

import com.shop.service.pojo.Result;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
* 全局异常处理器
* */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class) //捕获所有异常
    public Result ex(Exception exception){
        exception.printStackTrace();

        return  Result.error("对不起,操作失败,请联系管理员!");
    }

}
