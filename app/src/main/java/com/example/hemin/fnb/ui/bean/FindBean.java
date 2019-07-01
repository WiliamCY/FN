package com.example.hemin.fnb.ui.bean;

import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.List;

public class FindBean {
    private String imageUrl;
    private String pathUrl;
    private int status,isPHB;
    private String GiveNum,CollectionNum,WantNum;
    private ArrayList<String> dailyList = new ArrayList<>();


    public FindBean() {

    }

    public FindBean(String url,String pathUrl, int status,int isPHB) {
        this.imageUrl = url;
        this.status = status;
        this.pathUrl = pathUrl;
        this.isPHB = isPHB;
    }
    public FindBean(String url,String pathUrl, int status,String GiveNum,String CollectionNum,String WantNum,ArrayList<String> dailyList ) {
        this.imageUrl = url;
        this.status = status;
        this.pathUrl = pathUrl;
        this.GiveNum = GiveNum;
        this.CollectionNum = CollectionNum;
        this.WantNum = WantNum;
        this.dailyList = dailyList;
    }
    public FindBean(String url,String pathUrl, int status,String GiveNum,String CollectionNum,String WantNum,int isPHB) {
        this.imageUrl = url;
        this.status = status;
        this.pathUrl = pathUrl;
        this.GiveNum = GiveNum;
        this.CollectionNum = CollectionNum;
        this.WantNum = WantNum;
        this.isPHB = isPHB;
    }

    public FindBean(String url,String pathUrl, int status,String GiveNum,String WantNum,int isPHB) {
        this.imageUrl = url;
        this.status = status;
        this.pathUrl = pathUrl;
        this.GiveNum = GiveNum;
        this.WantNum = WantNum;
        this.isPHB = isPHB;
    }


    public int getIsPHB() {
        return isPHB;
    }

    public void setIsPHB(int isPHB) {
        this.isPHB = isPHB;
    }

    public int getStatus() {
        return status;
    }



    public String getPathUrl() {
        return pathUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPathUrl(String pathUrl) {
        this.pathUrl = pathUrl;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getGiveNum() {
        return GiveNum;
    }

    public String getWantNum() {
        return WantNum;
    }

    public String getCollectionNum() {
        return CollectionNum;
    }

    public void setWantNum(String wantNum) {
        WantNum = wantNum;
    }

    public void setGiveNum(String giveNum) {
        GiveNum = giveNum;
    }

    public void setCollectionNum(String collectionNum) {
        CollectionNum = collectionNum;
    }

    public ArrayList<String> getDailyList() {
        return dailyList;
    }

    public void setDailyList(ArrayList<String> dailyList) {
        this.dailyList = dailyList;
    }
}