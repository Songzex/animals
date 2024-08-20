package com.scy.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scy.controller.GrouplTags;
import com.scy.pojo.GroupMember;
import com.scy.pojo.UserGroup;

import java.util.List;

/**
* @author 24022
* @description 针对表【user_group】的数据库操作Service
* @createDate 2024-03-24 14:21:02
*/
public interface UserGroupService extends IService<UserGroup> {

    public void addgroup(UserGroup userGroup);
    public void  deletegroup(String groupid);

    public void update(UserGroup userGroup);

    public List<UserGroup> select(String adminnanem, String gruopid);

    List<UserGroup> select(GrouplTags groupMember);
}
