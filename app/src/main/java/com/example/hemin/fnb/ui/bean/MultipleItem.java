package com.example.hemin.fnb.ui.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class MultipleItem implements MultiItemEntity {
    public static final  int One = 1;
//    public static  final int Two = 2;
//    public  static  final  int Three = 3;
    private String url;
    private  int itemType;
    public MultipleItem(int itemType) {
        this.itemType = itemType;
    }
    @Override
    public int getItemType() {
        return itemType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
