package pojo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import pojo.domain.Animal;
import pojo.service.AnimalService;
import pojo.mapper.AnimalMapper;
import org.springframework.stereotype.Service;

/**
* @author 24022
* @description 针对表【animal】的数据库操作Service实现
* @createDate 2024-03-17 01:43:08
*/
@Service
public class AnimalServiceImpl extends ServiceImpl<AnimalMapper, Animal>
    implements AnimalService{

}




