package com.scy.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName group_member
 */
@TableName(value ="group_member")
@Data
@AllArgsConstructor
public class GroupMember implements Serializable {
    /**
     * 群组id
     */
    @TableField(value = "group_id")
    private String groupid;

    /**
     * 群成员邮箱
     */
    @TableField(value = "group_memail")
    private String groupmemail;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}