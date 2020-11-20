package com.dirge.serviceImpl;

import com.dirge.entity.User;
import com.dirge.mapper.UserMapper;
import com.dirge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUser(String userName, String passWord) {
        User user = userMapper.getUser(userName,passWord);
        return user;
    }

    @Override
    public void addUser(User user) {
        userMapper.insertSelective(user);
    }
}
