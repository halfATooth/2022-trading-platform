package com.example.summervacationproject.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface PublishService {
    Map<String, String> uploadImg(MultipartFile[] file, String username, String type);
    Map<String, String> publishItem(String category,String brand,Double price,String property,Double quality,
                                    Boolean isOut,String publisher,String payer,Double amount,Integer imgNum,
                                    MultipartFile[] img);
}