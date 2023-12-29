package com.scy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.pojo.Article;
import com.scy.mapper.ArticleMapper;
import com.scy.service.ArticleService;
import org.springframework.stereotype.Service;

/**
* @author 24022
* @description 针对表【article】的数据库操作Service实现
* @createDate 2023-12-03 14:55:46
*/
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
    implements ArticleService {

}




