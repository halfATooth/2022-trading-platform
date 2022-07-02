package com.example.summervacationproject.service.Impl;

import com.example.summervacationproject.bean.Item;
import com.example.summervacationproject.controller.UploadController;
import com.example.summervacationproject.mapper.PublishItemMapper;
import com.example.summervacationproject.mapper.UserMapper;
import com.example.summervacationproject.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
@Service
public class PublishServiceImpl implements PublishService {
    @Autowired
    PublishItemMapper publishItemMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public Map<String, String> uploadImg(MultipartFile[] files, String username, String type) {
        Map<String, String> res = new HashMap<>();
        String baseFileName = generateFileName(username);
        for(int i=0;i< files.length;i++){
            //获取文件名
            String fileName = files[i].getOriginalFilename();
            if(fileName == null){
                res.put("code","3");
                res.put("msg","图片为空");
                return res;
            }
            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            //重新生成文件名
            fileName = baseFileName + "-" + i + suffixName;
            //指定本地文件夹存储图片，写到需要保存的目录下
            String tail;
            if("head".equals(type)) tail = "headImg/";
            else if("other".equals(type)) tail = "otherImg/";
            else{
                res.put("code","2");
                res.put("msg","图片上传类型不明确，头像or物品");
                return res;
            }
            String s = UploadController.class.getResource("/") + tail;
            String filePath = s.substring(6);
            try {
                //将图片保存到文件夹里
                files[i].transferTo(new File(filePath + fileName));
                res.put("url",filePath + fileName);
            }catch (Exception e){
                e.printStackTrace();
                res.put("code","1");
                res.put("msg","图片上传失败 ");
                return res;
            }
        }
        //节点数加1
        userMapper.setCount(userMapper.getCount(username)+1,username);
        //返回提示信息
        res.put("code","0");
        res.put("msg","图片上传成功 ");
        return res;
    }

    @Override
    public Map<String, String> publishItem(String category, String brand, Double price, String property,
                                           Double quality, Boolean isOut, String publisher, String payer,
                                           Double amount, Integer imgNum, MultipartFile[] img) {

        Map<String, String> response = uploadImg(img, publisher, "other");
        if(!response.get("code").equals("0"))
            return response;
        else {
            Map<String, String> res = new HashMap<>();
            String url = response.get("url");
            Item item = new Item(category,brand,price,property,quality,isOut,publisher,payer,amount,imgNum,url);
            try{
                publishItemMapper.addItem(item);
                res.put("code","0");
                res.put("msg","商品发布成功");
                return res;
            }catch (Exception e){
                res.put("code","1");
                res.put("msg","商品发布失败");
                res.put("error",String.valueOf(e));
                return res;
            }
        }
    }

    private String generateFileName(String publisher){
        String user_part = "" + userMapper.getIdByName(publisher);
        String time_part = "" + System.currentTimeMillis();
        String node_part = "" +userMapper.getCount(publisher);
        return user_part+"-"+time_part+"-"+node_part;
    }
}
