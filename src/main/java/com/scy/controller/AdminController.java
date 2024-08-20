package com.scy.controller;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.scy.config.JwtUtil;
import com.scy.config.ThreadLocalToken;
import com.scy.controller.uservo.UserAdmin;
import com.scy.controller.uservo.groupcrete;
import com.scy.from.animals.Fromanimalsmeassage;
import com.scy.mapper.UserAdditionalMapper;
import com.scy.mapper.UserGroupMapper;
import com.scy.mapper.UserMapper;
import com.scy.mapper.UserRolesMapper;
import com.scy.pojo.*;
import com.scy.resoult.Resoult;
import com.scy.service.*;
import com.scy.utils.PasswordUtil;
import com.scy.utils.QiniuKodoUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserMapper userMapper
            ;
    @Autowired
    UserRolesMapper userRolesMapper;

    @Autowired
    UserGroupMapper userGroupMapper;

    @Autowired
    UserAdditionalMapper userAdditionalMapper;

    @Autowired
    AnimalService animalService;

    @Autowired
    AnimalsFlowService animalsFlowService;

    @Autowired
    AdoptService adoptService;

    @Autowired
    AnimalsAdditionalService animalsAdditionalService;

    @Autowired
    TenngerService tenngerService;

    @Autowired
    AnimalsFostercareService animalsFostercareService;

    @Autowired
    GroupMemberService groupMemberService;

    @Autowired
    UserGroupService userGroupService;
    @Autowired
    ThreadLocalToken threadLocalToken;

    @Autowired
    QiniuKodoUtil qiniuKodoUtil;

    @Autowired
    ArticleService articleService;


    @PostMapping("/aa")
    @RequiresRoles(value={"ADMIN", "SUPER"}, logical= Logical.OR)
    public Resoult getUserById() {
        System.out.println("被访问");
        return new Resoult(200,"",null);
    }

    /**
     * 创建群组
     * @return
     */
    @PostMapping("/cretegroup")
    @RequiresRoles(value={"ADMIN", "SUPER"}, logical= Logical.OR)
    public Resoult cretegroup(@RequestParam("file") MultipartFile file, groupcrete groupcrete) {
        String token = threadLocalToken.getToken();
        String email = JwtUtil.getUsername(token);
        String fileUrl;
        try {
            // 保存文件到本地临时目录
            File localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            // 上传到七牛云并获取外链
            qiniuKodoUtil.upload(localFile.getAbsolutePath());
            fileUrl = qiniuKodoUtil.getFileUrl(localFile.getName());
            UserGroup userGroup = new UserGroup();
            userGroup.setGroupphoto(fileUrl);
            userGroup.setGruopselefname(groupcrete.getGruop_selefname());
            userGroup.setGroupdetils(groupcrete.getGroup_detils());
            userGroup.setGrouptags(groupcrete.getGroup_tags());
            userGroup.setAdminname(email);
             userGroupMapper.insert(userGroup);
            UserGroup group = userGroupMapper.selectOne(new LambdaQueryWrapper<UserGroup>().eq(UserGroup::getGroupphoto, fileUrl));
            if(group==null){
                return null;

            }
            groupMemberService.add(new GroupMember(group.getId().toString(),email));
            return new Resoult(200,"成功",null);
    } catch (IOException e) {
            throw new RuntimeException(e);
        }}
        /**
     * 删除群组
     * @return
     */
    @PostMapping("/deletegroup")
    @RequiresRoles(value={"ADMIN", "SUPER"}, logical= Logical.OR)
    public Resoult deletegroup(@RequestBody Map<String,String> RequestBody) {
        String id = RequestBody.get("id");
        userGroupService.deletegroup(id);
        return new Resoult(200,"",null);
    }

    /**
     * 群组裙衩
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @PostMapping("allgroup/")
    public Resoult allgroup( @RequestBody Map<String,String> RequestBody) {
        String string = RequestBody.get("soudate");
        if(string==null){
        List<UserGroup> groupList = userGroupMapper.selectList(new
                LambdaQueryWrapper<UserGroup>());
        ArrayList<grouplist> grouplistArrayList = new ArrayList<>();
        for (UserGroup onre:groupList){
            String email = onre.getAdminname();
            User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, email));
            Integer userid = user.getUserid();
            UserAdditional userAdditional = userAdditionalMapper.selectOne(new LambdaQueryWrapper<UserAdditional>().eq(UserAdditional::getUserid, userid));
            String userphoto = userAdditional.getUserphoto();
            grouplist grouplist = new grouplist();
            grouplist.setGroupdetils(onre.getGroupdetils());
            grouplist.setGroupphoto(onre.getGroupphoto());
            grouplist.setAdminname(onre.getAdminname());
            grouplist.setGruopselefname(onre.getGruopselefname());
            grouplist.setPhoto(userphoto);
            grouplist.setId(onre.getId());
            grouplistArrayList.add(grouplist);
        }
        return new Resoult(200,grouplistArrayList,null);
    }else {
            List<UserGroup> groupList = userGroupMapper.selectList(new QueryWrapper<UserGroup>().eq("group_tags",string));

            ArrayList<grouplist> grouplistArrayList = new ArrayList<>();
            for (UserGroup onre:groupList){
                String email = onre.getAdminname();
                User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getEmail, email));
                Integer userid = user.getUserid();
                UserAdditional userAdditional = userAdditionalMapper.selectOne(new LambdaQueryWrapper<UserAdditional>().eq(UserAdditional::getUserid, userid));
                String userphoto = userAdditional.getUserphoto();
                grouplist grouplist = new grouplist();
                grouplist.setGroupdetils(onre.getGroupdetils());
                grouplist.setGroupphoto(onre.getGroupphoto());
                grouplist.setAdminname(onre.getAdminname());
                grouplist.setGruopselefname(onre.getGruopselefname());
                grouplist.setPhoto(userphoto);
                grouplist.setId(onre.getId());
                grouplistArrayList.add(grouplist);
        }
            return new Resoult(200,grouplistArrayList,null);
        }
    }
    /**
     * 人员的管理删除
     * @return
     */
    @PostMapping("userdelete/")
    @RequiresRoles(value={"ADMIN", "SUPER"}, logical= Logical.OR)
    public Resoult userdelete(@RequestBody Map<String,String> RequestBody) {
        String id = RequestBody.get("id");
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUserid, id));
        user.setDeleted(1);
        int id1 = userMapper.updateById(user);
        return new Resoult(200,"",null);
    }

    /**、
     * 管理权限
     * @param RequestBody
     * @return
     */
    @PostMapping("useradmin/")
    @RequiresRoles(value={"ADMIN", "SUPER"}, logical= Logical.OR)
    public Resoult useradmin(@RequestBody Map<String,String> RequestBody) {
        String id = RequestBody.get("id");
        UserRole userRole = userRolesMapper.selectOne(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserid, id));
        if(userRole==null){
            UserRole userRole1 = new UserRole();
            userRole1.setRoles("ADMIN");
            userRole1.setStatus(0);
            userRole1.setUserid(Integer.valueOf(id));
            userRolesMapper.insert(userRole1);
            return new Resoult(200,"具体权限需要站长授权",null);
        }
      userRole.setRoles("ADMIN");
        int id1 = userRolesMapper.updateById(userRole);
        if(id1<0){
            return new Resoult(500,"",null);
        }
        return new Resoult(200,"",null);
    }
    @PostMapping("cancleuseradmin/")
    @RequiresRoles(value={"ADMIN", "SUPER"}, logical= Logical.OR)
    public Resoult cancleuseradmin(@RequestBody Map<String,String> RequestBody) {
        String id = RequestBody.get("id");
        UserRole userRole = userRolesMapper.selectOne(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserid, id));
        if(userRole==null){
            UserRole userRole1 = new UserRole();
            userRole1.setRoles("普通用户");
            userRole1.setStatus(0);
            userRole1.setUserid(Integer.valueOf(id));
            userRolesMapper.insert(userRole1);
            return new Resoult(200,"具体权限需要站长授权",null);
        }
      userRole.setRoles("普通用户");
        int id1 = userRolesMapper.updateById(userRole);
        if(id1<0){
            return new Resoult(500,"",null);
        }
        return new Resoult(200,"",null);
    }

    /**
     * 人员的查看
     * @return
     */
    @PostMapping("userlooks/")
    @RequiresRoles(value={"ADMIN", "SUPER"}, logical= Logical.OR)
    public Resoult userlooks() {
        List<User> list = userMapper.selectList(new QueryWrapper<User>());
        ArrayList<UserAdmin> userAdmins = new ArrayList<>();
        for (User one:list){
            UserAdmin userAdmin = new UserAdmin();
            userAdmin.setAge(one.getAge());
            String string = PasswordUtil.decryptPassword(one.getPassword(), "ad111sfsfsA");
            userAdmin.setPassword(string);
            userAdmin.setSex(one.getSex());
            userAdmin.setEmail(one.getEmail());
            userAdmin.setUserid(one.getUserid());
            userAdmin.setUsername(one.getUsername());
            UserAdditional userAdditional = userAdditionalMapper.selectOne(new LambdaQueryWrapper<UserAdditional>().eq(UserAdditional::getUserid,one.getUserid()));
            userAdmin.setUserphoto(userAdditional.getUserphoto());
            userAdmin.setUsersign(userAdditional.getUsersign());
            userAdmin.setTelet(userAdditional.getUsertele());
            UserRole userRole = userRolesMapper.selectOne(new LambdaQueryWrapper<UserRole>().eq(UserRole::getUserid, one.getUserid()));
            if(userRole==null){
                userAdmin.setRoles("普通用户");
                userAdmin.setStaus("普通用权限");
                userAdmins.add(userAdmin);
            }else {
            userAdmin.setRoles(userRole.getRoles());
            userAdmin.setStaus(( userRole.getStatus()==1)?"权限正常":"权限异常");
            userAdmins.add(userAdmin);}
        }
        return new Resoult(200,userAdmins,null);
    }

    /**
     * 志愿者查询
     * @return
     */
    @PostMapping("/userlookteen")
    @RequiresRoles(value={"ADMIN", "SUPER"}, logical= Logical.OR)
    public Resoult userlook(Tennger tennger) {
        List<Tennger> tenngerList = tenngerService.selectlist(tennger);
        return new Resoult(200,tenngerList,null);
    }
    /**
     * 志愿者的管理删除
     * @return
     */
    @PostMapping("/teendelete")
    @RequiresRoles(value={"ADMIN", "SUPER"}, logical= Logical.OR)
    public Resoult teendelete(String uid) {
        tenngerService.delete(uid);
        return new Resoult(200,"",null);
    }
