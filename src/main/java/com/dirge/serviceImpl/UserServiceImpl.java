package com.dirge.serviceImpl;

import com.dirge.entity.User;
import com.dirge.mapper.UserMapper;
import com.dirge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(Integer id) {
        User user = userMapper.getUser(1);
        return user;
    }
}
