package com.scy.pojo.mogopojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comment")
public class Comment {
    @Id
    private   String  id;
    private  String  Articleid; //id Articleid name date commentcontext  SecondComment

    private  String name;

    private  String date;

    private String commentcontext;


    private List<SecondComment> SecondComment;



}
