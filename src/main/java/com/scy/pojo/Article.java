package com.scy.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName article
 */
@TableName(value ="article")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer articleId;

    /**
     * 
     */
    private String articleName;

    /**
     * 
     */
    private String articleAuthor;

    /**
     * 
     */
    private String articleContext;

    /**
     * 
     */
    private byte[] authorIamge;

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
        Article other = (Article) that;
        return (this.getArticleId() == null ? other.getArticleId() == null : this.getArticleId().equals(other.getArticleId()))
            && (this.getArticleName() == null ? other.getArticleName() == null : this.getArticleName().equals(other.getArticleName()))
            && (this.getArticleAuthor() == null ? other.getArticleAuthor() == null : this.getArticleAuthor().equals(other.getArticleAuthor()))
            && (this.getArticleContext() == null ? other.getArticleContext() == null : this.getArticleContext().equals(other.getArticleContext()))
            && (Arrays.equals(this.getAuthorIamge(), other.getAuthorIamge()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getArticleId() == null) ? 0 : getArticleId().hashCode());
        result = prime * result + ((getArticleName() == null) ? 0 : getArticleName().hashCode());
        result = prime * result + ((getArticleAuthor() == null) ? 0 : getArticleAuthor().hashCode());
        result = prime * result + ((getArticleContext() == null) ? 0 : getArticleContext().hashCode());
        result = prime * result + (Arrays.hashCode(getAuthorIamge()));
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", articleName=").append(articleName);
        sb.append(", articleAuthor=").append(articleAuthor);
        sb.append(", articleContext=").append(articleContext);
        sb.append(", authorIamge=").append(authorIamge);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}