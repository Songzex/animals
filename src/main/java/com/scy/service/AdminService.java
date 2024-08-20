package com.scy.service;

import com.scy.pojo.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* @author 24022
* @description 针对表【admin】的数据库操作Service
* @createDate 2023-12-03 14:55:46
*/
@Transactional
@Service
public interface AdminService extends IService<Admin> {

}
