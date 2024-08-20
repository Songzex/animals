package com.scy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scy.pojo.UserFriendes;

import java.util.List;

/**
* @author 24022
* @description 针对表【user_friendes】的数据库操作Service
* @createDate 2024-04-21 22:16:22
*/
public interface UserFriendesService extends IService<UserFriendes> {
    /**
     * 查询所有朋友
     * @param eamil
     */
    List<UserFriendes> selectAll(String email);
}
