package com.dirge.controller;

import com.dirge.entity.PO.PermissionCode;
import com.dirge.entity.User;
import com.dirge.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.time.LocalDateTime;
import java.util.UUID;

@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @PostMapping("/regist")
    public String regist(User user){
        String id = UUID.randomUUID().toString();
        user.setId(id);
        user.setCreateDate(LocalDateTime.now());
        user.setUpdateDate(LocalDateTime.now());
        boolean flag = userService.addUser(user);
        if(flag){
            return "success";
        } else {
            return "用户名已经被占用，请重新注册！！！";
        }
    }

    @PostMapping("/login")
    public String login(User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassWord());
        subject.login(token);
        User user1 = userService.getUser(user.getUserName(),user.getPassWord());
        subject.getSession().setAttribute("user",user1);
        return "登录成功";
    }
}
