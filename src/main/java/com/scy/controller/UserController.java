package com.scy.controller;

import com.scy.config.OAuth2Token;
import com.scy.from.FromUser;
import com.scy.from.Fromlogin;
import com.scy.from.ToFrom;
import com.scy.mogomapper.Mongomapper;
import com.scy.pojo.User;
import com.scy.pojo.UserRole;
import com.scy.pojo.mogopojo.Comment;
import com.scy.resoult.Resoult;
import com.scy.service.UserService;
import com.scy.service.impl.UserServiceImpl;
import com.scy.utils.JwtBlacklist;
import com.scy.utils.JwtUtils;
import com.scy.utils.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping("user/")
@CrossOrigin
@Slf4j
public class UserController {
    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtBlacklist jwtBlacklist;

    //注册
    @PostMapping("register/")
    @ResponseBody
    public Resoult getComment(@RequestBody FromUser user) {
        User user2 = userService.selelctemail(user.getEmail());
        if(user2!=null){
            return new Resoult(500, "邮箱已经被注册了", null);
        }
        User user1 = new User();
        user1.setUsername(user.getName());
        user1.setAge(user.getAge());
        String sex = user.getSex();
        if (Objects.equals(sex, "男")) {
            user1.setSex(1);
        } else {
            user1.setSex(0);
        }
        user1.setTelet(null);
        user1.setPassword(PasswordUtil.encryptPassword(user.getPass(),"ad111sfsfsA"));
        user1.setEmail(user.getEmail());
        userService.addUser(user1);
        User login = userService.Login(user.getEmail());
      /*  String email = login.getEmail();
        String token = jwtUtils.createToken(email);
        OAuth2Token oAuth2Token = new OAuth2Token(token);
        System.out.println("token是。。。。。");
        log.info(oAuth2Token.toString());*/
        return new Resoult(200, null, null);

    }

    //登录
    @PostMapping("login/")
    @ResponseBody
    public Resoult adduser(@RequestBody Fromlogin user) {
        User existingUser = userService.selelctemail(user.getEmail());
        if (existingUser == null) {
            return new Resoult(500, "用户不存在", null);
        } else if (PasswordUtil.verifyPassword(user.getPass(),"ad111sfsfsA",existingUser.getPassword())) {
            String token = jwtUtils.createToken(existingUser.getUserid());
           OAuth2Token oAuth2Token = new OAuth2Token(token);
            System.out.println("token是。。。。。"+token);
            // 调用 Subject.login 方法进行登录，将用户的身份信息传递给 Shiro
            SecurityUtils.getSubject().login(oAuth2Token);
            return new Resoult(200, "", token);
        } else {
            return new Resoult(500, "", null);
        }}
    @GetMapping("/logout")
    public String getUserById(HttpServletRequest httpRequest) {
        String token=httpRequest.getHeader("token");
        jwtBlacklist.addToBlacklist(token);
        return "out success";
    }
}