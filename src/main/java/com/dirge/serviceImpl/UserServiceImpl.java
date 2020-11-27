package com.dirge.serviceImpl;

import com.dirge.entity.PO.PermissionCode;
import com.dirge.entity.User;
import com.dirge.mapper.UserMapper;
import com.dirge.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User getUser(String userName, String passWord) {
        User user = userMapper.getUser(userName,passWord);
        return user;
    }

    @Override
    public boolean addUser(User user) {
        Condition condition = new Condition(User.class);
        condition.createCriteria().andEqualTo("userName",user.getUserName());
        List<User> users = userMapper.selectByCondition(condition);
        if(users.size()>1){
            return false;
        }
        //设置角色和权限
        user.setRole("admin");
        List<String> permission = new ArrayList<>();
        permission.add(PermissionCode.CREATE);
        permission.add(PermissionCode.UPDATE);
        permission.add(PermissionCode.DELETE);
        permission.add(PermissionCode.READ);
        user.setPermission(StringUtils.join(permission,","));
        userMapper.insertSelective(user);
        return true;
    }

    @Override
    public User getUserByUserName(String userName) {
        User user = userMapper.getUserByUserName(userName);
        return user;
    }
}
