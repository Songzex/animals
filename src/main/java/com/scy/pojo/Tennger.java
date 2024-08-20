package com.scy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 
 * @TableName tennger
 */
@TableName(value ="tennger")
@Data
@AllArgsConstructor@NoArgsConstructor
@EqualsAndHashCode
public class Tennger implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 用户id
     */
    @TableField(value = "userid")
    private Integer userid;

    /**
     * 救助id
     */
    @TableField(value = "count")
    private Integer count;    /**
     * 救助id
     */
    @TableField(value = "space")
    private String space;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}