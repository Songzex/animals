package com.scy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.mapper.UserFriendesMapper;
import com.scy.pojo.UserFriendes;
import com.scy.service.UserFriendesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 24022
* @description 针对表【user_friendes】的数据库操作Service实现
* @createDate 2024-04-21 22:16:22
*/
@Service
public class UserFriendesServiceImpl extends ServiceImpl<UserFriendesMapper, UserFriendes>
    implements UserFriendesService {



    @Autowired
    UserFriendesMapper userFriendesMapper;
    @Override
    public List<UserFriendes> selectAll(String email) {
        return userFriendesMapper.selectList(new LambdaQueryWrapper<UserFriendes>().eq(UserFriendes::getUser_email,email));
    }
}




