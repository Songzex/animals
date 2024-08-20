package com.scy.from;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 封装二级评论的from类**/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentInster {
    private String value;//内容
    private String id;//一级id
    private String dates;//日期
    private String uid;//
    private String uname;//用户邮箱
    private String photo;//用户头像

}
