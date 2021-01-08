package com.dirge.serviceImpl;

import com.dirge.entity.PO.PermissionCode;
import com.dirge.entity.User;
import com.dirge.mapper.UserMapper;
import com.dirge.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

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
        Condition condition = new Condition(User.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("userName",userName);
        criteria.andEqualTo("passWord",passWord);
        User user = userMapper.selectOneByExample(condition);
        return user;
    }

    @Override
    public boolean addUser(User user) {
        Condition condition = new Condition(User.class);
        condition.createCriteria().andEqualTo("userName",user.getUserName());
        List<User> users = userMapper.selectByCondition(condition);
        if(CollectionUtils.isNotEmpty(users)){
            return false;
        }
        //设置角色和权限
        List<String> permission = new ArrayList<>();
        permission.add(PermissionCode.READ);
        user.setPermission(StringUtils.join(permission,","));
        userMapper.insertSelective(user);
        return true;
    }

    @Override
    public User getUserByUserName(String userName) {
        Condition condition = new Condition(User.class);
        condition.createCriteria().andEqualTo("userName",userName);
        User user = userMapper.selectOneByExample(condition);
        return user;
    }
}