/////
    /**
     * 文章全查
     * @return
     */
    @PostMapping("/articleall")
    @RequiresRoles(value={"ADMIN", "SUPER"}, logical= Logical.OR)
    public Resoult articleall() {
        List<Article> list = articleService.selectall();
        return new Resoult(200,list,null);
    }
    /**
     * 文章审核
     * @return 0 1
     */
    @PostMapping("/articledo")
    @RequiresRoles(value={"ADMIN", "SUPER"}, logical= Logical.OR)
    public Resoult articledo(String id) {
        articleService.updatestaticbyid(id,1);
        return new Resoult(200,"",null);
    }

    /**
     * 任务发布 动物id
     * @return
     */
    @PostMapping("/workdoing")
    @RequiresRoles(value={"ADMIN", "SUPER"}, logical= Logical.OR)
    public Resoult workdoing( WorkSs workSs) {
        animalsFlowService.add(workSs);
        return new Resoult(200,"",null);
    }
    /**
     * 文章的删除
     * @return 0 1
     */
    @PostMapping("/artdelelte")
    @RequiresRoles(value={"ADMIN", "SUPER"}, logical= Logical.OR)
    public Resoult articledelelte(Integer id) {
        articleService.deletebyarticleid(id);
        return new Resoult(200,"",null);
    }




}
