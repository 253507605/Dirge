package com.dirge.controller;

import com.dirge.config.ResultData;
import com.dirge.constants.ResponseMessage;
import com.dirge.entity.User;
import com.dirge.service.UserService;
import com.dirge.utils.JWTUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Date;
import java.util.UUID;

@RestController
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/regist")
    public ResultData regist(User user){
        String id = UUID.randomUUID().toString();
        if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassWord())){
            return ResultData.error(ResponseMessage.ACCOUNT_OR_PASSWORD_NULL);
        }
        if(userService.getUserByUserName(user.getUserName())!=null){
            return ResultData.error(ResponseMessage.NAMEUSED);
        }
        user.setId(id);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
        boolean flag = userService.addUser(user);
        if(flag){
            return ResultData.ok();
        }else {
            return ResultData.error(ResponseMessage.REDIST_FAILURE);
        }
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResultData login(User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken uptoken = new UsernamePasswordToken(user.getUserName(),user.getPassWord());
        subject.login(uptoken);
        User user1 = userService.getUser(user.getUserName(),user.getPassWord());
        String token = JWTUtil.createToken(user1);
        logger.info("token-{}",token);
        subject.getSession().setAttribute("user",user1);
        return ResultData.ok(token);
    }
}
