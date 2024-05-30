package pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 寄养表
 * @TableName animals_fostercare
 */
@TableName(value ="animals_fostercare")
@Data
public class AnimalsFostercare implements Serializable {
    /**
     * 
     */
    @TableField(value = "foster_id")
    private Integer foster_id;

    /**
     * 类型
     */
    @TableField(value = "forst_type")
    private String forst_type;

    /**
     * 日期
     */
    @TableField(value = "forst_date")
    private String forst_date;

    /**
     * 用户名字
     */
    @TableField(value = "useremail")
    private String useremail;

    /**
     * 寄养动物的健康
     */
    @TableField(value = "forst_healthy")
    private String forst_healthy;

    /**
     * 接受通知吗
     */
    @TableField(value = "forst_shadw")
    private Integer forst_shadw;

    /**
     * 寄养地
     */
    @TableField(value = "forst_space")
    private String forst_space;

    /**
     * 寄养年龄
     */
    @TableField(value = "forst_age")
    private String forst_age;

    /**
     * 照片
     */
    @TableField(value = "article_tags")
    private String article_tags;

    /**
     * 动物名字
     */
    @TableField(value = "name")
    private String name;

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
        AnimalsFostercare other = (AnimalsFostercare) that;
        return (this.getFoster_id() == null ? other.getFoster_id() == null : this.getFoster_id().equals(other.getFoster_id()))
            && (this.getForst_type() == null ? other.getForst_type() == null : this.getForst_type().equals(other.getForst_type()))
            && (this.getForst_date() == null ? other.getForst_date() == null : this.getForst_date().equals(other.getForst_date()))
            && (this.getUseremail() == null ? other.getUseremail() == null : this.getUseremail().equals(other.getUseremail()))
            && (this.getForst_healthy() == null ? other.getForst_healthy() == null : this.getForst_healthy().equals(other.getForst_healthy()))
            && (this.getForst_shadw() == null ? other.getForst_shadw() == null : this.getForst_shadw().equals(other.getForst_shadw()))
            && (this.getForst_space() == null ? other.getForst_space() == null : this.getForst_space().equals(other.getForst_space()))
            && (this.getForst_age() == null ? other.getForst_age() == null : this.getForst_age().equals(other.getForst_age()))
            && (this.getArticle_tags() == null ? other.getArticle_tags() == null : this.getArticle_tags().equals(other.getArticle_tags()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getFoster_id() == null) ? 0 : getFoster_id().hashCode());
        result = prime * result + ((getForst_type() == null) ? 0 : getForst_type().hashCode());
        result = prime * result + ((getForst_date() == null) ? 0 : getForst_date().hashCode());
        result = prime * result + ((getUseremail() == null) ? 0 : getUseremail().hashCode());
        result = prime * result + ((getForst_healthy() == null) ? 0 : getForst_healthy().hashCode());
        result = prime * result + ((getForst_shadw() == null) ? 0 : getForst_shadw().hashCode());
        result = prime * result + ((getForst_space() == null) ? 0 : getForst_space().hashCode());
        result = prime * result + ((getForst_age() == null) ? 0 : getForst_age().hashCode());
        result = prime * result + ((getArticle_tags() == null) ? 0 : getArticle_tags().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", foster_id=").append(foster_id);
        sb.append(", forst_type=").append(forst_type);
        sb.append(", forst_date=").append(forst_date);
        sb.append(", useremail=").append(useremail);
        sb.append(", forst_healthy=").append(forst_healthy);
        sb.append(", forst_shadw=").append(forst_shadw);
        sb.append(", forst_space=").append(forst_space);
        sb.append(", forst_age=").append(forst_age);
        sb.append(", article_tags=").append(article_tags);
        sb.append(", name=").append(name);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}