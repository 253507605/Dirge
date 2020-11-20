package com.dirge.entity;

import org.springframework.stereotype.Component;
import javax.persistence.*;

@Component
//加载自定义配置文件，如果字段名一样默认会加载application.properties
//@PropertySource("classpath:application-dev.properties")
public class User {
    //@Value("${u.id}")
    @Id
    private String id;
    //@Value("${u.name}")
    private String userName;

    private String passWord;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
}
