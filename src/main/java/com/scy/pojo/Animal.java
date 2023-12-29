package com.scy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName animal
 */
@TableName(value ="animal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer animalId;

    /**
     * 
     */
    private String animalSpecies;

    /**
     * 
     */
    private Integer animalAge;

    /**
     * 
     */
    private Integer animalSex;

    /**
     * 
     */
    private String animalName;

    /**
     * 
     */
    private Date annimalDate;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Animal other = (Animal) that;
        return (this.getAnimalId() == null ? other.getAnimalId() == null : this.getAnimalId().equals(other.getAnimalId()))
            && (this.getAnimalSpecies() == null ? other.getAnimalSpecies() == null : this.getAnimalSpecies().equals(other.getAnimalSpecies()))
            && (this.getAnimalAge() == null ? other.getAnimalAge() == null : this.getAnimalAge().equals(other.getAnimalAge()))
            && (this.getAnimalSex() == null ? other.getAnimalSex() == null : this.getAnimalSex().equals(other.getAnimalSex()))
            && (this.getAnimalName() == null ? other.getAnimalName() == null : this.getAnimalName().equals(other.getAnimalName()))
            && (this.getAnnimalDate() == null ? other.getAnnimalDate() == null : this.getAnnimalDate().equals(other.getAnnimalDate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAnimalId() == null) ? 0 : getAnimalId().hashCode());
        result = prime * result + ((getAnimalSpecies() == null) ? 0 : getAnimalSpecies().hashCode());
        result = prime * result + ((getAnimalAge() == null) ? 0 : getAnimalAge().hashCode());
        result = prime * result + ((getAnimalSex() == null) ? 0 : getAnimalSex().hashCode());
        result = prime * result + ((getAnimalName() == null) ? 0 : getAnimalName().hashCode());
        result = prime * result + ((getAnnimalDate() == null) ? 0 : getAnnimalDate().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", animalId=").append(animalId);
        sb.append(", animalSpecies=").append(animalSpecies);
        sb.append(", animalAge=").append(animalAge);
        sb.append(", animalSex=").append(animalSex);
        sb.append(", animalName=").append(animalName);
        sb.append(", annimalDate=").append(annimalDate);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}