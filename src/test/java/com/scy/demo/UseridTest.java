package com.scy.demo;

import com.scy.service.UserService;
import com.scy.service.impl.UserServiceImpl;

import java.util.Set;

public class UseridTest {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        Set<String> userRoles = userService.getUserRoles(36);
        System.out.println(userRoles.toArray());
    }//Exception in thread "main" java.lang.NullPointerException
   // at com.scy.utils.JwtUtils.getUserId(JwtUtils.java:42)
   // at com.scy.demo.UseridTest.main(UseridTest.java:9)


}
