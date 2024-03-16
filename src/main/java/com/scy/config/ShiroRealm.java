package com.scy.config;

import cn.hutool.http.HttpRequest;
import com.scy.mapper.UserMapper;
import com.scy.pojo.User;
//import com.soyuan.bigdata.heartbeat.common.CommonConstant;
//import com.soyuan.bigdata.heartbeat.entity.LoginUser;
//import com.soyuan.bigdata.heartbeat.utils.JwtUtil;
//import com.soyuan.bigdata.heartbeat.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * @author tangwx@soyuan.com.cn
 * @date 2020/5/15 10:31
 */
@Component
@Slf4j
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private  ThreadLocalToken threadLocalToken;

    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.info("===============Shiro权限认证开始============ [ roles、permissions]==========");
//        String username = null;
        if (principals != null) {
            User sysUser = (User) principals.getPrimaryPrincipal();
//            String email = sysUser.getUsername();
//            User user = userMapper.selelctemail(email);
            try {
                // 执行可能引发 AuthorizationException 的操作
                Integer userid = sysUser.getUserid();
                Set<String> roles = userMapper.getUserRoles(userid);
                SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
                info.setRoles(roles);
                return  info;
            } catch (AuthorizationException e) {
                throw new UnauthorizedException("没有权限访问该资源"); // 抛出 Shiro 的 UnauthorizedException 异常
            }

        }


//
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//
//        // 设置用户拥有的角色集合，比如“admin,test”
//        //sysUserService.getUserRolesSet(username);从数据库中获取到用户所拥有的角色信息
//        Set<String> roleSet = new HashSet<>();
//        roleSet.add("admin");//模拟
//        roleSet.add("visit");
//        info.setRoles(roleSet);
//
//        // 设置用户拥有的权限集合，比如“sys:role:add,sys:user:add”
//        //sysUserService.getUserPermissionsSet(username);从数据库获取到用户的权限信息
//        Set<String> permissionSet = new HashSet<>();
//        permissionSet.add("sys:role:add");
//        permissionSet.add("sys:user:add");
//        info.addStringPermissions(permissionSet);
        log.info("===============Shiro权限认证成功==============");

        return null;
    }

    /**
     * 用户信息认证是在用户进行登录的时候进行验证(不存redis)
     * 也就是说验证用户输入的账号和密码是否正确，错误抛出异常
     *
     * @param token 用户登录的账号密码信息
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //这里getPrincipal和getCredentials是一样的
        //因为我们在
        // JwtToken jwtToken = new JwtToken(token);
        // getSubject(request, response).login(jwtToken);
        // 提交的是JwtToken，jwtToken中Principal和Credentials都是token
        String credentials = (String) token.getCredentials();
        if (credentials == null) {
            log.info("————————relam身份认证失败——————————");
            throw new AuthenticationException("token为空!");
        }
        // 校验token有效性
        User loginUser = this.checkUserTokenIsEffect(credentials);
        //return SimpleAuthenticationInfo之后会进入到方法 assertCredentialsMatch(token, info);
        //该方法比较info中的Credential和token中的Credential，具体表现在
        //Object tokenHashedCredentials = hashProvidedCredentials(token, info);取出token中的Credential
        //Object accountCredentials = getCredentials(info);取出authenticationInfo中的Credential
        //equals(tokenHashedCredentials, accountCredentials);
        return new SimpleAuthenticationInfo(loginUser, credentials, getName());
    }

    /**
     * 校验token的有效性
     *
     * @param token
     */
    public User checkUserTokenIsEffect(String token) throws AuthenticationException {
        // 解密获得username，用于和数据库进行对比
        String email = JwtUtil.getUsername(token);
        if (email == null) {
            throw new AuthenticationException("token非法无效!");
        }

        // 查询用户信息
        log.debug("———校验token是否有效————checkUserTokenIsEffect——————— "+ token);
        //TODO 查询数据库，根据username得到loginUser
//        LoginUser loginUser = new LoginUser();//这里是模拟
//        loginUser.setId("admin");
//        loginUser.setUsername(username);
//        loginUser.setStatus(1);
//        loginUser.setPassword("123456");
        User user = userMapper.selelctemail(email);
        if (user == null) {
            throw new AuthenticationException("用户不存在!");
        }
        // 判断用户状态
//        if (user.getStatus() != 1) {
//            throw new AuthenticationException("账号已被锁定,请联系管理员!");
//        }
        // 校验token是否超时失效 & 或者账号密码是否错误
//        if (!jwtTokenRefresh(token, username, loginUser.getPassword())) {
//            throw new AuthenticationException("Token失效，请重新登录!");
//        }
       threadLocalToken.setToken(token);
        return user;
    }

    /**
     * TODO:
     * JWTToken刷新生命周期 （实现： 用户在线操作不掉线功能）
     * 1、登录成功后将用户的JWT生成的Token作为k、v存储到cache缓存里面(这时候k、v值一样)，缓存有效期设置为Jwt有效时间的2倍
     * 2、当该用户再次请求时，通过JWTFilter层层校验之后会进入到doGetAuthenticationInfo进行身份验证
     * 3、当该用户这次请求jwt生成的token值已经超时，但该token对应cache中的k还是存在，则表示该用户一直在操作只是JWT的token失效了，程序会给token对应的k映射的v值重新生成JWTToken并覆盖v值，该缓存生命周期重新计算
     * 4、当该用户这次请求jwt在生成的token值已经超时，并在cache中不存在对应的k，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
     * 注意： 前端请求Header中设置Authorization保持不变，校验有效性以缓存中的token为准。
     *       用户过期时间 = Jwt有效时间 * 2。
     * @param token
     * @param userName
     * @param passWord
     * @return
     */
//    public boolean jwtTokenRefresh(String token, String userName, String passWord){
//        String cacheToken = String.valueOf(redisUtil.get(CommonConstant.PREFIX_USER_TOKEN + token));
//        if (StringUtils.isNotEmpty(cacheToken)) {
//            // 校验token有效性
//            if (!JwtUtil.verify(cacheToken, userName, passWord)) {
//                String newAuthorization = JwtUtil.sign(userName, passWord);
//                // 设置超时时间
//                redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, newAuthorization);
//                redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME *2 / 1000);
//                log.info("——————————用户在线操作，更新token保证不掉线—————————jwtTokenRefresh——————— "+ token);
//            }
//            return true;
//        }
//        return false;
//    }
}

