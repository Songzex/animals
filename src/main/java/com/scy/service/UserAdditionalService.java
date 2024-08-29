package com.scy.service;

import com.scy.pojo.UserAdditional;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

/**
* @author 24022
* @description 针对表【user_additional】的数据库操作Service
* @createDate 2024-03-23 22:39:34
*/
public interface UserAdditionalService extends IService<UserAdditional> {
    public  void updates(UserAdditional userAdditional);
    public  void add(UserAdditional userAdditional);
    public  UserAdditional select( Integer userid);
    public void delets (Integer userid);
}
