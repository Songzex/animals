package com.scy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scy.pojo.UserGroup;
import org.apache.ibatis.annotations.Mapper;


/**
* @author 24022
* @description 针对表【user_group】的数据库操作Mapper
* @createDate 2024-03-24 14:21:02
* @Entity pojo.domain.UserGroup
*/
@Mapper
public interface UserGroupMapper extends BaseMapper<UserGroup> {

}




