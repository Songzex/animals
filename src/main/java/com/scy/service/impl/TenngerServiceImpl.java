package com.scy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.pojo.Tennger;
import com.scy.service.TenngerService;
import com.scy.mapper.TenngerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 24022
* @description 针对表【tennger】的数据库操作Service实现
* @createDate 2024-05-09 19:59:10
*/
@Service
public class TenngerServiceImpl extends ServiceImpl<TenngerMapper, Tennger>
    implements TenngerService{
@Autowired
TenngerMapper tenngerMapper;
    @Override
    public void selectAll(Tennger tennger) {
        tenngerMapper.insert(tennger);
    }

    @Override
    public void delete(String uid) {
        tenngerMapper.deleteById(uid);
    }

    @Override
    public  List<Tennger> selectlist(Tennger tennger) {
       return   tenngerMapper.selectList(new LambdaQueryWrapper<>());
    }
}




