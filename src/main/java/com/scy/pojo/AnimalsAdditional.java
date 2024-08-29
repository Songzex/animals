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
 * 
 * @TableName animals_additional
 */
@TableName(value ="animals_additional")
@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AnimalsAdditional implements Serializable {
    /**
     * 动物的id
     */
    @TableField(value = "animals_id")
    private String animals_id;

    /**
     * 动物的状态
     */
    @TableField(value = "animals_status")
    private String animals_status;

    /**
     * 动物的过往
     */
    @TableField(value = "animals_hostily")
    private String animals_hostily;

    /**
     * 志愿者邮箱
     */
    @TableField(value = "volunteers_email")
    private String volunteers_email;
    @TableId(type = IdType.AUTO)
    private Integer id;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}