package com.scy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.pojo.User;
import com.scy.mapper.UserMapper;
import com.scy.pojo.UserRole;
import com.scy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
* @author 24022
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-12-03 14:55:46
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{
  @Autowired  UserMapper userMapper;
  @Autowired
  MongoTemplate  mongoTemplate;

  @Override
  public void addUser(User user) {
    userMapper.addUser(user);
  }

  @Override
  public User Login(String email) {
    User login = userMapper.Login(email);
    return login;
  }

  @Override
  public User selelctemail(String userEmail) {
    User user = userMapper.selelctemail(userEmail);
    return user;
  }

  @Override
  public Set<String> getUserRoles(int userId) {
    return userMapper.getUserRoles(userId);
  }
}




