package com.scy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scy.controller.WorkSs;
import com.scy.pojo.AnimalsFlow;
import com.scy.service.AnimalsFlowService;
import com.scy.mapper.AnimalsFlowMapper;
import org.springframework.stereotype.Service;

/**
* @author 24022
* @description 针对表【animals_flow】的数据库操作Service实现
* @createDate 2024-05-09 20:04:44
*/
@Service
public class AnimalsFlowServiceImpl extends ServiceImpl<AnimalsFlowMapper, AnimalsFlow>
    implements AnimalsFlowService{

    @Override
    public void add(WorkSs workSs) {

    }
}




