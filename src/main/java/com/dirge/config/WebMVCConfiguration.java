package com.dirge.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //把自定义的拦截器添注册到MVC，对所有请求都拦截
        registry.addInterceptor(new UserInterceptor())
                .excludePathPatterns("/login","/regist")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/swagger-ui.html/**","/static/**","/")
                .addPathPatterns("/**");
    }
}
