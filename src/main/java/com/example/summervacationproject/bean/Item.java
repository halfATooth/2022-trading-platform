package com.example.summervacationproject.bean;

import lombok.Data;

@Data
public class Item {
    private String category;
    private String brand;
    private Double price;
    private String property;
    private Double quality;
    private Boolean isOut;
    private String publisher;
    private String payer;
    private Double amount;
    private Integer imgNum;
    private String img;

    public Item(String category, String brand, Double price, String property, Double quality, Boolean isOut,
                String publisher, String payer, Double amount, Integer imgNum, String img) {
        this.category = category;
        this.brand = brand;
        this.price = price;
        this.property = property;
        this.quality = quality;
        this.isOut = isOut;
        this.publisher = publisher;
        this.payer = payer;
        this.amount = amount;
        this.imgNum = imgNum;
        this.img = img;
    }
}
