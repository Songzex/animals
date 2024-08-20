package com.scy.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.scy.controller.AnimalTags;
import com.scy.from.article.ArticleTags;
import com.scy.pojo.Animal;

import java.util.List;

/**
* @author 24022
* @description 针对表【animal】的数据库操作Service
* @createDate 2024-03-16 14:53:04
*/
public interface AnimalService extends IService<Animal> {
    /**
     * 条件查询
     * @param email
     * @return
     */
    List<Animal> findall(String email);    /**
     * 条件查询
     * @param email
     * @return
     */
    List<Animal> findalls(String email);

    /**
     * 全查
     * @return
     */
    List<Animal> findAll();

    List<Animal> selectallxin(ArticleTags articleTags);

    List<Animal> selectbytags(AnimalTags animalTags);

    void  updates(Animal animal);

    List<Animal> findAlltui();
}
