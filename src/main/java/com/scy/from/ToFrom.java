package com.scy.from;

import com.scy.pojo.User;
import com.scy.pojo.UserAdditional;
import com.scy.pojo.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToFrom {
    private User user;
    private UserAdditional userAdditional;
    private  String roles;

}
