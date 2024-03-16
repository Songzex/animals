package com.scy.controller;

import com.scy.utils.QiniuKodoUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

//@RestController
//@RequestMapping("condominium/")
//@EnableWebMvc
@RestController
@CrossOrigin
@RequestMapping("condominium/")
public class QiniuKodoController {

    @Resource
    QiniuKodoUtil qiniuKodoUtil;


    @PostMapping("upload/")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }


        try {
            // 保存文件到本地临时目录
            File localFile = File.createTempFile("temp", null);
            file.transferTo(localFile);

            // 上传到七牛云并获取外链
//       String fileUrl = qiniuKodoUtil.upload(localFile.getAbsolutePath());
            qiniuKodoUtil.upload(localFile.getAbsolutePath());
            String fileUrl = qiniuKodoUtil.getFileUrl(localFile.getName());//文件外连
            // 删除临时文件
            localFile.delete();

            return ResponseEntity.ok(null);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("File upload failed: " + e.getMessage());
        }
    }
    @RequestMapping("listSpaceFiles/")
    public void listSpaceFiles() {
        qiniuKodoUtil.listSpaceFiles();
    }
    
    @RequestMapping("getFileUrl/")
    public void getFileUrl(String fileName) {
        try {
            qiniuKodoUtil.getFileUrl(fileName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("deleteFile1/")
    public void deleteFile1(String[] fileList) {
        qiniuKodoUtil.deleteFile(fileList);
    }
    @RequestMapping("deleteFile/")
    public void deleteFile() {
        qiniuKodoUtil
                .listSpaceFiles();
    }
}
