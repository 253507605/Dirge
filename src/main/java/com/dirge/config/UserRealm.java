package com.dirge.config;

import com.dirge.entity.User;
import com.dirge.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import tk.mybatis.mapper.entity.Condition;

public class UserRealm extends AuthorizingRealm {

    private Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    //权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("----执行shiro权限获取------");
        String  username = (String) principals.getPrimaryPrincipal();
        //记录用户的所有角色和权限
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = userService.getUserByUserName(username);
        authorizationInfo.addRole(user.getRole());
        authorizationInfo.addStringPermission(user.getPermission());
        System.out.println(authorizationInfo.getStringPermissions().size());
        return authorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        logger.info("----执行shiro认证------");
        String userName = (String) token.getPrincipal();
        String passWord = new String((char[]) token.getCredentials());
        User user = userService.getUser(userName,passWord);
        if(null == user){
            throw new UnknownAccountException("用户不存在");
        }
        logger.info("----shiro认证成功------");
        return new SimpleAuthenticationInfo(userName,passWord,getName());
    }
}
