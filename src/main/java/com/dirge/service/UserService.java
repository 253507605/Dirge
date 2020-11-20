package com.dirge.service;

import com.dirge.entity.User;

public interface UserService {
    User getUser(String userName ,String passWord);

    void addUser(User user);
}
