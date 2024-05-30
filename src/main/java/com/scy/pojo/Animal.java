package pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 网站动物进站表
 * @TableName animal
 */
@TableName(value ="animal")
@Data
public class Animal implements Serializable {
    /**
     * 动物id
     */
    @TableId(value = "animal_id", type = IdType.AUTO)
    private Integer animal_id;

    /**
     * 动物年龄
     */
    @TableField(value = "animal_age")
    private Integer animal_age;

    /**
     * 动物性别
     */
    @TableField(value = "animal_sex")
    private Integer animal_sex;

    /**
     * 动物名字
     */
    @TableField(value = "animal_name")
    private String animal_name;

    /**
     * 发现日期
     */
    @TableField(value = "annimal_date")
    private String annimal_date;

    /**
     * 是否跟踪
     */
    @TableField(value = "animal_shadow")
    private Integer animal_shadow;

    /**
     * 健康状态
     */
    @TableField(value = "animals_heathly")
    private String animals_heathly;

    /**
     * 照片
     */
    @TableField(value = "animals_photo")
    private String animals_photo;

    /**
     * 备注
     */
    @TableField(value = "animals_notes")
    private String animals_notes;

    /**
     * 邮箱
     */
    @TableField(value = "animals_uemail")
    private String animals_uemail;

    /**
     * 地址
     */
    @TableField(value = "animals_where")
    private String animals_where;

    /**
     * 种类
     */
    @TableField(value = "animals_type")
    private String animals_type;

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
        return (this.getAnimal_id() == null ? other.getAnimal_id() == null : this.getAnimal_id().equals(other.getAnimal_id()))
            && (this.getAnimal_age() == null ? other.getAnimal_age() == null : this.getAnimal_age().equals(other.getAnimal_age()))
            && (this.getAnimal_sex() == null ? other.getAnimal_sex() == null : this.getAnimal_sex().equals(other.getAnimal_sex()))
            && (this.getAnimal_name() == null ? other.getAnimal_name() == null : this.getAnimal_name().equals(other.getAnimal_name()))
            && (this.getAnnimal_date() == null ? other.getAnnimal_date() == null : this.getAnnimal_date().equals(other.getAnnimal_date()))
            && (this.getAnimal_shadow() == null ? other.getAnimal_shadow() == null : this.getAnimal_shadow().equals(other.getAnimal_shadow()))
            && (this.getAnimals_heathly() == null ? other.getAnimals_heathly() == null : this.getAnimals_heathly().equals(other.getAnimals_heathly()))
            && (this.getAnimals_photo() == null ? other.getAnimals_photo() == null : this.getAnimals_photo().equals(other.getAnimals_photo()))
            && (this.getAnimals_notes() == null ? other.getAnimals_notes() == null : this.getAnimals_notes().equals(other.getAnimals_notes()))
            && (this.getAnimals_uemail() == null ? other.getAnimals_uemail() == null : this.getAnimals_uemail().equals(other.getAnimals_uemail()))
            && (this.getAnimals_where() == null ? other.getAnimals_where() == null : this.getAnimals_where().equals(other.getAnimals_where()))
            && (this.getAnimals_type() == null ? other.getAnimals_type() == null : this.getAnimals_type().equals(other.getAnimals_type()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAnimal_id() == null) ? 0 : getAnimal_id().hashCode());
        result = prime * result + ((getAnimal_age() == null) ? 0 : getAnimal_age().hashCode());
        result = prime * result + ((getAnimal_sex() == null) ? 0 : getAnimal_sex().hashCode());
        result = prime * result + ((getAnimal_name() == null) ? 0 : getAnimal_name().hashCode());
        result = prime * result + ((getAnnimal_date() == null) ? 0 : getAnnimal_date().hashCode());
        result = prime * result + ((getAnimal_shadow() == null) ? 0 : getAnimal_shadow().hashCode());
        result = prime * result + ((getAnimals_heathly() == null) ? 0 : getAnimals_heathly().hashCode());
        result = prime * result + ((getAnimals_photo() == null) ? 0 : getAnimals_photo().hashCode());
        result = prime * result + ((getAnimals_notes() == null) ? 0 : getAnimals_notes().hashCode());
        result = prime * result + ((getAnimals_uemail() == null) ? 0 : getAnimals_uemail().hashCode());
        result = prime * result + ((getAnimals_where() == null) ? 0 : getAnimals_where().hashCode());
        result = prime * result + ((getAnimals_type() == null) ? 0 : getAnimals_type().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", animal_id=").append(animal_id);
        sb.append(", animal_age=").append(animal_age);
        sb.append(", animal_sex=").append(animal_sex);
        sb.append(", animal_name=").append(animal_name);
        sb.append(", annimal_date=").append(annimal_date);
        sb.append(", animal_shadow=").append(animal_shadow);
        sb.append(", animals_heathly=").append(animals_heathly);
        sb.append(", animals_photo=").append(animals_photo);
        sb.append(", animals_notes=").append(animals_notes);
        sb.append(", animals_uemail=").append(animals_uemail);
        sb.append(", animals_where=").append(animals_where);
        sb.append(", animals_type=").append(animals_type);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}