package com.scy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.controller.AnimalTags;
import com.scy.from.article.ArticleTags;
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

    @Override
    public List<Animal> findalls(String email) {
        return animalMapper.selectList(new LambdaQueryWrapper<Animal>().eq(Animal::getAnimalsUemail,email));
    }

    @Override
    public List<Animal> findAll() {
        QueryWrapper<Animal> wrapper = new QueryWrapper<>();
        wrapper.eq("animal_shadow",1);
        List<Animal> findall = animalMapper.selectList(wrapper);
        return findall;
    }

    @Override
    public List<Animal> selectallxin(ArticleTags articleTags) {
        List<Animal> animalList = animalMapper.selectList(new LambdaQueryWrapper<>());
        return animalList;
    }

    @Override
    public List<Animal> selectbytags(AnimalTags animalTags) {
        List<Animal> list = animalMapper.selectList(new QueryWrapper<Animal>().eq("animal_shadow",0));
        return list;
    }

    @Override
    public void updates(Animal animal) {
        animalMapper.updateById(animal);
    }

    @Override
    public List<Animal> findAlltui() {
        return animalMapper.selectList(new LambdaQueryWrapper<Animal>().eq(Animal::getAnimalShadow,1));
    }
}




