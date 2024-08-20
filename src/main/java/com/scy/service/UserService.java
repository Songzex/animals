package com.scy.service;

import com.scy.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scy.pojo.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

public interface UserService extends IService<User> {
    void addUser(User user);
    User Login( String id);

    User selelctemail(String userEmail);
    Set<String> getUserRoles(int userId);

    void delete(String uid);

    List<User> selectlist();
}
