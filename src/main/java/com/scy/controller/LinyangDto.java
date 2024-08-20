package com.scy.controller;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.scy.pojo.Animal;
import lombok.Data;

@Data
public class LinyangDto {
            //动物名字
          // 动物图片
          //领养人
         // 健康状态
          //发现时间

    /**
     * 动物名字
     */

    private String animalName;

    /**
     * 发现日期
     */

    private String annimalDate;


    /**
     * 健康状态
     */

    private String animalsHeathly;

    /**
     * 照片
     */

    private String animalsPhoto;

    /**
     * 领养人邮箱
     */

    private String usernmail;
    //id

    private Integer adoptId;
    private Integer animalsId;
    //审核
    private Integer adminid;





}
