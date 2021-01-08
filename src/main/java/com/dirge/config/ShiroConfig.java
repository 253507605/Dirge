package com.dirge.config;



import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public UserRealm getUserRealm(){
        return new UserRealm();
    }

    @Bean
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(getUserRealm());
        return manager;
    }

//    /**
//     * 过滤器，实现对请求的拦截
//     * @return
//     */
//    @Bean
//    public ShiroFilterChainDefinition shiroFilterChainDefinition(){
//        DefaultShiroFilterChainDefinition definition =new DefaultShiroFilterChainDefinition();
//        definition.addPathDefinition("/login","anon");
//        definition.addPathDefinition("/regist","anon");
//        definition.addPathDefinition("/sendMq","anon");
//        definition.addPathDefinition("/**","authc");
//        //definition.addPathDefinition("/index","user");
//        return definition;
//    }


    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean() {
        // <1> 创建 ShiroFilterFactoryBean 对象，用于创建 ShiroFilter 过滤器
        ShiroFilterFactoryBean filterFactoryBean = new ShiroFilterFactoryBean();

        // <2> 设置 SecurityManager
        filterFactoryBean.setSecurityManager(securityManager());

        /*// <3> 设置 URL 们
        filterFactoryBean.setLoginUrl("/login"); // 登录 URL
        filterFactoryBean.setSuccessUrl("/login_success"); // 登录成功 URL
        filterFactoryBean.setUnauthorizedUrl("/unauthorized"); // 无权限 URL*/

        // <4> 设置 URL 的权限配置
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login","anon");
        filterMap.put("/regist","anon");
        filterMap.put("/doLogin","roles[admin]");
        filterMap.put("/**","authc");
        filterFactoryBean.setFilterChainDefinitionMap(filterMap);

        return filterFactoryBean;
    }
}
