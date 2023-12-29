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
 * @TableName adopt
 */
@TableName(value ="adopt")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adopt implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer adoptId;

    /**
     * 
     */
    private String userid;

    /**
     * 
     */
    private String adoptContext;

    /**
     * 
     */
    private Integer animalId;

    /**
     * 
     */
    private Integer adminId;

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
        Adopt other = (Adopt) that;
        return (this.getAdoptId() == null ? other.getAdoptId() == null : this.getAdoptId().equals(other.getAdoptId()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getAdoptContext() == null ? other.getAdoptContext() == null : this.getAdoptContext().equals(other.getAdoptContext()))
            && (this.getAnimalId() == null ? other.getAnimalId() == null : this.getAnimalId().equals(other.getAnimalId()))
            && (this.getAdminId() == null ? other.getAdminId() == null : this.getAdminId().equals(other.getAdminId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAdoptId() == null) ? 0 : getAdoptId().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getAdoptContext() == null) ? 0 : getAdoptContext().hashCode());
        result = prime * result + ((getAnimalId() == null) ? 0 : getAnimalId().hashCode());
        result = prime * result + ((getAdminId() == null) ? 0 : getAdminId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adoptId=").append(adoptId);
        sb.append(", userid=").append(userid);
        sb.append(", adoptContext=").append(adoptContext);
        sb.append(", animalId=").append(animalId);
        sb.append(", adminId=").append(adminId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}