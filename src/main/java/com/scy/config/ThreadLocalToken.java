package com.scy.config;

import org.springframework.stereotype.Component;

@Component
public class ThreadLocalToken {
    private ThreadLocal<String> local=new ThreadLocal<>();/* 线程副本 属于线程同步中   无锁同步==*/

    public void setToken(String token){
        local.set(token);
    }

    public String getToken(){
        return local.get();
    }

    public void clear(){
        local.remove();
    }
}
