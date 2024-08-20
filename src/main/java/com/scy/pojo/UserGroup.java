package com.scy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 
 * @TableName user_group
 */
@TableName(value ="user_group")
@Data
@EqualsAndHashCode
public class UserGroup implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 群主名字邮箱
     */
    @TableField(value = "admin_name")
    private String adminname;

    /**
     * 群组类别
     */
    @TableField(value = "groupid")
    private String groupid;

    /**
     * 群组头像
     */
    @TableField(value = "group_photo")
    private String groupphoto;

    /**
     * 群组的描述
     */
    @TableField(value = "group_detils")
    private String groupdetils;

    /**
     * 人数
     */
    @TableField(value = "group_count")
    private Integer groupcount;

    /**
     * 群组的名字
     */
    @TableField(value = "gruop_selefname")
    private String gruopselefname;

    /**
     * 群组标签
     */
    @TableField(value = "group_tags")
    private String grouptags;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}