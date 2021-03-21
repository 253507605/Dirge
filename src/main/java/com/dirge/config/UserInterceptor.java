package com.dirge.config;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
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
        if(token == null){
            logger.info("用户未登录");
            return false;
        }
        DecodedJWT jwt = JWTUtil.verify(token);
        if(jwt.getClaims()==null){
            return false;
        }
        request.setAttribute("username",jwt.getClaims().get("name").asString());
        return true;
    }
}
