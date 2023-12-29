package com.scy.mapper;

import com.scy.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24022
* @description 针对表【user】的数据库操作Mapper
* @createDate 2023-12-03 14:55:46
* @Entity pojo.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {
       //注册
       void addUser(User user);
       //登录
       User Login(String email);

       User selelctemail(String userEmail);
}




