package com.dirge.serviceImpl;

import com.dirge.constants.Role;
import com.dirge.entity.PO.PermissionCode;
import com.dirge.entity.User;
import com.dirge.mapper.UserMapper;
import com.dirge.service.UserService;
import com.dirge.utils.SendEmail;
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
        //设置角色和权限
        List<String> permission = new ArrayList<>();
        if(user.getRole().equals(Role.ADMIN)){
            permission.add(PermissionCode.READ);
            permission.add(PermissionCode.UPDATE);
            permission.add(PermissionCode.CREATE);
            permission.add(PermissionCode.DELETE);
        }else {
            permission.add(PermissionCode.READ);
        }
        user.setPermission(StringUtils.join(permission,","));
        boolean result = userMapper.insertSelective(user) == 1?true:false;
        return result;
    }

    @Override
    public User getUserByUserName(String userName) {
        Condition condition = new Condition(User.class);
        condition.createCriteria().andEqualTo("userName",userName);
        User user = userMapper.selectOneByExample(condition);
        return user;
    }
}
