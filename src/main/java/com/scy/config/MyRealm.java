package com.scy.config;

import com.scy.mapper.UserMapper;
import com.scy.pojo.User;
import com.scy.utils.JwtUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class MyRealm extends AuthorizingRealm {
    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    UserMapper userMapper;
    /**授权**/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user= (User) principalCollection.getPrimaryPrincipal();
        int userId=user.getUserid();
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        return info;
    }
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }
    /**认证**/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String accessToken=(String)authenticationToken.getPrincipal();
        int userId=jwtUtil.getUserId(accessToken);
        User user1 = userMapper.selectById(userId);
        if(user1==null){
            throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user1,accessToken,getName());
        return info;
    }

}
