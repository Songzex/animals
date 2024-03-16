package com.scy.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtFilter extends BasicHttpAuthenticationFilter {




    /**
     * 判断请求类型并执行对应的认证逻辑
     */
    private boolean isWebSocketHandshake(ServletRequest request) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String upgradeHeader = httpRequest.getHeader("Upgrade");
        return upgradeHeader != null && upgradeHeader.equalsIgnoreCase("websocket");
    }

    /**
     * 执行 WebSocket 连接认证逻辑
     */
    private boolean executeWebSocketAuth(ServletRequest request, ServletResponse response) throws Exception {
        // 在这里执行 WebSocket 连接认证逻辑，例如提取 token 并进行认证
        // 如果认证成功返回 true，否则返回 false
        return true; // 示例中直接返回 true，你需要根据实际情况实现认证逻辑
    }

    /**
     * 执行登录认证
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (isWebSocketHandshake(request)) {
            try {
                return executeWebSocketAuth(request, response);
            } catch (Exception e) {
                // 处理异常
                return false;
            }
        } else {
            // 执行 HTTP 请求的认证逻辑
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                throw new AuthenticationException("过滤器这里Token失效，请重新登录", e);
            }
        }

    }

    /**
     *
     在普通的项目中，一般使用传统的表单登录方式，即用户通过输入用户名和密码提交表单进行登录。
     这时，executeLogin 方法的实现可能涉及到用户名和密码的获取、构造 UsernamePasswordToken 等步骤。
     而在前后端分离的项目中，通常采用基于 token 的认证方式，前端在登录成功后会获取到一个 token，并在每次请求时将 token 放置在请求头中。
     因此，executeLogin 方法的实现主要是从请求头中获取 token，并创建一个 JwtToken 对象，然后提交给 Shiro 进行登录认证。
     因此，前后端分离项目与传统项目在身份认证方式上有所不同，导致了 executeLogin 方法的实现方式也不同。
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");

        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        // 普通应用中获取subject方式 subject = SecurityUtils.getSubject();
        // 然后登录 subject.login(token)
        //login方法最后会回调到ShiroRealm.doGetAuthenticationInfo
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 对跨域提供支持
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个option请求，这里我们给option请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}

