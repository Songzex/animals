package pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName animal
 */
@TableName(value ="animal")
@Data
public class Animal implements Serializable {
    /**
     * 
     */
    @TableId(value = "animal_id", type = IdType.AUTO)
    private Integer animal_id;

    /**
     * 
     */
    @TableField(value = "animal_age")
    private Integer animal_age;

    /**
     * 
     */
    @TableField(value = "animal_sex")
    private Integer animal_sex;

    /**
     * 
     */
    @TableField(value = "animal_name")
    private String animal_name;

    /**
     * 
     */
    @TableField(value = "annimal_date")
    private String annimal_date;

    /**
     * 
     */
    @TableField(value = "animal_shadow")
    private Integer animal_shadow;

    /**
     * 
     */
    @TableField(value = "animals_heathly")
    private String animals_heathly;

    /**
     * 
     */
    @TableField(value = "animals_photo")
    private String animals_photo;

    /**
     * 
     */
    @TableField(value = "animals_notes")
    private String animals_notes;

    /**
     * 
     */
    @TableField(value = "animals_uemail")
    private String animals_uemail;

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
            && (this.getAnimals_uemail() == null ? other.getAnimals_uemail() == null : this.getAnimals_uemail().equals(other.getAnimals_uemail()));
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
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}