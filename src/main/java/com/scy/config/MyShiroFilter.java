package com.scy.config;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.scy.utils.JwtUtils;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/*shro过滤器*/
@Component
@Scope("prototype")
public class MyShiroFilter extends AuthenticatingFilter {

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req= (HttpServletRequest) request;
        //options方法不执行
        //除了options请求之外 所有的请求都交给shiro处理
        return req.getMethod().equals(RequestMethod.OPTIONS.name());//为true shiro 不处理
    }
/**
 * 获取前端的token验证和映射到shiro**/
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        String token=getRequestToken(req);
        if(StrUtil.isBlank(token)){
            return null;
        }
        return new OAuth2Token(token);
    }
/**
 * 被拒绝的时候**/
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        HttpServletResponse resp= (HttpServletResponse) servletResponse;
        /*threadLocalToken.clear();*/
        String token=getRequestToken(req);
        if(StrUtil.isBlank(token)){
            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
            resp.getWriter().print("无效的令牌");
            return false;
        }
        try{
            jwtUtils.verifierToken(token);
        }catch (TokenExpiredException e){
          /*  if(redisTemplate.hasKey(token)){
                redisTemplate.delete(token);
                int userId=jwtUtil.getUserId(token);
                token=jwtUtil.createToken(userId);
                redisTemplate.opsForValue().set(token,userId+"",cacheExpire, TimeUnit.DAYS);
                threadLocalToken.setToken(token);
            }
            else{
                resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
                resp.getWriter().print("令牌已过期");
                return false;
            }*/
        }catch (Exception e){
            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
            resp.getWriter().print("无效的令牌");
            return false;
        }
        boolean bool=executeLogin(servletRequest,servletResponse);
        return bool;
    }
/**
 * 获取前端的token
 * **/
    private String getRequestToken(HttpServletRequest request){
        String token=request.getHeader("token");
        if(StrUtil.isBlank(token)){
            token=request.getParameter("token");
        }
        return token;
    }

    /**
     * 登录失败时候逻辑处理
     *      在执行过滤器链的内部逻辑中进行处理，设置适当的响应头信息。
     *      @param request Servlet 请求
     *      @param response Servlet 响应 **/
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp= (HttpServletResponse) response;
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
        try{
            resp.getWriter().print(e.getMessage());
        }catch (Exception exception){

        }

        return false;
    }

    /**
     * 在执行过滤器链的内部逻辑中进行处理，设置适当的响应头信息。
     * @param request Servlet 请求
     * @param response Servlet 响应
     * @param chain 过滤器链
     * @throws ServletException 在处理过滤器链时可能抛出的异常
     **/
    @Override
    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp= (HttpServletResponse) response;
        super.doFilterInternal(request, response, chain);

    }

}
