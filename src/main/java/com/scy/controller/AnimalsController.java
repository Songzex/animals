package com.scy.controller;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.scy.config.JwtUtil;
import com.scy.config.ThreadLocalToken;
import com.scy.from.animals.Fromanimalsmeassage;
import com.scy.from.article.ArticleTags;
import com.scy.mapper.AdoptMapper;
import com.scy.mapper.AnimalMapper;
import com.scy.mapper.AnimalsFostercareMapper;
import com.scy.pojo.Adopt;
import com.scy.pojo.Animal;
import com.scy.pojo.AnimalsFostercare;
import com.scy.pojo.Article;
import com.scy.resoult.Resoult;
import com.scy.service.AdoptService;
import com.scy.service.AnimalService;
import com.scy.service.ArticleService;
import com.scy.utils.QiniuKodoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    ArticleService articleService;

    @Autowired
    AdoptService adoptService;
    @Autowired
    AdoptMapper adoptMapper;

    @Autowired
    AnimalsFostercareMapper fostercareService;
    @Autowired
    AnimalsFostercareMapper animalsFostercareMapper;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 救助动物接口
     * @param file
     * @param fromanimals
     * @return
     */
    @PostMapping("add/")
    public Resoult add(@RequestParam("file") MultipartFile file, Fromanimalsmeassage fromanimals) {
        String token = threadLocalToken.getToken();
        String email = JwtUtil.getUsername(token);
        System.out.println(fromanimals.getDate());
        Animal animal = new Animal();
        animal.setAnimalSex(fromanimals.getSex());
        animal.setAnimalShadow(fromanimals.getShadow());
        animal.setAnimalsHeathly(fromanimals.getHelathly());
        animal.setAnimalsNotes(fromanimals.getNotes());
        animal.setAnnimalDate( fromanimals.getDate());
        animal.setAnimalsUemail(email);
        animal.setAnimalsWhere(fromanimals.getAnimalsWhere());
        animal.setAnimalsType(fromanimals.getAnimalsType());
        String fileUrl;
        try {
            // 保存文件到本地临时目录
            File localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);
            // 上传到七牛云并获取外链
            qiniuKodoUtil.upload(localFile.getAbsolutePath());
            fileUrl = qiniuKodoUtil.getFileUrl(localFile.getName());
            animal.setAnimalsPhoto(fileUrl);
            animal.setAnimalShadow(0);
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

    /**
     * 领养申请api
     * @param
     * @param fromanimals
     * @return
     */
    @PostMapping("addAdopt/")
    public Resoult Adopt(@RequestBody Animal fromanimals) {
        String token = threadLocalToken.getToken();
        String email = JwtUtil.getUsername(token);
        List<Adopt> list = adoptMapper.selectList(new LambdaQueryWrapper<Adopt>().eq(Adopt::getUserid, email).eq(Adopt::getAnimalId, fromanimals.getAnimalId()));
        if(!list.isEmpty()){
            return new Resoult(200,"已经被申请,不能重复申请",null);

        }
        Adopt adopt = BeanUtil.toBean(fromanimals, Adopt.class);
       adopt.setAdoptContext(fromanimals.getAnimalName());
       adopt.setUserid(email);
       adopt.setAdminId(0);
        int insert = adoptMapper.insert(adopt);
        return new Resoult(200, "已经申请", null);
    }
    /**
     * 领养api审核 1
     * @param
     * @param fromanimals
     * @return
     */
    @PostMapping("adminAdopt9/")
    public Resoult adminAdopt( @RequestBody LinyangDto article) {
        String token = threadLocalToken.getToken();
        String email = JwtUtil.getUsername(token);
        Adopt adopt = BeanUtil.toBean(article, Adopt.class);
        adopt.setAdminId(1);
        //&#x88AB;&#x9886;&#x517B;
        Animal animal = animalMapper.selectOne(new LambdaQueryWrapper<Animal>().eq(Animal::getAnimalId, article.getAnimalsId()));
        animal.setAnimalShadow(4);
        animalMapper.updateById(animal);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("3328397500@qq.com");//发送者
        message.setTo(article.getUsernmail());//接收者
        message.setSubject("\uD83C\uDF3C\uD83C\uDF3B\uD83C\uDF3B\uD83C\uDF3B\uD83D\uDE01\uD83D\uDE01流浪动物管理栈-----你的领养申请已经通过");//主题设置
        message.setText("\uD83C\uDF3B\uD83C\uDF3B\uD83C\uDF3B\uD83C\uDF40\uD83C\uDF40你好啊"+article.getUsernmail()+"你领养的动物已经被审核成功\uD83D\uDE01\uD83D\uDE01"+"动物编号为"
                +article.getAnimalsId()+"请到基地领养"+"关爱动物的是每一个社会人的责任和业务你真棒\uD83C\uDF89\uD83C\uDF89\uD83C\uDF89\uD83C\uDF81");//内容
        javaMailSender.send(message);
       //0 未审核 1 审核通过  3 审核不通过 2 默认值
        int insert = adoptMapper.updateById(adopt);
        return new Resoult(200, "通过审核", null);
    }

    @PostMapping("adminAdopt3/")
    public Resoult adminAdopt3( @RequestBody LinyangDto article) {
        String token = threadLocalToken.getToken();
        Adopt adopt = BeanUtil.toBean(article, Adopt.class);
        adopt.setAdminId(3);
        //0 未审核 1 审核通过  3 审核不通过 2 默认值
        int insert = adoptMapper.updateById(adopt);
        return new Resoult(200, "通过审核", null);
    }

    /**
     * 我领养的动物
     * @return
     */
    @PostMapping("meAdopt/")
    public Resoult meAdopt() {
        String token = threadLocalToken.getToken();
        String email = JwtUtil.getUsername(token);
        List<Adopt> meadoptList = adoptMapper.selectList(new QueryWrapper<Adopt>().eq("animals_uemail", email));
        return new Resoult(200, meadoptList, null);
    }

    /**
     * 寄养动物 申请
     * @param file
     * @param fromanimals
     * @return
     */
    @PostMapping("fostercare/")
    public Resoult fostercare(@RequestParam("file") MultipartFile file,  AnimalsFostercare fromanimals) throws IOException {
        String token = threadLocalToken.getToken();
        String email = JwtUtil.getUsername(token);
        AnimalsFostercare bean = BeanUtil.toBean(fromanimals, AnimalsFostercare.class);
        File localFile = File.createTempFile("temp", null);
        file.transferTo(localFile);
        // 上传到七牛云并获取外链
        qiniuKodoUtil.upload(localFile.getAbsolutePath());
        String fileUrl = qiniuKodoUtil.getFileUrl(localFile.getName());
        bean.setForstphoto(fileUrl);
        bean.setUseremail(email);
        bean.setForstshadw(0);//不通过
        bean.setForsttype("自费寄养");
        fostercareService.insert(bean);
        return new Resoult(200, "已经申请l", null);
    }
    /**
     * 我的寄养申请
     * @param
     * @return
     */
    @PostMapping("mefostercare/")
    public Resoult mefostercare() throws IOException {
        String token = threadLocalToken.getToken();
        String email = JwtUtil.getUsername(token);
        List<AnimalsFostercare> list = animalsFostercareMapper.selectList(new QueryWrapper<AnimalsFostercare>().eq("useremail", email));
        return new Resoult(200, list, null);
    }
    /**
     * 寄养通过操作
     * @param
     * @return
     */
    @PostMapping("adminfostercare/")
    public Resoult adminfostercare(@RequestBody AnimalsFostercare animalsFostercare) throws IOException {
        String token = threadLocalToken.getToken();
        String email = JwtUtil.getUsername(token);
        animalsFostercare.setForstshadw(1);
        fostercareService.updateById(animalsFostercare);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("3328397500@qq.com");//发送者
        message.setTo(animalsFostercare.getUseremail());//接收者
        message.setSubject("\uD83C\uDF3C\uD83C\uDF3B\uD83C\uDF3B\uD83C\uDF3B\uD83D\uDE01\uD83D\uDE01流浪动物管理栈-----你的申请已经通过");//主题设置
        message.setText("\uD83C\uDF3B\uD83C\uDF3B\uD83C\uDF3B\uD83C\uDF40\uD83C\uDF40你好啊"+animalsFostercare.getUseremail()+"你寄养的动物已经被审核成功\uD83D\uDE01\uD83D\uDE01"+"动物编号为"
                +animalsFostercare.getFosterid()+"请自费寄养到基地");//内容
        javaMailSender.send(message);
        return new Resoult(200, "完成", null);
    }
    /**
     * 寄养不通过操作
     * @param
     * @return
     */
    @PostMapping("adminbufostercare/")
    public Resoult adminbufostercare(@RequestBody AnimalsFostercare animalsFostercare) throws IOException {
        String token = threadLocalToken.getToken();
        String email = JwtUtil.getUsername(token);
        animalsFostercare.setForstshadw(3);//审核不通过 0未审核
        int id = fostercareService.updateById(animalsFostercare);
        return new Resoult(200, "完成", null);
    }
    /**
     * 用户所关联的动物我的动物信息+领养信息
     * @return
     */
    @PostMapping("find/")
    public Resoult find() {
        String token = threadLocalToken.getToken();
        String email = JwtUtil.getUsername(token);
        List<Animal> list = animalService.findalls(email);
        List<Integer> list1 = list.stream().map(animal -> animal.getAnimalId()).collect(Collectors.toList());
        if(list1.size() == 0){
            return null;
        }
        List<LinyangDto> dtos = new ArrayList<>();
        List<Adopt> adoptList = adoptMapper.selectList(new QueryWrapper<Adopt>().in("userid", email));
        for(Adopt one:adoptList){
            String userid = one.getUserid();
//            for (Integer ll:list1){
//            Animal animal = animalMapper.selectOne(new QueryWrapper<Animal>().eq("animal_id", ll));
            Animal animal = animalMapper.selectOne(new QueryWrapper<Animal>().eq("animal_id", one.getAnimalId()));
            if(animal==null){
                return null;
            }
            LinyangDto linyangDto = new LinyangDto();
            linyangDto.setAnimalName(animal.getAnimalName());
            linyangDto.setAnimalsPhoto(animal.getAnimalsPhoto());
            linyangDto.setAnimalsHeathly(animal.getAnimalsHeathly());
            linyangDto.setAnnimalDate(animal.getAnnimalDate());
            linyangDto.setUsernmail(userid);
            linyangDto.setAdoptId(one.getAdoptId());
            linyangDto.setAnimalsId(one.getAnimalId());
            linyangDto.setAdminid(one.getAdminId());
            linyangDto.setAnimalsId(animal.getAnimalId());

            dtos.add(linyangDto);

        }
        List<AnimalsFostercare> list2 = animalsFostercareMapper.selectList(new LambdaQueryWrapper<AnimalsFostercare>().eq(AnimalsFostercare::getUseremail, email));
        Heji heji = new Heji();
        heji.setList(list);
        heji.setList1(dtos);
        heji.setList3(list2);
        return new Resoult(200, heji, null);

    }

    /**
     *根据标签查找全查救助动物
     * @return
     */
    @PostMapping("findanimals/")
    public Resoult findfindanimals(@RequestBody AnimalTags  animalTags) {
        List<Animal> list = animalService.selectbytags(animalTags);
        System.out.println(list.toString());
        return new Resoult(200, list, null);

    }

    /**
     * 救助动物的审核通过操作
     * @return
     */
    @PostMapping("findall1/")
    public Resoult findall1(@RequestBody Animal animal) {
        animal.setAnimalShadow(1);
        animalService.updates(animal);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("3328397500@qq.com");//发送者
        message.setTo(animal.getAnimalsUemail());//接收者
        message.setSubject("\uD83C\uDF3C\uD83C\uDF3B\uD83C\uDF3B\uD83C\uDF3B\uD83D\uDE01\uD83D\uDE01流浪动物管理栈-----你的申请已经通过");//主题设置
        message.setText("\uD83C\uDF3B\uD83C\uDF3B\uD83C\uDF3B\uD83C\uDF40\uD83C\uDF40你好啊"+animal.getAnimalsUemail()+"你救助的动物已经被审核成功\uD83D\uDE01\uD83D\uDE01"+"动物编号为"+animal.getAnimalId());//内容
        javaMailSender.send(message);
        return new Resoult(200, "", null);

    }    /**
     * 救助动物的审核不通过操作
     * @return
     */
    @PostMapping("notong/")
    public Resoult findall8(@RequestBody Animal animal) {
        animal.setAnimalShadow(3);//3weitonguuo
        animalService.updates(animal);
        return new Resoult(200, "", null);

    }

    /**
     *  寄养动物的全查
     * @return
     */
    @PostMapping("findall2/")
    public Resoult findall2() {
        List<AnimalsFostercare> list = fostercareService.selectList(new QueryWrapper<AnimalsFostercare>().eq("forst_shadw", 0));
        return new Resoult(200, list, null);

    }
   /**
     *  领养动物的全查
     * @return
     */
    @PostMapping("findall3/")
    public Resoult findall3() {
        List<Animal> all = animalService.findAll();
        return new Resoult(200, all, null);

    }

    /**
     * 推荐全查
     * @return
     */
    @PostMapping("findalltui/")
    public Resoult findalltui() {
        List<Animal> all = animalService.findAlltui();
        return new Resoult(200, all, null);

    }   /**
     *  领养动物的全查
     * @return
     */
    @PostMapping("findall4/")
    public Resoult findall4() {
        List<Adopt> list = adoptMapper.selectList(new QueryWrapper<Adopt>().eq("admin_id", 0));
        List<LinyangDto> dtos = new ArrayList<>();
        List<Integer> list1 = list.stream().map(Adopt::getAnimalId).collect(Collectors.toList());
        for(Adopt one:list){
            String userid = one.getUserid();
//            for (Integer ll:list1){
//            Animal animal = animalMapper.selectOne(new QueryWrapper<Animal>().eq("animal_id", ll));
            Animal animal = animalMapper.selectOne(new QueryWrapper<Animal>().eq("animal_id", one.getAnimalId()));
            if(animal==null){
                return null;
            }
            LinyangDto linyangDto = new LinyangDto();
            linyangDto.setAnimalName(animal.getAnimalName());
            linyangDto.setAnimalsPhoto(animal.getAnimalsPhoto());
            linyangDto.setAnimalsHeathly(animal.getAnimalsHeathly());
            linyangDto.setAnnimalDate(animal.getAnnimalDate());
            linyangDto.setUsernmail(userid);
            linyangDto.setAdoptId(one.getAdoptId());
            linyangDto.setAnimalsId(one.getAnimalId());
            dtos.add(linyangDto);

    }
        return new Resoult(200, dtos, null);}

    /**
     * 加星标动物 轮播图
     * @param articleTags
     * @return
     */
    @PostMapping("findallxin/")
    public Resoult findallxin(@RequestBody ArticleTags articleTags) {
        List<Animal> animals = animalService.selectallxin(articleTags);
        return new Resoult(200, animals, null);
    }

    /**
     * 流浪动物 健康修改
     * @param articleTags
     * @return
     */
    @PostMapping("updatedw/")
    public Resoult updatedw(@RequestBody Animal articleTags) {
         animalMapper.updateById(articleTags);
        return new Resoult(200, "animals", null);
    }

    /**
     * 寄养动物健康修改
     * @param articleTags
     * @return
     */
    @PostMapping("updatejy/")
    public Resoult updatejy(@RequestBody AnimalsFostercare AnimalsFostercare) {
        fostercareService.updateById(AnimalsFostercare);
        return new Resoult(200, "animals", null);
    }

    /**
     * 推荐的文章
     * @param articleTags
     * @return
     */
    @PostMapping("selelctxin/")
    public Resoult selelctxin(@RequestBody Article articleTags) {
        List<Article> list = articleService.selectarticlexin(articleTags);
        return new Resoult(200, list, null);
    }

    /**
     * 领养
     * @param id
     * @return
     */
    @DeleteMapping("/lingyang/{id}")
    public Resoult deletedByIdlingyang(@PathVariable Integer id) {
        adoptMapper.deleteById(id);
        return new Resoult(200, null, null);
    }

    /**
     * 救助
     * @param id
     * @return
     */
    @DeleteMapping("/jiuzhu/{id}")
    public Resoult deletedByIdjiuzhu(@PathVariable Integer id) {
        animalMapper.deleteById(id);
        return new Resoult(200, null, null);
    }

    /**
     *寄养
     * @param id
     * @return
     */
    @DeleteMapping("/jiyang/{id}")
    public Resoult deletedByIdjiyang(@PathVariable Integer id) {
        animalsFostercareMapper.deleteById(id);
        return new Resoult(200, null, null);
    }


}