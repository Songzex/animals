package com.scy.from;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FromUser {
    /* pass: "",
        age: "",
        name: "",
        email: "",
        sex: "",*/
    private String pass;
    private String name;
    private String id;
    private int  age;
    private String email;
    private String sex;
}
