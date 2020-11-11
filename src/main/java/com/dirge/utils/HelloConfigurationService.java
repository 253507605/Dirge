package com.dirge.utils;

import com.dirge.entity.HelloProperties;
import com.dirge.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(HelloProperties.class)
@ConditionalOnClass(HelloService.class)
public class HelloConfigurationService {
    @Autowired
    private HelloProperties helloProperties;

    @Bean
    HelloService helloService(){
        HelloService helloService = new HelloService();
        helloService.setMsg(helloProperties.getMsg());
        helloService.setName(helloProperties.getName());
        return helloService;
    }
}
