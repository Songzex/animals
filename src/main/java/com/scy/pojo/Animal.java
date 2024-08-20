package com.scy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 网站动物进站表
 * @TableName animal
 */
@TableName(value ="animal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Animal implements Serializable {
    /**
     * 动物id
     */
    @TableId(value = "animal_id", type = IdType.AUTO)
    private Integer animalId;

    /**
     * 动物年龄
     */
    @TableField(value = "animal_age")
    private Integer animalAge;

    /**
     * 动物性别
     */
    @TableField(value = "animal_sex")
    private Integer animalSex;

    /**
     * 动物名字
     */
    @TableField(value = "animal_name")
    private String animalName;

    /**
     * 发现日期
     */
    @TableField(value = "annimal_date")
    private String annimalDate;

    /**
     * 审核
     */
    @TableField(value = "animal_shadow")
    private Integer animalShadow;

    /**
     * 健康状态
     */
    @TableField(value = "animals_heathly")
    private String animalsHeathly;

    /**
     * 照片
     */
    @TableField(value = "animals_photo")
    private String animalsPhoto;

    /**
     * 备注
     */
    @TableField(value = "animals_notes")
    private String animalsNotes;

    /**
     * 邮箱
     */
    @TableField(value = "animals_uemail")
    private String animalsUemail;

    /**
     * 地址
     */
    @TableField(value = "animals_where")
    private String animalsWhere;

    /**
     * 种类
     */
    @TableField(value = "animals_type")
    private String animalsType;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(animalId, animal.animalId) && Objects.equals(animalAge, animal.animalAge) && Objects.equals(animalSex, animal.animalSex) && Objects.equals(animalName, animal.animalName) && Objects.equals(annimalDate, animal.annimalDate) && Objects.equals(animalShadow, animal.animalShadow) && Objects.equals(animalsHeathly, animal.animalsHeathly) && Objects.equals(animalsPhoto, animal.animalsPhoto) && Objects.equals(animalsNotes, animal.animalsNotes) && Objects.equals(animalsUemail, animal.animalsUemail) && Objects.equals(animalsWhere, animal.animalsWhere) && Objects.equals(animalsType, animal.animalsType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(animalId, animalAge, animalSex, animalName, annimalDate, animalShadow, animalsHeathly, animalsPhoto, animalsNotes, animalsUemail, animalsWhere, animalsType);
    }
}