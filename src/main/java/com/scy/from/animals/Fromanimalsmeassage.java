package com.scy.from.animals;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fromanimalsmeassage {
    private  String  name;
    private  String region;
    private String date;
    private Integer shadow;
    private Integer sex;
    private String helathly;
    private String notes;

}
