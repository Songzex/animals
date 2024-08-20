package com.scy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.scy.config.JwtUtil;
import com.scy.config.ThreadLocalToken;
import com.scy.from.article.ArticleTags;
import com.scy.mapper.ArticleMapper;
import com.scy.pojo.Article;
import com.scy.pojo.User;
import com.scy.pojo.UserAdditional;
import com.scy.resoult.Resoult;
import com.scy.service.ArticleService;
import com.scy.service.UserAdditionalService;
import com.scy.service.UserService;
import com.scy.utils.QiniuKodoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("article/")
@CrossOrigin
public class ArticleController {
    @Autowired
    QiniuKodoUtil qiniuKodoUtil;

    @Autowired
    private ThreadLocalToken  threadLocalToken;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserAdditionalService userAdditionalService;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    private  UserService userService;
    @PostMapping("submitArtical/")
    public Resoult uploadFile(@RequestParam("file") MultipartFile file,@RequestParam("content") String content,@RequestParam("titles") String titles,@RequestParam("type") String type,@RequestParam("tags") String tags) {
        if (file.isEmpty()) {
            return new Resoult(400, null, null);

        }
        try {
            // 保存文件到本地临时目录
            File localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);

            // 上传到七牛云并获取外链
//       String fileUrl = qiniuKodoUtil.upload(localFile.getAbsolutePath());
            qiniuKodoUtil.upload(localFile.getAbsolutePath());
            String fileUrl = qiniuKodoUtil.getFileUrl(localFile.getName());//文件外连
            // 删除临时文件
            localFile.delete();
            System.out.println(content.toString());
            String token = threadLocalToken.getToken();
            String username = JwtUtil.getUsername(token);
            System.out.println(username);
            User user = userService.selelctemail(username);
            UserAdditional additional = userAdditionalService.select(user.getUserid());
            System.out.println(additional.getUserphoto());
            Article article = new Article(null,titles,username,fileUrl,content,0,additional.getUserphoto(),tags,type);
            System.out.println(article.toString());
            articleService.add(article);
            return new Resoult(200, null, null);
        } catch (IOException e) {
            return new Resoult(400, null, null);
        }

    }

    /**
     * 文章全查
     * @return
     */
    @PostMapping("findall/")
    public Resoult findall() {
        List<Article> list = articleService.selectall();
        return new Resoult(200, list, null);
    }
    @PostMapping("adminshearticle/")
    public Resoult adminshearticle(@RequestBody Map<String,String> data) {
        String id = data.get("id");
        Article article = articleMapper.selectOne(new LambdaQueryWrapper<Article>().eq(Article::getArticleid, id));
        article.setArticlestatus(1);
        int id1 = articleMapper.updateById(article);
        return new Resoult(200, "成功", null);
    }

    /**
     * 标签搜索的文章
     * @param articleTags
     * @return
     */
    @PostMapping("findtagsariticle/")
    public Resoult findtagsariticle(@RequestBody ArticleTags articleTags) {
        List<Article> list = articleService.selectags(articleTags);
        return new Resoult(200, list, null);
    }

    /**
     * 星标签文章 推荐
     * @param articleTags
     * @return
     */
    @PostMapping("findallxin/")
    public Resoult findallxin(@RequestBody ArticleTags articleTags) {
        List<Article> list = articleService.selectallxin(articleTags);
        return new Resoult(200, list, null);
    }

    /**
     *删除文章
     * @param articleTags
     * @return
     */
    @DeleteMapping("/articles/{id}")
        public Resoult deletedById(@PathVariable Integer id) {
         articleService.deletebyarticleid(id);
        return new Resoult(200, null, null);
    }


    /**
     * 我的文章全差**/
    @PostMapping("findallme/")
    public Resoult findallme() {
        String token = threadLocalToken.getToken();
        String email = JwtUtil.getUsername(token);
        List<Article> list = articleService.selectallme(email);
        return new Resoult(200, list, null);
    }

  }