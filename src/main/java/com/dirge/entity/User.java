package com.dirge.entity;

import org.springframework.stereotype.Component;
import javax.persistence.*;

@Component
//加载自定义配置文件，如果字段名一样默认会加载application.properties
//@PropertySource("classpath:application-dev.properties")
public class User {
    //@Value("${u.id}")
    @Id
    private Integer id;
    //@Value("${u.name}")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
