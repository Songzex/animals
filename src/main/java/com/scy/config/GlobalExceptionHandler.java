package com.scy.config;

import com.scy.resoult.Resoult;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthorizationException.class)
    public Resoult handleAuthorizationException(AuthorizationException e) {
        return new Resoult(401, "不哈意思你（没有权限哦）", "");
    }

    @ExceptionHandler(AuthenticationException.class)
    public Resoult handlAuthenticationException(AuthorizationException e) {
        return new Resoult(403, "认未通过认证", "");
    }
}