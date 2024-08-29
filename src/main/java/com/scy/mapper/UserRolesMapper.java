package com.scy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scy.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24022
* @description 针对表【user_roles】的数据库操作Mapper
* @createDate 2024-05-12 12:41:38
* @Entity pojo.domain.UserRoles
*/
@Mapper
public interface UserRolesMapper extends BaseMapper<UserRole> {

}




