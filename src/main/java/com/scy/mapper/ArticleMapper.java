package com.scy.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scy.pojo.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
/**
* @author 24022
* @description 针对表【article】的数据库操作Mapper
* @createDate 2024-03-23 16:34:01
* @Entity pojo.domain.Article
*/
public interface ArticleMapper extends BaseMapper<Article> {

}




