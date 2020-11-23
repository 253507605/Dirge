package com.dirge.controller;

import com.dirge.entity.User;
import com.dirge.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String doLogin(User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassWord());
        subject.login(token);
        return "登录成功";
    }

    @GetMapping("/hello")
    public String hello(HttpSession session){
        System.out.println(session.getAttribute("user"));
        return "hello";
    }

    @GetMapping("/doLogin")
    public String login(){
        return "please login!";
    }

    @PostMapping("/regist")
    public String regist(User user){
        String id = UUID.randomUUID().toString();
        user.setId(id);
        boolean flag = userService.addUser(user);
        if(flag){
            return "success";
        } else {
            return "用户名已经被占用，请重新注册！！！";
        }
    }
}
