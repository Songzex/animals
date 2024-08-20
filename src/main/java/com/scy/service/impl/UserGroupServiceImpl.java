package com.scy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.controller.GrouplTags;
import com.scy.mapper.UserGroupMapper;
import com.scy.pojo.GroupMember;
import com.scy.pojo.UserGroup;
import com.scy.service.UserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 24022
* @description 针对表【user_group】的数据库操作Service实现
* @createDate 2024-03-24 14:21:02
*/
@Service
public class UserGroupServiceImpl extends ServiceImpl<UserGroupMapper, UserGroup>
    implements UserGroupService {

    @Autowired
    private  UserGroupMapper userGroupMapper;

    /**
     * 添加群组
     * @param userGroup 对象
     */
    public void addgroup(UserGroup userGroup){
        int insert = userGroupMapper.insert(userGroup);
    }


    /**
     * 删除群组 根据群组的id
     * @param groupid
     */
    public void  deletegroup(String groupid){

        int id = userGroupMapper.deleteById(groupid);


    }

    /**
     * 修改群信息 id不能改动
     * @param userGroup
     */
    public void update(UserGroup userGroup){
            userGroupMapper.updateById(userGroup);

    }

    /**
     * 查询自己的群组
     * @param adminnanem
     * @param gruopid
     * @return
     */
    public List<UserGroup> select(String adminnanem,String gruopid){
        LambdaQueryWrapper<UserGroup> wrapper = new LambdaQueryWrapper<>();
      wrapper.eq(UserGroup::getAdminname, adminnanem);
        List<UserGroup> userGroups = userGroupMapper.selectList(wrapper);
      return  userGroups;
    }

    @Override
    public List<UserGroup> select(GrouplTags groupMember) {
        if(groupMember==null){
          return userGroupMapper.selectList(new LambdaQueryWrapper<>());
        }
        return userGroupMapper.selectList(new LambdaQueryWrapper<>());
    }

    /**
     * 查看所有群组
     * @return
     */
    public List<UserGroup> selectall(){
        LambdaQueryWrapper<UserGroup> wrapper = new LambdaQueryWrapper<>();
        List<UserGroup> userGroups = userGroupMapper.selectList(wrapper);
        return  userGroups;
    }

}




