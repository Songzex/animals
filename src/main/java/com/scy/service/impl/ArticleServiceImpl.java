package com.scy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.from.article.ArticleTags;
import com.scy.mapper.ArticleMapper;
import com.scy.pojo.Article;
import com.scy.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 24022
* @description 针对表【article】的数据库操作Service实现
* @createDate 2024-03-23 16:34:01
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService {

    @Autowired
    public  ArticleMapper articleMapper;

    /**
     * 全查文章列表
     * @return
     */
    public List<Article> selectall(){
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(Article::getArticlestatus,0);
        List<Article> articleList = articleMapper.selectList(queryWrapper);
        return articleList;
    }

    /**
     * 根据用户邮箱查询文章
     * @param email
     * @return
     */
    public List<Article> selectallbyuserid(String email){
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<Article>();
        queryWrapper.eq(Article::getArticleemail,email);
        List<Article> articleList = articleMapper.selectList(queryWrapper);
        return articleList;
    }

    @Override
    public void deletebyarticleid(Integer articleid) {
        int i = articleMapper.delete(new QueryWrapper<Article>().eq("article_id", articleid));
    }

    /**
     * 根据id删除文章
     * @param articleid
     */
    public void deletebyarticleid(String articleid){
        int i = articleMapper.deleteById(new LambdaQueryWrapper<Article>().eq(Article::getArticleid,
                articleid));
    }

    /**
     * 根据文章id修改状态
     * @param articleid
     */
    public void updatestaticbyid(String articleid,Integer status){
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getArticleid,articleid);
        Article article = articleMapper.selectOne(queryWrapper);
        article.setArticlestatus(status);
        articleMapper.updateById(article);
    }

    /**
     * 插入添加文章
     * @param article
     */
    public void add(Article article){
        articleMapper.insert(article);
    }

    @Override
    public List<Article> selectallxin(ArticleTags xin) {
        return articleMapper.selectList(new LambdaQueryWrapper<Article>().eq(Article::getArticlestatus, xin.getTags()));
    }

    @Override
    public List<Article> selectarticlexin(Article articleTags) {
        return articleMapper.selectList(new LambdaQueryWrapper<>());
    }

    @Override
    public List<Article> selectallme(String email) {
        return articleMapper.selectList(new LambdaQueryWrapper<Article>().eq(Article::getArticleemail,email));
    }

    @Override
    public List<Article> selectags(ArticleTags articleTags) {
        if(articleTags.getAnimaltype()==null&&articleTags.getTags()==null&&articleTags.getEmail()==null){
            LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(Article::getArticlestatus,1);
            List<Article> articleList = articleMapper.selectList(queryWrapper);
          return  articleList;
        }

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();

        if (articleTags.getTags()!= null) {
            queryWrapper.eq("article_tags", articleTags.getTags());
        }

        if (articleTags.getAnimaltype()!= null) {
            queryWrapper.eq("article_about", articleTags.getAnimaltype());
        }

        if (articleTags.getEmail()!= null) {
            queryWrapper.eq("article_email", articleTags.getEmail());
        }

        return articleMapper.selectList(queryWrapper);
    }


}




