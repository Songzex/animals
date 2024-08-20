package com.scy.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scy.config.JwtToken;
import com.scy.config.JwtUtil;
import com.scy.config.ThreadLocalToken;
import com.scy.from.FromUser;
import com.scy.from.Fromlogin;
import com.scy.from.ToFrom;
import com.scy.mapper.TenngerMapper;
import com.scy.mapper.UserMapper;
import com.scy.mapper.UserRolesMapper;
import com.scy.pojo.*;
import com.scy.resoult.Resoult;
import com.scy.service.UserAdditionalService;
import com.scy.service.UserFriendesService;
import com.scy.service.UserService;
import com.scy.utils.JwtBlacklist;
import com.scy.utils.PasswordUtil;
import com.scy.utils.QiniuKodoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user/")
@CrossOrigin
@Slf4j
public class UserController {
    @Autowired
    private JwtUtil jwtUtils;

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtBlacklist jwtBlacklist;

    @Autowired
    private UserAdditionalService userAdditional;

    @Autowired
    private ThreadLocalToken threadLocal;

    @Autowired
    private  UserAdditionalService userAdditionalService;
    @Autowired
    private QiniuKodoUtil qiniuKodoUtil;

    @Autowired
    private UserFriendesService userFriendesService;


    @Autowired
    private TenngerMapper tenngerService;
    @Autowired
    private UserRolesMapper UserRolesMapper;

    //注册

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("register/")
    @ResponseBody
    public Resoult getRegister(@RequestBody FromUser user) {
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
        userAdditionalService.add(new UserAdditional("http://sadj5svyg.hb-bkt.clouddn.com/temp1005485323920984769.tmp","在个人很实在","121212",login.getUserid(),""));
        return new Resoult(200, null, null);

    }

