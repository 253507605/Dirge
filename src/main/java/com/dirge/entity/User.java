package com.dirge.entity;

import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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

    private String role;

    private String permission;

    private LocalDate createDate;

    private LocalDate updateDte;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", role='" + role + '\'' +
                ", permission='" + permission + '\'' +
                '}';
    }
}
