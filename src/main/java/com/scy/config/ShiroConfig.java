package com.scy.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean("securityManager")
    public SecurityManager securityManager(MyRealm realm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setRememberMeManager(null);
        return securityManager;
    }

    @Bean("shiroFilter")
        public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager, MyShiroFilter filter){
        ShiroFilterFactoryBean shiroFilter1=new ShiroFilterFactoryBean();
        shiroFilter1.setSecurityManager(securityManager);

        Map<String , Filter> map=new HashMap<>();
        map.put("oauth2",filter);//添加自定义过滤
        shiroFilter1.setFilters(map);
        /**授权资源**/
        Map<String,String> filterMap=new LinkedHashMap<>();
       /* filterMap.put("/swagger/**", "anon");
        filterMap.put("/swagger-ui.html", "anon");
        filterMap.put("/swagger-resources/**", "anon");
        filterMap.put("/user/register", "anon");
        filterMap.put("/user/login", "anon");*/
        filterMap.put("/user/register", "anon");
        filterMap.put("/user/login", "anon");
        filterMap.put("/**", "anon");//oauth2意味着所有的请求都要经过我们配置的过滤(就是对请求的一个格式转换和token的验证)在这之后就会在加一个权限的判定资源的定向 在指定的资源下 用anon是所有的用户都可以实现调用
//filterMap.put("/**", "oauth2") 表示剩下的所有资源都要进行一个过滤 放在最后边 防止拦截所有的资源
        shiroFilter1.setFilterChainDefinitionMap(filterMap);

        return shiroFilter1;

    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor advisor=new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }




}
