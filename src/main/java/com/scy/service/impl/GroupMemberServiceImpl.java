package com.scy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.controller.GrouplTags;
import com.scy.mapper.GroupMemberMapper;
import com.scy.pojo.GroupMember;
import com.scy.pojo.UserGroup;
import com.scy.service.GroupMemberService;
import com.scy.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author 24022
* @description 针对表【group_member】的数据库操作Service实现
* @createDate 2024-03-24 14:20:49
*/
@Service
public class GroupMemberServiceImpl extends ServiceImpl<GroupMemberMapper, GroupMember>
    implements GroupMemberService {

    @Autowired
    public  GroupMemberMapper groupMemberMapper;

    @Autowired
    UserGroupService userGroupService;

    /**
     * 增加成员到库中
     * @param groupMember
     */
    public void add( GroupMember groupMember) {
        int i = groupMemberMapper.insert(groupMember);
    }
   /* 删除成员*/
    @Override
    public void deletebyid(GroupMember groupMember) {
        int delete = groupMemberMapper.delete(new LambdaQueryWrapper<GroupMember>().eq(GroupMember::getGroupmemail, groupMember.getGroupmemail()).eq(GroupMember::getGroupid,groupMember.getGroupid()));
    }

    /**
     * 标签搜搜群组
     * @param groupMember
     */

    @Override
    public List<UserGroup> selectlist(GrouplTags groupMember) {
        return userGroupService.select(groupMember);
    }




    /**
     * 删除成员
     * @param id
     */
    public void deletebyid( String id) {
        int id1 = groupMemberMapper.deleteById(id);
    }


}




