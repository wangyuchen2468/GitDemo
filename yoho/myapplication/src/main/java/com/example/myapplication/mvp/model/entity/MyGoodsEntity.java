package com.example.myapplication.mvp.model.entity;

import java.util.ArrayList;

/**
 * @author ：created by
 * Create Date ：2019/12/11 09
 * Package_Name : yoho
 */
public class MyGoodsEntity {
    private String categoryId;
    private String name;
    private String price;
    private String urlPath;
    private ArrayList<String> urlDesCribePaths;

    public MyGoodsEntity() {
    }

    public MyGoodsEntity(String categoryId, String name, String price) {
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = urlPath;
    }

    public ArrayList<String> getUrlDesCribePaths() {
        return urlDesCribePaths;
    }

    public void setUrlDesCribePaths(ArrayList<String> urlDesCribePaths) {
        this.urlDesCribePaths = urlDesCribePaths;
    }

    @Override
    public String toString() {
        return "{" +
                "categoryId='" + categoryId + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", urlPath='" + urlPath + '\'' +
                ", urlDesCribePaths=" + urlDesCribePaths +
                '}';
    }
}
