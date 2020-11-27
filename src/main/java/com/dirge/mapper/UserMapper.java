package com.dirge.mapper;

import com.dirge.base.AppMapper;
import com.dirge.entity.User;

public interface UserMapper extends AppMapper<User> {
    User getUser(String userName, String passWord);

    User getUserByUserName(String userName);
}
