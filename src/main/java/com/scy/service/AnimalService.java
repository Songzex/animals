package com.scy.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.scy.pojo.Animal;

import java.util.List;

/**
* @author 24022
* @description 针对表【animal】的数据库操作Service
* @createDate 2024-03-16 14:53:04
*/
public interface AnimalService extends IService<Animal> {
    List<Animal> findall(String email);
}
