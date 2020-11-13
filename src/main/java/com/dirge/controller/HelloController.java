package com.dirge.controller;

import com.dirge.entity.Music;
import com.dirge.entity.User;
import com.dirge.service.HelloService;
import com.dirge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {

    @Autowired
    public UserService userService;

    @Autowired
    private User user;

    @Autowired
    private Music music;

    @Autowired
    private HelloService helloService;

    @Autowired
    public StringRedisTemplate redisTemplate;

    @GetMapping("/say")
    public String sayHello(HttpServletRequest request){
        //User user = userService.getUser(1);
        System.out.println(user.getId());
        System.out.println(music.getName());
        helloService.say();
        redisTemplate.opsForValue().set("id","cjl");
        System.out.println(redisTemplate.opsForValue().get("id"));
        return "hello";
    }

    @PostMapping("/add")
    public String addUser(User user,HttpServletRequest request){
        System.out.println(user.getId());
        System.out.println(user.getName());
        return "success";
    }
}


