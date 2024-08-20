package com.scy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scy.from.article.ArticleTags;
import com.scy.pojo.Article;

import java.util.List;

/**
* @author 24022
* @description 针对表【article】的数据库操作Service
* @createDate 2023-12-03 14:55:46
*/
public interface ArticleService extends IService<Article> {
//    add(String);
public List<Article> selectall();
    public List<Article> selectallbyuserid(String email);
    public void deletebyarticleid(Integer articleid);
    public void updatestaticbyid(String articleid,Integer status);
    public void add(Article article);

    List<Article> selectallxin(ArticleTags xin);

    List<Article> selectarticlexin(Article articleTags);

    List<Article> selectallme(String email);

    List<Article> selectags(ArticleTags articleTags);
}
