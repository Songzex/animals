package com.scy.from.message;

import com.scy.from.CommentInster;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileSCommentInster {
    private String id;
    private String uid;
    private  String dates;

    private  String uname;

    private  String date;

    private String value;
}
