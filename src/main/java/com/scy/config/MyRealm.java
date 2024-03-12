package com.scy.config;

import com.scy.mapper.UserMapper;
import com.scy.pojo.User;
import com.scy.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class MyRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    public void ShiroConfig() {
        logger.info("Relam类被 bean is created.");
    }
    @Autowired
    private JwtUtils jwtUtil;
    @Autowired
    UserMapper userMapper;
    /**授权**/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String token = (String) principals.getPrimaryPrincipal();
        int userId = jwtUtil.getUserId(token);
        log.info(String.valueOf(userId));

        Set<String> userRoles = userMapper.getUserRoles(userId);
        log.info("MYrelam 获取的用户角色：{}", userRoles);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        if (userRoles != null && !userRoles.isEmpty()) {
            info.setRoles(userRoles);
        } else {
            // 如果用户没有角色，则不添加任何角色信息，Shiro 会自动拒绝访问受保护资源
            log.error("User has no roles assigned. Access denied.");
        }

        return info;
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof OAuth2Token;
    }
    /**认证**/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String accessToken = (String) authenticationToken.getCredentials();

        // 验证 token 的有效性
        if (!jwtUtil.verifierToken(accessToken)) {
            throw new AuthenticationException("Invalid token");
        }

        // 获取用户信息
        int userId = jwtUtil.getUserId(accessToken);
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new LockedAccountException("Account locked, please contact the administrator");
        }

        return new SimpleAuthenticationInfo(user, accessToken, getName());

    }}
