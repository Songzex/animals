package com.scy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.scy.config.JwtUtil;
import com.scy.config.ThreadLocalToken;
import com.scy.from.animals.Fromanimalsmeassage;
import com.scy.mapper.AnimalMapper;
import com.scy.pojo.Animal;
import com.scy.resoult.Resoult;
import com.scy.service.AdminService;
import com.scy.service.AnimalService;
import com.scy.utils.QiniuKodoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("animals/")
public class AnimalsController {

    @Autowired
    AnimalService animalService;
    @Autowired
    QiniuKodoUtil qiniuKodoUtil;
    @Autowired
    ThreadLocalToken threadLocalToken;
    @Autowired
    AnimalMapper animalMapper;

    @PostMapping("add/")
    public Resoult add(@RequestParam("file") MultipartFile file, Fromanimalsmeassage fromanimals) {
        String token = threadLocalToken.getToken();
        String email = JwtUtil.getUsername(token);
        System.out.println(fromanimals.getDate().toString());
        Animal animal = new Animal();
        animal.setAnimal_sex(fromanimals.getSex());
        animal.setAnimal_shadow(fromanimals.getShadow());
        animal.setAnimals_heathly(fromanimals.getHelathly());
        animal.setAnimals_notes(fromanimals.getNotes());
        animal.setAnnimal_date(fromanimals.getDate().toString());
        animal.setAnimals_uemail(email);
        String fileUrl;
        try {
            // 保存文件到本地临时目录
            File localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            // 上传到七牛云并获取外链
            qiniuKodoUtil.upload(localFile.getAbsolutePath());
            fileUrl = qiniuKodoUtil.getFileUrl(localFile.getName());
            animal.setAnimals_photo(fileUrl);
            // 删除临时文件
            localFile.delete();
            int insert = animalMapper.insert(animal);
            System.out.println(1);
        } catch (IOException e) {
            return new Resoult(400, null, null);
        } catch (Exception e) {
            return new Resoult(400, null, null);
        }
        return new Resoult(200, "上传成功", null);
    }

    @PostMapping("find/")
    public Resoult find() {
        String token = threadLocalToken.getToken();
        String email = JwtUtil.getUsername(token);
        List<Animal> list = animalService.findall(email);
        System.out.println(list.toArray());
        return new Resoult(200, list, null);

    }
}