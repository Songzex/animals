package com.scy.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName user_friendes
 */
@TableName(value ="user_friendes")
@Data
@AllArgsConstructor
public class UserFriendes implements Serializable {
    /**
     * 
     */
    @TableField(value = "user_firends")
    private String user_firends;

    /**
     * 
     */
    @TableField(value = "user_email")
    private String user_email;

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
        UserFriendes other = (UserFriendes) that;
        return (this.getUser_firends() == null ? other.getUser_firends() == null : this.getUser_firends().equals(other.getUser_firends()))
            && (this.getUser_email() == null ? other.getUser_email() == null : this.getUser_email().equals(other.getUser_email()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getUser_firends() == null) ? 0 : getUser_firends().hashCode());
        result = prime * result + ((getUser_email() == null) ? 0 : getUser_email().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", user_firends=").append(user_firends);
        sb.append(", user_email=").append(user_email);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}