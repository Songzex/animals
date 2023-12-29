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
    private String value;
    private String id;
    private String  dates;
    private String uid;
    private  String uname;

}
