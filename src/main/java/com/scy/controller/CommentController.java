package com.scy.controller;

import com.scy.config.JwtUtil;
import com.scy.config.ThreadLocalToken;
import com.scy.from.CommentInster;
import com.scy.from.message.FileSCommentInster;
import com.scy.mapper.UserMapper;
import com.scy.mogomapper.Mongomapper;
import com.scy.pojo.User;
import com.scy.pojo.UserAdditional;
import com.scy.pojo.mogopojo.Comment;
import com.scy.pojo.mogopojo.SecondComment;
import com.scy.resoult.Resoult;
import com.scy.service.UserAdditionalService;
import com.scy.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.nio.file.FileStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.apache.ibatis.ognl.DynamicSubscript.all;
/**评论**/
@RestController
@RequestMapping("comment/")
@CrossOrigin
@Slf4j
public class CommentController {
    @Autowired
    UserAdditionalService userAdditionalService;
    @Autowired
    UserService userService;
    @Autowired
    private ThreadLocalToken threadLocal;

    @Autowired
    Mongomapper mongomapper;
    @Autowired
    MongoTemplate mongoTemplate;
    @PostMapping ("mongo/")
    @ResponseBody
    public List<Comment>getComment(@RequestBody Map<String, String> requestBody){//文章id
        String email = requestBody.get("articleId");
        Query query = new Query(
                new Criteria().andOperator(
                        Criteria.where("articleid").is(email),
                        Criteria.where("secondComment").exists(false)
                )
        );
        List<Comment> list = mongoTemplate.find(query, Comment.class);
        System.out.println(123);
        List<Comment> all1 = mongomapper.findAll();
        return list;
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
        String threadLocalToken = threadLocal.getToken();
        String username = JwtUtil.getUsername(threadLocalToken);
        User existingUser = userService.selelctemail(username);
        UserAdditional additional = userAdditionalService.select(existingUser.getUserid());
        String userphoto = additional.getUserphoto();
        String userUsername = existingUser.getUsername();
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
            List<SecondComment> secondcomment = comment.getSecondcomment();
            if (secondcomment == null){
                secondcomment = new ArrayList<>();
                secondcomment.add(new SecondComment(request.getId(), request.getUid(), userUsername, request.getDates(), request.getValue(),userphoto));//体检评论数据
                mongomapper.save(comment);
            }else {
            List<SecondComment> secondCommentList = comment.getSecondcomment();
            //拿到二级评论集合
            secondCommentList.add(new SecondComment(request.getId(), request.getUid(), userUsername, request.getDates(), request.getValue(),userphoto));//体检评论数据
            for ( SecondComment secondComment:secondCommentList){
                System.out.println(secondComment.toString());//遍历出来了新插入的数据 但是mongodb数据库里边没有刷新出来 也即是没有持久化存储 前端也没有遍历出来 注意最后保存 save（）
            }}
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
    public String gettexts(@RequestBody FileSCommentInster commentInster){
        String threadLocalToken = threadLocal.getToken();
        String username = JwtUtil.getUsername(threadLocalToken);
        User existingUser = userService.selelctemail(username);
        UserAdditional additional = userAdditionalService.select(existingUser.getUserid());
        String userphoto = additional.getUserphoto();
        ////id  Articleid name date commentcontext  SecondComment  (string)
        log.info("222"+commentInster.getValue());
        Comment comment = mongomapper.insert(new Comment(null, commentInster.getId(), existingUser.getUsername(), commentInster.getDates(), commentInster.getValue(),userphoto, new ArrayList<SecondComment>()));
        Comment saved = mongomapper.save(comment);
        //插入到mongodb
        return null;
    }


    /**
     * 评论审查操作
     * @return
     */
    @PostMapping("/commentdo")
    @RequiresRoles(value={"ADMIN", "SUPER"}, logical= Logical.OR)
    public Resoult commentdo(@RequestBody Map<String ,String> id) {
        String string = id.get("id");
        mongomapper.deleteById(string);
        return new Resoult(200,"删除成功",null);
    }

    /**
     * 评论全查
     * @return
     */
    @PostMapping("/commentall")
    @RequiresRoles("ADMIN")
    public Resoult commentall() {
        List<Comment> commentList = mongomapper.findAll();
        return new Resoult(200,commentList,null);
    }

}
