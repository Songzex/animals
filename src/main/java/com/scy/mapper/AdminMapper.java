package com.scy.mapper;

import com.scy.pojo.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 24022
* @description 针对表【admin】的数据库操作Mapper
* @createDate 2023-12-03 14:55:46
* @Entity pojo.domain.Admin
*/
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}



