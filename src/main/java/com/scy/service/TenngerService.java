package com.scy.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scy.pojo.Tennger;

import java.util.List;

/**
* @author 24022
* @description 针对表【tennger】的数据库操作Service
* @createDate 2024-05-09 19:59:10
*/
public interface TenngerService extends IService<Tennger> {
    /**
     * 添加志愿者
     * @param tennger
     */
    void selectAll(Tennger tennger);

    void delete(String uid);

    List<Tennger> selectlist(Tennger tennger);
}
