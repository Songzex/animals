package com.scy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scy.config.JwtUtil;
import com.scy.config.ThreadLocalToken;
import com.scy.mapper.*;
import com.scy.pojo.*;
import com.scy.resoult.Resoult;
import com.scy.service.GroupMemberService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("group/")
@CrossOrigin
public class GroupController {



    @Autowired
    GroupMemberService groupMemberService;
    @Autowired
    GroupMemberMapper groupMemberMapper;
    @Autowired
    UserGroupMapper userGroupMapper;
    @Autowired
    private ThreadLocalToken threadLocal;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserAdditionalMapper userAdditionalMapper;
    @Autowired
    private UserFriendesMapper userFriendesMapper;


    /**
     * 用户加入群组
     * @return
     */
    @PostMapping("/groupAdd")
    public Resoult groupAdd(@RequestBody Map<String,String> MP) {
        String threadLocalToken = threadLocal.getToken();
        String username = JwtUtil.getUsername(threadLocalToken);
        String string = MP.get("id");
        GroupMember member = new GroupMember(string, username);
        GroupMember member1 = groupMemberMapper.selectOne(new QueryWrapper<GroupMember>().eq("group_id", string).eq("group_memail", username));
        if(member1==null){
            groupMemberService.add(member);
            return new Resoult(200,"加入成功",null);
        }

        return new Resoult(200,"不要重复加",null);
    }

    /**
     * 群组
     * @return
     */
 @PostMapping("/groupFind")
    public Resoult groupFind(GroupMember groupMember) {
     groupMemberService.deletebyid(groupMember);
        return new Resoult(200,"",null);
    }
    /**
     * 群组我的
     * @return
     */
 @PostMapping("/groups")
    public Resoult group(@RequestBody Map<String,String> gfroup) {
     String string = gfroup.get("id");
     List<GroupMember> groupMemberList = groupMemberMapper.selectList(new QueryWrapper<GroupMember>().eq("group_memail", string));
     List<String> list = groupMemberList.stream().map(GroupMember::getGroupid).collect(Collectors.toList());
     List<UserGroup> groupList = userGroupMapper.selectList(new LambdaQueryWrapper<UserGroup>().in(UserGroup::getId, list));
     return new Resoult(200,groupList,null);
    }

    /**
     * 添加好友
     * @param gfroup
     * @return
     */
    @PostMapping("/addfriends")
    public Resoult addfriends(@RequestBody Map<String,String> gfroup) {
     String froupid = gfroup.get("id");
        String threadLocalToken = threadLocal.getToken();
        String username = JwtUtil.getUsername(threadLocalToken);
        UserFriendes friendes1 = userFriendesMapper.selectOne(new LambdaQueryWrapper<UserFriendes>().eq(UserFriendes::getUser_firends, froupid).eq(UserFriendes::getUser_email, username));
       if(friendes1!=null){
           return new Resoult(200,"不能重复添加",null);
       }else {

        UserFriendes friendes = new UserFriendes(froupid, username);
        int insert =userFriendesMapper.insert(friendes);
        return new Resoult(200,"添加成功",null);}
    }
    @PostMapping("/groupmerber")
    public Resoult groupmerber(@RequestBody Map<String,String> gfroup) {
     String string = gfroup.get("id");
     List<GroupMember> groupMemberList = groupMemberMapper.selectList(new QueryWrapper<GroupMember>().eq("group_id", string));
     if(groupMemberList.size()==0){
         return new Resoult(500,"无人成员为0",null);
     }
     List<String> list = groupMemberList.stream().map(GroupMember::getGroupmemail).collect(Collectors.toList());
     List<User> list1 = userMapper.selectList(new LambdaQueryWrapper<User>().in(User::getEmail, list));
        List<Integer> collect = list1.stream().map(User::getUserid).collect(Collectors.toList());
        List<Friends> dtos = new ArrayList<>();
        for(User one :list1){
            Friends friends = new Friends();
            friends.setUsername(one.getUsername());
            friends.setEmail(one.getEmail());
            UserAdditional additional = userAdditionalMapper.selectOne(new LambdaQueryWrapper<UserAdditional>().eq(UserAdditional::getUserid, one.getUserid()));
            friends.setUserphoto(additional.getUserphoto());
            friends.setUsersign(additional.getUsersign());
            dtos.add(friends);
        }
        return new Resoult(200,dtos,null);
    }





}
