package com.dirge.config;

import com.auth0.jwt.interfaces.Claim;
import com.dirge.utils.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 拦截器
 */
public class UserInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String token = request.getHeader("Authorization");
        boolean map = JWTUtil.verify(token);
        if(map){
            return false;
        }
        return true;
    }
}
