package com.scy.service;

import com.scy.controller.WorkSs;
import com.scy.pojo.AnimalsFlow;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 24022
* @description 针对表【animals_flow】的数据库操作Service
* @createDate 2024-05-09 20:04:44
*/
public interface AnimalsFlowService extends IService<AnimalsFlow> {

    void add(WorkSs workSs);
}
