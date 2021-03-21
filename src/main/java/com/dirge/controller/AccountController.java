package com.dirge.controller;

import com.dirge.config.ResultData;
import com.dirge.constants.ResponseMessage;
import com.dirge.constants.Role;
import com.dirge.entity.PO.PermissionCode;
import com.dirge.entity.User;
import com.dirge.mapper.UserMapper;
import com.dirge.service.UserService;
import com.dirge.utils.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
@Api("补偿和扣除规则")
public class AccountController {

    private Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param user
     * @return
     */
    @PostMapping("/regist")
    @ApiOperation(value = "账户注册")
    public ResultData regist(User user){
        String id = UUID.randomUUID().toString();
        if(StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassWord())){
            return ResultData.error(ResponseMessage.ACCOUNT_OR_PASSWORD_NULL);
        }
        if(userMapper.getUserByUserName(user.getUserName())!=null){
            return ResultData.error(ResponseMessage.NAMEUSED);
        }
        user.setId(id);
        user.setCreateDate(new Date());
        user.setUpdateDate(new Date());
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
        if(result){
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
    @ApiOperation(value = "账户登录")
    public ResultData login(User user){
        Condition condition = new Condition(User.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("userName",user.getUserName());
        criteria.andEqualTo("passWord",user.getPassWord());
        User user1 = userMapper.selectOneByExample(condition);
        if(user1==null){
            return ResultData.error(ResponseMessage.ACCOUNT_OR_PASSWORD_EXIST);
        }
        //生成token
        String token = JWTUtil.createToken(user1);
        logger.info("token-{}",token);
        return ResultData.ok(token);
    }

    /**
     * 用户修改信息
     * @param user
     * @return
     */
    @PostMapping("/edit")
    @ApiOperation(value = "账户修改信息")
    public ResultData edit(User user){
        Condition condition  = new Condition(User.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("userName",user.getUserName());
        List<User> userList = userMapper.selectByCondition(condition);
        //如果数据库存在更新的名字，则更新失败
        if(userList.get(0).getUserName().equals(user.getUserName())){
            return  ResultData.error(ResponseMessage.USERNAME_REPEAT_USED);
        }
        userMapper.insertSelective(user);
        return ResultData.ok();
    }
}
