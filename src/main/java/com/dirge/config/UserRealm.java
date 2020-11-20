package com.dirge.config;

import com.dirge.entity.User;
import com.dirge.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        String passWord = new String((char[]) token.getCredentials());
        User user = userService.getUser(userName,passWord);
        if(null == user){
            throw new UnknownAccountException("用户不存在");
        }
        return new SimpleAuthenticationInfo(userName,passWord,getName());
    }
}
