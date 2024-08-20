package com.scy.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scy.pojo.Adopt;
import com.scy.pojo.AnimalsFostercare;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @author 24022
* @description 针对表【animals_fostercare】的数据库操作Mapper
* @createDate 2024-03-24 14:04:27
* @Entity com.scy.pojo.AnimalsFostercare
*/
@Mapper
public interface AnimalsFostercareMapper extends BaseMapper<AnimalsFostercare> {


}




