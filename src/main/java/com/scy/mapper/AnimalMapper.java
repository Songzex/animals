package com.scy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scy.pojo.Animal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
* @author 24022
* @description 针对表【animal】的数据库操作Mapper
* @createDate 2024-03-17 00:09:51
* @Entity com.scy.pojo.Animal
*/
@Mapper
public interface AnimalMapper extends BaseMapper<Animal> {
      List<Animal>findall(@Param("email")String email);
}




