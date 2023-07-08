package com.study.ssyx.common.exception;

import com.study.ssyx.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//Aop 面向切面
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody //返回Json数据
    public Result error(Exception e){
        e.printStackTrace();
        return Result.fail(null);
    }
    //自定义异常处理
    @ExceptionHandler(SsyxException.class)
    @ResponseBody
    public  Result error(SsyxException ssyxException){
        return  Result.build(null,ssyxException.getCode(),ssyxException.getMessage());
    }
}
