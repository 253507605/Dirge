package com.dirge.controller;

import com.dirge.entity.Music;
import com.dirge.entity.User;
import com.dirge.service.HelloService;
import com.dirge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/say")
    public String sayHello(){
        //User user = userService.getUser(1);
        System.out.println(user.getId());
        System.out.println(music.getName());
        helloService.say();
        return "hello";
    }
}


