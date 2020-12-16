package com.dirge.controller;

import com.dirge.entity.PO.PermissionCode;
import com.dirge.entity.User;
import com.dirge.service.UserService;
import com.dirge.utils.MqSender;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class LoginController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Autowired
    private MqSender mqSender;

    @PostMapping("/login")
    public String doLogin(User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),user.getPassWord());
        subject.login(token);
        User user1 = userService.getUser(user.getUserName(),user.getPassWord());
        subject.getSession().setAttribute("user",user1);
        return "登录成功";
    }

    @GetMapping("/hello")
    @RequiresPermissions(PermissionCode.UPDATE)
    public String hello(){
        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.getPrincipal());
        System.out.println(subject.getSession().getAttribute("user"));
        System.out.println(subject.isAuthenticated());
        return "hello";
    }

    @GetMapping("/doLogin")
    @RequiresRoles("admin")
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

    @GetMapping("/sendMq")
    public String sendMq(){
        mqSender.send();
        return "success";
    }
}
