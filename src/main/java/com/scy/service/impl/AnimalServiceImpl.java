package com.scy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.pojo.Animal;
import com.scy.mapper.AnimalMapper;
import com.scy.service.AnimalService;
import org.springframework.stereotype.Service;

/**
* @author 24022
* @description 针对表【animal】的数据库操作Service实现
* @createDate 2023-12-03 14:55:46
*/
@Service
public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal>
    implements AnimalService {

}




