package com.scy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scy.pojo.GroupMember;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24022
* @description 针对表【group_member】的数据库操作Mapper
* @createDate 2024-03-24 14:20:49
* @Entity pojo.domain.GroupMember
*/
@Mapper
public interface GroupMemberMapper extends BaseMapper<GroupMember> {

}




