package com.dirge.entity;

import org.springframework.context.annotation.ImportResource;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


//加载自定义配置文件，如果字段名一样默认会加载application.properties
//@PropertySource("classpath:application-dev.properties")
public class User {
    //@Value("${u.id}")
    @Id
    private String id;
    //@Value("${u.name}")
    private String userName;

    private String passWord;

    private String email;

    private String role = "common";

    private String permission;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
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
