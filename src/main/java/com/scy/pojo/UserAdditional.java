package com.scy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName user_additional
 */
@TableName(value ="user_additional")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAdditional implements Serializable {
    /**
     * 
     */
    @TableField(value = "user_photo")
    private String userphoto;

    /**
     * 
     */
    @TableField(value = "user_sign")
    private String usersign;

    /**
     * 
     */
    @TableField(value = "user_tele")
    private String usertele;

    /**
     * 
     */
    @TableField(value = "user_id")
    private Integer userid;

    /**
     * 
     */
    @TableField(value = "user_title")
    private String usertitle;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}