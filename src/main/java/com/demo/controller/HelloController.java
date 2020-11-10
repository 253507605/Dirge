package com.demo.controller;

import com.demo.eneity.User;
import com.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/say")
    public String sayHello(){
        User user = userMapper.getUser(1);
        System.out.println(user.getId()+" "+user.getName());
        return "hello";
    }
}


