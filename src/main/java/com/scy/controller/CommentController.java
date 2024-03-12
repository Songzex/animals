package com.scy.controller;

import com.scy.from.CommentInster;
import com.scy.mogomapper.Mongomapper;
import com.scy.pojo.mogopojo.Comment;
import com.scy.pojo.mogopojo.SecondComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.apache.ibatis.ognl.DynamicSubscript.all;
/**评论**/
@RestController
@RequestMapping("comment/")
@CrossOrigin
public class CommentController {
    @Autowired
    Mongomapper mongomapper;
    @Autowired
    MongoTemplate mongoTemplate;
    @GetMapping ("mongo/")
    @ResponseBody
    public List<Comment>getComment(){
        System.out.println(123);
        List<Comment> all1 = mongomapper.findAll();
        return all1;
    }
    @GetMapping ("list/")
    public String get(){

        return "ok";
    }
    /**插入二级评论**/
    @PostMapping ("text/")
    @CrossOrigin
    @ResponseBody
    public String gettext(@RequestBody CommentInster request){
       /* Object email = request.getValue();//n
        System.out.println(email);//null
        Object id = request.getId();
        System.out.println(id);
        System.out.println(1);
        System.out.println(request.toString());//为什么页面一会能实时刷新出来刚刚提交的数据 一会就不能能实时响应
        new SecondComment();*/
        System.out.println(request.getUname());//获取当前用户的名字
        Optional<Comment> commentOptional = mongomapper.findById(request.getId()); //查询一级的评论
        if (commentOptional.isPresent()) {
            Comment comment = commentOptional.get();
            System.out.println(comment);
            List<SecondComment> secondCommentList = comment.getSecondComment();//拿到二级评论集合
            secondCommentList.add(new SecondComment(request.getId(), request.getUid(), request.getUname(), request.getDates(), request.getValue()));//体检评论数据
            for ( SecondComment secondComment:secondCommentList){
                System.out.println(secondComment.toString());//遍历出来了新插入的数据 但是mongodb数据库里边没有刷新出来 也即是没有持久化存储 前端也没有遍历出来 注意最后保存 save（）
            }
            mongomapper.save(comment);// 提交到数据库保存
        }//输出以及评论

        /*Query query = new Query(Criteria.where("id").is(request.getId()));//null
        List<Comment> list = mongoTemplate.find(query, Comment.class);
        for ( Comment comment:list){
            System.out.println("vvvvv"+comment.toString());
            List<SecondComment> secondComment = comment.getSecondComment();//查到了一级id的二级评论集合
            SecondComment secondComment1 = new SecondComment(request.getId(), request.getUid(), request.getUname(), request.getDates(), request.getValue());
            boolean add = secondComment.add(secondComment1);
            System.out.println(add);
        }*/
      /*  mongomapper.i*/
        return "okkkkkkkkk";
    }
    /**插入一级评论评论**/
    @PostMapping ("texts/")
    @CrossOrigin
    @ResponseBody
    public String gettexts(){  ////id  Articleid name date commentcontext  SecondComment  (string)
         Comment comment = mongomapper.insert(new Comment("12", "12-1", "诸葛亮", "2023-12-8", "神机妙算。运筹  帷幄", new ArrayList<SecondComment>()));
         mongomapper.save(comment);
        //插入到mongodb
        System.out.println(123);
        return null;
    }

}
