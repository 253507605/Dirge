package com.dirge.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
//@PropertySource("application-dev.properties")
//加载前缀为music的属性值，不需要用@value注解进行赋值
@ConfigurationProperties(prefix = "music")
public class Music {

    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
