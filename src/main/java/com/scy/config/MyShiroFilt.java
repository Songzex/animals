package com.scy.config;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.scy.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
@Component
@Scope("prototype")
@Slf4j
public class MyShiroFilt extends AuthenticatingFilter {
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        // 获取请求中的认证令牌
        AuthenticationToken token = createToken(request, response);
        if (token == null) {
            // 如果令牌为空，返回认证失败的结果
            return onLoginFailure(null, new AuthenticationException("Token is null"), request, response);
        }

        try {
            // 获取 Subject 对象
            Subject subject = getSubject(request, response);
            // 调用 Subject 的 login 方法进行认证
            subject.login(token);
            // 认证成功，返回 true
            return true;
        } catch (AuthenticationException e) {
            // 认证失败，返回失败的结果
            return onLoginFailure(token, e, request, response);
        }
    }

    /**
     * 检查是否允许访问的给定请求。
     * @param request Servlet 请求
     * @param response Servlet 响应
     * @param mappedValue 映射的值
     * @return 如果允许访问则返回 true，否则返回 false
     */
/*    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        log.info("$$$isAccessAllowed");
        HttpServletRequest req= (HttpServletRequest) request;
        if(req.getMethod().equals(RequestMethod.OPTIONS.name())){
            return true;
        }
        return false;
    }*/
    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        log.info("$$$iscreateToken");
        HttpServletRequest req= (HttpServletRequest) request;
        String token=getRequestToken(req);
        if(StrUtil.isBlank(token)){
            return null;
        }
        return new OAuth2Token(token);
    }
    /**
     * 处理访问被拒绝的情况，可能涉及令牌验证、过期和刷新。设置适当的响应状态和消息。
     * @param request Servlet 请求
     * @param response Servlet 响应
     * @return 如果继续执行过滤器链则返回 true，否则返回 false
     * @throws Exception 在处理访问被拒绝的情况时抛出异常
     */

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
//        log.info("$$$onAccessDenied");
//        HttpServletRequest req= (HttpServletRequest) request;
//        HttpServletResponse resp= (HttpServletResponse) response;
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//        resp.setHeader("Access-Control-Allow-Credentials", "true");
//        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
//        String token=getRequestToken(req);
//        if(StrUtil.isBlank(token)){
//            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
//            resp.getWriter().print("无效的令牌1");
//            return false;
//        }
//        try{
//            jwtUtils.verifierToken(token);
//        }
//        catch (Exception e){
//            resp.setStatus(HttpStatus.SC_UNAUTHORIZED);
//            resp.getWriter().print("无效的令牌");
//            return false;
//        }
//        boolean bool=executeLogin(request,response);
        return true;
    }


    /**
     * 处理登录失败的情况。设置适当的响应状态和消息。
     * @param token 身份验证令牌
     * @param e 身份验证异常
     * @param request Servlet 请求
     * @param response Servlet 响应
     * @return 如果继续执行过滤器链则返回 true，否则返回 false
     */
/*    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
       log.info("$$onLoginFailure");
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
    }*/

    /**
     * 在执行过滤器链的内部逻辑中进行处理，设置适当的响应头信息。
     * @param request Servlet 请求
     * @param response Servlet 响应
     * @param chain 过滤器链
     * @throws ServletException 在处理过滤器链时可能抛出的异常
     **/
   @Override
    public void doFilterInternal(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("$$doFilterInternal");
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp= (HttpServletResponse) response;
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Access-Control-Allow-Credentials", "true");
        resp.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        super.doFilterInternal(request, response, chain);

    }

    private String getRequestToken(HttpServletRequest request){
        log.info("$%$getRequestToken");
        String token=request.getHeader("Authorization");
        if(StrUtil.isBlank(token)){
            token=request.getParameter("Authorization");
        }
        return token;
    }
}
