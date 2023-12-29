package com.scy.demo;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.scy.pojo.mogopojo.Comment;
import com.scy.pojo.mogopojo.SecondComment;
import org.bson.Document;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
/**
 * mongoTemplate.findAll(User.class): 查询User文档的全部数据
 * mongoTemplate.findById(, User.class): 查询User文档id为id的数据
 * mongoTemplate.find(query, User.class);: 根据query内的查询条件查询
 * mongoTemplate.upsert(query, update, User.class): 修改
 * mongoTemplate.remove(query, User.class): 删除
 * mongoTemplate.insert(User): 新增
 * Query对象
 * 1、创建一个query对象（用来封装所有条件对象)，再创建一个criteria对象（用来构建条件）
 * 2、 精准条件：criteria.and(“key”).is(“条件”)
 * 模糊条件：criteria.and(“key”).regex(“条件”)
 * 3、封装条件：query.addCriteria(criteria)
 * 4、大于（创建新的criteria）：Criteria gt = Criteria.where(“key”).gt（“条件”）
 * 小于（创建新的criteria）：Criteria lt = Criteria.where(“key”).lt（“条件”）
 * 5、Query.addCriteria(new Criteria().andOperator(gt,lt));
 * 6、一个query中只能有一个andOperator()。其参数也可以是Criteria数组。
 * 7、排序 ：query.with（new Sort(Sort.Direction.ASC, “age”). and(new Sort(Sort.Direction.DESC, “date”)))**/
@SpringBootTest
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
class AnimalsApplicationTests {
    @Autowired
      private JavaMailSender javaMailSender;
    @Autowired
   private   MongoTemplate  mongoTemplate;
/*   @Test
   void contextLoads() {
       SimpleMailMessage message = new SimpleMailMessage();
       message.setFrom("3328397500@qq.com");//发送者
       message.setTo("2402265378@qq.com");//接收者
       message.setSubject("流浪动物管理栈-----邮件注册验证码");//主题设置
       message.setText("你好啊 helloworld");//内容
       javaMailSender.send(message);
   }*/
    /*ArrayList<SecondComment> secondComments = new ArrayList<>();
/      secondComments.add(new SecondComment("3-1", 2, "露娜", "2023.10.30", "你好至尊宝"));
        Comment comment = new Comment("3", 1, "至尊宝", "2023.10.11", "你好啊 紫霞", secondComments);
//        mongoTemplate.insert(comment);*/
//        //*查询所有数据*//*
//       /* List<? extends Comment> all = mongoTemplate.findAll(comment.getClass());
//        System.out.println(all);
//       *//*查询数据依据与条件*//*
//        Query query = new Query(Criteria.where("name").is("lisi"));
//        List<? extends Comment> list = mongoTemplate.find(query, Comment.class);
//     *//*   String string = list.toString();
//        int articleid = comment.getArticleid();
//        System.out.println(articleid);*//*
//        for (Comment comments: list){
//            System.out.println(comments.getArticleid()+" "+comments.getSecondComment());
//
//        }
//        System.out.println("条件查询"+list);
//    }*/
//    }
    @Test
    public String getMessage(){ //为什么运行报错  Process finished with exit code 0
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("3328397500@qq.com");//发送者
        message.setTo("18537363992@163.com");//接收者
        message.setSubject("测试邮件");//主题设置
        message.setText("你好啊 helloworld");//内容
        javaMailSender.send(message);

        return "okkkk";


    }
}
