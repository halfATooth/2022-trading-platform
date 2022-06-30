package com.example.summervacationproject.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
public class UploadController {

    @PostMapping("/upload")
    public String upload(MultipartFile file, String username){

        //获取文件名
        String fileName = file.getOriginalFilename();
        //获取文件后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //重新生成文件名
        fileName = username + suffixName;
        //指定本地文件夹存储图片，写到需要保存的目录下
        String s = UploadController.class.getResource("/") + "headImg/";
        String filePath = s.substring(6);
        try {
            //将图片保存到static文件夹里
            file.transferTo(new File(filePath + fileName));
            //返回提示信息
            return "上传成功 ";
        } catch (Exception e) {
            e.printStackTrace();
            return "上传失败";
        }
    }
}
