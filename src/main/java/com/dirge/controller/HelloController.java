package com.dirge.controller;

import com.dirge.entity.Music;
import com.dirge.entity.User;
import com.dirge.mapper.UserMapper;
import com.dirge.service.HelloService;
import com.dirge.service.UserService;
import com.dirge.utils.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public RedisTemplate redisTemplate;

    @Value("${spring.mail.username}")
    private String sender;

    @Autowired
    private SendEmail sendEmail;

    @Autowired
    private UserMapper userMapper;


    @GetMapping("/say")
    public String sayHello(HttpServletRequest request){
        //User user = userService.getUser(1);
        System.out.println(user.getId());
        System.out.println(music.getName());
        helloService.say();
        //redisTemplate.opsForValue().set("ids","cjl");
        System.out.println(redisTemplate.opsForValue().get("ids"));
        return "user";
    }

    @PostMapping("/add")
    public String addUser(User user,HttpServletRequest request){
        System.out.println(user.getId());
        System.out.println(user.getUserName());
        //userMapper.insertSelective(user);
        Condition condition = new Condition(User.class);
        condition.createCriteria().andEqualTo("id",1);
        List<User> userList = userMapper.selectByCondition(condition);
        System.out.println(userList.size());
        return "success";
    }

    @PostMapping("/sendMail")
    public String sendMail(){
        sendEmail.sendSimpleMail(sender,"1055856220@qq.com","springboot发送邮件","你好，这是我测试的发送邮件");
        System.out.println("nihao");
        return "success";
    }
}


