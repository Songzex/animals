package com.scy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.mapper.AnimalMapper;
import com.scy.pojo.Animal;
import com.scy.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author 24022
* @description 针对表【animal】的数据库操作Service实现
* @createDate 2024-03-16 14:53:04
*/
@Service
public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal>
    implements AnimalService{
    @Autowired
    private  AnimalMapper animalMapper;
    @Override
    public List<Animal> findall(String email) {

        List<Animal> findall = animalMapper.findall(email);
        return findall;
    }
}




