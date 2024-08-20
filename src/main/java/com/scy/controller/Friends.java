package com.scy.controller;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class Friends{

    private Integer userid;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String telet;

    /**
     *
     */
    private Integer sex;

    /**
     *
     */
    private Integer age;

    private  Integer deleted;
    private String userphoto;


    private String usersign;



    private String usertitle;

}