    //登录
    @PostMapping("login/")
    @ResponseBody
    public Resoult getLogin(@RequestBody Fromlogin user) {
        User existingUser = userService.selelctemail(user.getEmail());

        if (existingUser == null) {
            return new Resoult(501, "用户不存在", null);
        } else if (PasswordUtil.verifyPassword(user.getPass(),"ad111sfsfsA",existingUser.getPassword())) {
            String token = jwtUtils.cretaeToken(user.getEmail(),"123321");
            JwtToken oAuth2Token = new JwtToken(token);
            System.out.println("token是。。。。。"+token);
            // 调用 Subject.login 方法进行登录，将用户的身份信息传递给 Shiro
//            SecurityUtils.getSubject().login(oAuth2Token);
            UserAdditional add = userAdditional.select(existingUser.getUserid());
            UserRole userRole = UserRolesMapper.selectOne(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserid, existingUser.getUserid()));
            if(userRole==null){
                ToFrom from = new ToFrom(existingUser, add,"普通用户");
                return new Resoult(200, from, token);
            }else {
            ToFrom from = new ToFrom(existingUser, add,userRole.getRoles());
            return new Resoult(200, from, token);}
        }
        return new Resoult(500, "用户密码不对", null);
    }
    //附加
    @PostMapping("addition/")
    @ResponseBody
    public Resoult addtion() {
        String threadLocalToken = threadLocal.getToken();
        String username = JwtUtil.getUsername(threadLocalToken);
        User existingUser = userService.selelctemail(username);

        if (existingUser != null) {
            UserAdditional add = userAdditional.select(existingUser.getUserid());
            System.out.println(add.getUserphoto());
            UserRole userRole = UserRolesMapper.selectOne(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserid, existingUser.getUserid()));
            if(userRole==null){
                ToFrom from = new ToFrom(existingUser, add,"普通用户");
                return new Resoult(200, from, null);
            }else {
            ToFrom from = new ToFrom(existingUser, add,userRole.getRoles());
            return new Resoult(200, from, null);}
        } else {
            return new Resoult(500, null, null);
        }}
    @PostMapping("additions/")
    @ResponseBody
    public Resoult updateMessage(@RequestParam("file")MultipartFile file,@RequestParam("usersign") String usersign,@RequestParam("usertele") String usertele ) {
        String threadLocalToken = threadLocal.getToken();
        String username = JwtUtil.getUsername(threadLocalToken);
        User existingUser = userService.selelctemail(username);
       // System.out.println(usersign.toString()+usertele.toString());
        if (existingUser != null) {
            if (file.isEmpty()) {
                return new Resoult(500, null, null);
            }
            String fileUrl;
            try {
                // 保存文件到本地临时目录
                File localFile = File.createTempFile("temp", null);
                file.transferTo(localFile);

                // 上传到七牛云并获取外链
//       String fileUrl = qiniuKodoUtil.upload(localFile.getAbsolutePath());
                qiniuKodoUtil.upload(localFile.getAbsolutePath());
                fileUrl = qiniuKodoUtil.getFileUrl(localFile.getName());
                // 删除临时文件
                localFile.delete();
            } catch (IOException e) {
                return new Resoult(500, null, null);
            }
            UserAdditional userAdditional1 = new UserAdditional(fileUrl, usersign, usertele,existingUser.getUserid(),null);
            userAdditionalService.updates(userAdditional1);
            System.out.println(userAdditional1.getUserphoto());
            UserRole userRole = UserRolesMapper.selectOne(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserid, existingUser.getUserid()));
            if(userRole==null){
                ToFrom from = new ToFrom(existingUser, userAdditional1,"普通用户");
                return new Resoult(200, from, null);
            }
            ToFrom from = new ToFrom(existingUser, userAdditional1,userRole.getRoles());
            return new Resoult(200, from, null);
        }
            return new Resoult(500, "additional", null);

    }
    @GetMapping("/logout")
    public String getLogout(HttpServletRequest httpRequest) {
        String token=httpRequest.getHeader("token");
        jwtBlacklist.addToBlacklist(token);
        return "out success";
    }

    /**
     * 我的好友
     * @param data
     * @return
     */
    @PostMapping("/allFriends")
    public Resoult getUserfriends(@RequestBody Map<String, String> data) {
        String email = data.get("id"); // 从请求体中获取email字段的值
        log.info(email);
        List<UserFriendes> list = userFriendesService.selectAll(email);
        ArrayList<Friends> friendsArrayList = new ArrayList<>();
        for (UserFriendes userFriendes : list) {
            String userEmail = userFriendes.getUser_firends();//好有的邮箱
            User user = userService.selelctemail(userEmail);//好有
            UserAdditional additional = userAdditionalService.select(user.getUserid());//好有的邮箱
            Friends bean = BeanUtil.toBean(additional, Friends.class);
            bean.setEmail(userEmail);
            bean.setUsername(user.getUsername());
            friendsArrayList.add(bean);
        }
        return new Resoult(200,friendsArrayList,null);
    }

    /**
     * 成为志愿者
     * @param
     * @return
     */
    @PostMapping("/teenger")
    public Resoult addteenger(@RequestBody Map<String, String> requestBody) {
        String space = requestBody.get("space");
        if(space==null){
            return new Resoult(500,"加入失败",null);
        }
        String threadLocalToken = threadLocal.getToken();
        String username = JwtUtil.getUsername(threadLocalToken);
        User user2 = userService.selelctemail(username);
        Tennger tennger = new Tennger();
        List<Tennger> list = tenngerService.selectList(new QueryWrapper<Tennger>().eq("userid", user2.getUserid()));
        if(list.stream().count()==0){
            tennger.setUserid(user2.getUserid());
            tennger.setSpace(space);
            tenngerService.insert(tennger);
            return new Resoult(200,"加入成功",null);
        }

        return new Resoult(500,"加入失败",null);
    }

    /**
     * 所谓的用户
     * @return
     */
    @PostMapping("/alluserselelct")
    public Resoult alluserselelct() {
        List<String> list = userService.selectlist().stream().map(User::getEmail).collect(Collectors.toList());
        return new Resoult(500,list,null);
    }




}