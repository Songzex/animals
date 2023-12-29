package com.scy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.pojo.Admin;
import com.scy.mapper.AdminMapper;
import com.scy.service.AdminService;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService {

}




