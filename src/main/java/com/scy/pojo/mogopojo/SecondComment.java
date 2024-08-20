package com.scy.pojo.mogopojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comment")
public class SecondComment {
    @Id
    private   String  id;
    private  String  articleid;

    private  String name;

    private  String date;

    private String commentcontext;
    private String photo;

}
