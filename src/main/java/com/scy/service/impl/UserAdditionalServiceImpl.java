package com.scy.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.pojo.UserAdditional;
import com.scy.service.UserAdditionalService;
import com.scy.mapper.UserAdditionalMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author 24022
* @description 针对表【user_additional】的数据库操作Service实现
* @createDate 2024-03-23 22:39:34
*/
@Service
public class UserAdditionalServiceImpl extends ServiceImpl<UserAdditionalMapper, UserAdditional>
    implements UserAdditionalService{

    @Autowired
    public UserAdditionalMapper userAdditionalMapper;
     /**
      * 修改
      * **/
    public  void updates(UserAdditional userAdditional){
        LambdaQueryWrapper<UserAdditional> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserAdditional::getUserid,userAdditional.getUserid());
        userAdditionalMapper.update(userAdditional,wrapper);
    }

    /**
     * 添加
     * **/
    public  void add(UserAdditional userAdditional){
        userAdditionalMapper.insert(userAdditional);
    }

    /**
     * 搜素
     * @param userid
     * @return
     */
    public  UserAdditional select( Integer id){
        LambdaQueryWrapper<UserAdditional> Wrapper = new LambdaQueryWrapper<>();
         Wrapper.eq(UserAdditional::getUserid,id);
        return userAdditionalMapper.selectOne(Wrapper);
    }

    /**
     * 删除
     * @param userid
     */
    public void delets (Integer userid){

        int i = userAdditionalMapper.deleteById(userid);

    }
}




