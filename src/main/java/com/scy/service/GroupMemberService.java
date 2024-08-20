package com.scy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scy.controller.GrouplTags;
import com.scy.pojo.GroupMember;
import com.scy.pojo.UserGroup;

import java.util.List;

/**
* @author 24022
* @description 针对表【group_member】的数据库操作Service
* @createDate 2024-03-24 14:20:49
*/
public interface GroupMemberService extends IService<GroupMember> {

    void add(GroupMember groupMember);

    void deletebyid(GroupMember groupMember);

    List<UserGroup> selectlist(GrouplTags groupMember);
}
