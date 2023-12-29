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
 * @TableName admin
 */
@TableName(value ="admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer adId;

    /**
     * 
     */
    private String adName;

    /**
     * 
     */
    private Integer adSex;

    /**
     * 
     */
    private String adEmail;

    /**
     * 
     */
    private String adPassword;

    /**
     * 
     */
    private Integer adAge;

    /**
     * 
     */
    private Integer adTelet;

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
        Admin other = (Admin) that;
        return (this.getAdId() == null ? other.getAdId() == null : this.getAdId().equals(other.getAdId()))
            && (this.getAdName() == null ? other.getAdName() == null : this.getAdName().equals(other.getAdName()))
            && (this.getAdSex() == null ? other.getAdSex() == null : this.getAdSex().equals(other.getAdSex()))
            && (this.getAdEmail() == null ? other.getAdEmail() == null : this.getAdEmail().equals(other.getAdEmail()))
            && (this.getAdPassword() == null ? other.getAdPassword() == null : this.getAdPassword().equals(other.getAdPassword()))
            && (this.getAdAge() == null ? other.getAdAge() == null : this.getAdAge().equals(other.getAdAge()))
            && (this.getAdTelet() == null ? other.getAdTelet() == null : this.getAdTelet().equals(other.getAdTelet()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAdId() == null) ? 0 : getAdId().hashCode());
        result = prime * result + ((getAdName() == null) ? 0 : getAdName().hashCode());
        result = prime * result + ((getAdSex() == null) ? 0 : getAdSex().hashCode());
        result = prime * result + ((getAdEmail() == null) ? 0 : getAdEmail().hashCode());
        result = prime * result + ((getAdPassword() == null) ? 0 : getAdPassword().hashCode());
        result = prime * result + ((getAdAge() == null) ? 0 : getAdAge().hashCode());
        result = prime * result + ((getAdTelet() == null) ? 0 : getAdTelet().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", adId=").append(adId);
        sb.append(", adName=").append(adName);
        sb.append(", adSex=").append(adSex);
        sb.append(", adEmail=").append(adEmail);
        sb.append(", adPassword=").append(adPassword);
        sb.append(", adAge=").append(adAge);
        sb.append(", adTelet=").append(adTelet);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}