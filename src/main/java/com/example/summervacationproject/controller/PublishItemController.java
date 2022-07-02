package com.example.summervacationproject.controller;

import com.example.summervacationproject.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@CrossOrigin(origins = "*")
@Controller
public class PublishItemController {
    @Autowired
    PublishService publishService;

    @ResponseBody
    @PostMapping("/publishItem")
    Map<String,String> publishItem(String category,String brand,Double price,String property,Double quality,
                                   Boolean isOut,String publisher,String payer,Double amount,Integer imgNum,
                                   MultipartFile[] img){
        return publishService.publishItem(category,brand,price,property,quality,isOut,publisher,
                payer,amount,imgNum,img);
    }
}
