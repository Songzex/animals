package com.scy.service;

import com.scy.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.scy.pojo.UserRole;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
* @author 24022
* @description 针对表【user】的数据库操作Service
* @createDate 2023-12-03 14:55:46
*/
@Service
public interface UserService extends IService<User> {
    void addUser(User user);
    User Login( String id);

    User selelctemail(String userEmail);
    Set<String> getUserRoles(int userId);
}
