package com.scy.controller.uservo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class grouplist {

    private Integer id;


    private String adminname;


    private String groupid;



    private String groupphoto;

    private String groupdetils;

    private Integer groupcount;


    private String gruopselefname;


    private String grouptags;
    private String photo;
}
