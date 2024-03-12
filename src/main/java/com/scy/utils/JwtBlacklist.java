package com.scy.utils;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class JwtBlacklist {
    private Set<String> blacklist;

    public JwtBlacklist() {
        this.blacklist = new HashSet<>();
    }

    // 添加 token 到黑名单
    public void addToBlacklist(String token) {
        blacklist.add(token);
    }
    // 检查 token 是否在黑名单中
    public boolean isBlacklisted(String token) {
        return blacklist.contains(token);
    }

}
