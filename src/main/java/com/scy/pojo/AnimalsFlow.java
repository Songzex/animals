package com.scy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName animals_flow
 */
@TableName(value ="animals_flow")
@Data
public class AnimalsFlow implements Serializable {
    /**
     * 流转id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 动物的id(（寄养1，一般0)
     */
    @TableField(value = "animal_id")
    private Integer animal_id;

    /**
     * 动物的类型（寄养1，一般0）
     */
    @TableField(value = "animal_type")
    private Integer animal_type;

    /**
     * 动物名字
     */
    @TableField(value = "animal_name")
    private String animal_name;

    /**
     * 流转的描述
     */
    @TableField(value = "anmial_action")
    private String anmial_action;

    /**
     * 动作时间
     */
    @TableField(value = "flow_date")
    private Date flow_date;

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
        AnimalsFlow other = (AnimalsFlow) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAnimal_id() == null ? other.getAnimal_id() == null : this.getAnimal_id().equals(other.getAnimal_id()))
            && (this.getAnimal_type() == null ? other.getAnimal_type() == null : this.getAnimal_type().equals(other.getAnimal_type()))
            && (this.getAnimal_name() == null ? other.getAnimal_name() == null : this.getAnimal_name().equals(other.getAnimal_name()))
            && (this.getAnmial_action() == null ? other.getAnmial_action() == null : this.getAnmial_action().equals(other.getAnmial_action()))
            && (this.getFlow_date() == null ? other.getFlow_date() == null : this.getFlow_date().equals(other.getFlow_date()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAnimal_id() == null) ? 0 : getAnimal_id().hashCode());
        result = prime * result + ((getAnimal_type() == null) ? 0 : getAnimal_type().hashCode());
        result = prime * result + ((getAnimal_name() == null) ? 0 : getAnimal_name().hashCode());
        result = prime * result + ((getAnmial_action() == null) ? 0 : getAnmial_action().hashCode());
        result = prime * result + ((getFlow_date() == null) ? 0 : getFlow_date().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", animal_id=").append(animal_id);
        sb.append(", animal_type=").append(animal_type);
        sb.append(", animal_name=").append(animal_name);
        sb.append(", anmial_action=").append(anmial_action);
        sb.append(", flow_date=").append(flow_date);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}