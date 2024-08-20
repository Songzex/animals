package com.scy.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName article
 */
@TableName(value ="article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    /**
     * 
     */
    @TableId(value = "article_id")
    private Integer articleid;

    /**
     * 
     */
    @TableField(value = "article_name")
    private String articlename;

    /**
     * 作者的邮箱
     */
    @TableField(value = "article_email")
    private String articleemail;

    /**
     * 
     */
    @TableField(value = "author_iamge")
    private String authoriamge;

    /*
     * 
     */
    @TableField(value = "article_context")
    private String articlecontext;

    /**
     * 
     */
    @TableField(value = "article_status")
    private Integer articlestatus;

    @TableField(value = "article_autherphoto")
    private String articleautherphoto;

    @TableField(value = "article_tags")
    private String articletags;


    @TableField(value = "article_about")
    private String articletype;



    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Integer getArticleid() {
        return articleid;
    }

    public void setArticleid(Integer articleid) {
        this.articleid = articleid;
    }

    public String getArticlename() {
        return articlename;
    }

    public void setArticlename(String articlename) {
        this.articlename = articlename;
    }

    public String getArticleemail() {
        return articleemail;
    }

    public void setArticleemail(String articleemail) {
        this.articleemail = articleemail;
    }

    public String getAuthoriamge() {
        return authoriamge;
    }

    public void setAuthoriamge(String authoriamge) {
        this.authoriamge = authoriamge;
    }

    public String getArticlecontext() {
        return articlecontext;
    }

    public void setArticlecontext(String articlecontext) {
        this.articlecontext = articlecontext;
    }

    public Integer getArticlestatus() {
        return articlestatus;
    }

    public void setArticlestatus(Integer articlestatus) {
        this.articlestatus = articlestatus;
    }

    public String getArticleautherphoto() {
        return articleautherphoto;
    }

    public void setArticleautherphoto(String articleautherphoto) {
        this.articleautherphoto = articleautherphoto;
    }
}