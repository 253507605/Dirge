package com.dirge.utils;

import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthorizedException;
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

    //认证失败异常处理
    @org.springframework.web.bind.annotation.ExceptionHandler(UnknownAccountException.class)
    public String handleException1(UnknownAccountException e){
        //logger.debug(e.getMessage());
        return "403";
    }

    //拦截未授权页面
    @org.springframework.web.bind.annotation.ExceptionHandler(UnauthorizedException .class)
    public String handleException(UnauthorizedException  e) {
        logger.debug(e.getMessage());
        return "405";
    }
}
