package com.dirge.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 1.全局异常处理器
 * 2.全局数据绑定
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandler {

    private  static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public String exceptionHandle(Exception e){
        System.out.println("未知异常！！原因是：");
        e.printStackTrace();
        return e.getMessage();
    }
}
