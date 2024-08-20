package com.scy.controller.uservo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class UserAdmin {

    private Integer userid;

    /**
     *
     */
    private String username;

    /**
     *
     */
    private String email;

    /**
     *
     */
    private String password;

    /**
     *
     */
    private String telet;

    /**
     *
     */
    private Integer sex;

    /**
     *
     */
    private Integer age;
    ////////

    /**
     *
     */
    private String userphoto;

    /**
     *
     */
    private String usersign;
    //
    private String roles;
    private String staus;





}
