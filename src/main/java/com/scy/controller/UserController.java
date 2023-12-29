package com.scy.controller;

import com.scy.from.FromUser;
import com.scy.from.Fromlogin;
import com.scy.mogomapper.Mongomapper;
import com.scy.pojo.User;
import com.scy.pojo.mogopojo.Comment;
import com.scy.resoult.Resoult;
import com.scy.service.UserService;
import com.scy.service.impl.UserServiceImpl;
import com.scy.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("user/")
@CrossOrigin
public class UserController {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private    UserService   userService;
    //注册
    @PostMapping ("register/")
    @ResponseBody
    public Resoult getComment(@RequestBody FromUser user){
        User user1 = new User();
       user1.setUsername(user.getName());
        user1.setAge(user.getAge());
        String sex = user.getSex();
        if(Objects.equals(sex, "男")){
            user1.setSex(1);
        }else {
            user1.setSex(0);
        }
        user1.setTelet(null);
        user1.setPassword(user.getPass());
        user1.setEmail(user.getEmail());
        userService.addUser(user1);
        System.out.println(user.toString());
        User login = userService.Login(user.getEmail());
        Integer userid = login.getUserid();
        String token = jwtUtils.createToken(userid);
        return  new Resoult(200,null,token);
    }
    //登录
    @PostMapping ("login/")
    @ResponseBody
    public Resoult adduser(@RequestBody Fromlogin user) {
        try {
            String userEmail = user.getEmail();//检查是否注册
            User user1 = userService.selelctemail(userEmail);
            if(user1==null){
                return new Resoult(300,null,null);
            }
         //检查密码是否正确
        } catch (Exception e) {
        }

        return null;
    }

}
