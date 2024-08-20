package com.scy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.pojo.UserRole;
import com.scy.service.UserRoleService;
import com.scy.mapper.UserRolesMapper;
import org.springframework.stereotype.Service;

/**
* @author 24022
* @description 针对表【user_roles】的数据库操作Service实现
* @createDate 2024-05-12 12:41:38
*/
@Service
public class UserRolesServiceImpl extends ServiceImpl<UserRolesMapper, UserRole>
    implements UserRoleService {

}




