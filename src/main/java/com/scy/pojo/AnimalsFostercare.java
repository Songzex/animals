package com.scy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 寄养表
 * @TableName animals_fostercare
 */
@TableName(value ="animals_fostercare")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class AnimalsFostercare implements Serializable {
    /**
     * 
     */
    @TableId(value = "foster_id", type = IdType.AUTO)
    private Integer fosterid;


    /**
     * 类型
     */
    @TableField(value = "forst_type")
    private String forsttype;

    /**
     * 日期
     */
    @TableField(value = "forst_date")
    private String forstdate;

    /**
     * 用户名字
     */
    @TableField(value = "useremail")
    private String useremail;

    /**
     * 寄养动物的健康
     */
    @TableField(value = "forst_healthy")
    private String forsthealthy;

    /**
     * 接受通知吗
     */
    @TableField(value = "forst_shadw")
    private Integer forstshadw;

    /**
     * 寄养地
     */
    @TableField(value = "forst_space")
    private String forstspace;

    /**
     * 寄养年龄
     */
    @TableField(value = "forst_age")
    private String forstage;

    /**
     * 照片
     */
    @TableField(value = "article_tags")
    private String Forstphoto;

    /**
     * 动物名字
     */
    @TableField(value = "name")
    private String name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}