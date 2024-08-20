package com.scy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.pojo.Adopt;
import com.scy.mapper.AdoptMapper;
import com.scy.service.AdoptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 24022
* @description 针对表【adopt】的数据库操作Service实现
* @createDate 2023-12-03 14:55:46
*/
@Service
public class AdoptServiceImpl extends ServiceImpl<AdoptMapper, Adopt>
    implements AdoptService {
@Autowired

private  AdoptMapper adoptMapper;
    @Override
    public List<Adopt> findAll() {
        List<Adopt> adoptList = adoptMapper.selectList(new LambdaQueryWrapper<>());
        return adoptList;
    }
}




