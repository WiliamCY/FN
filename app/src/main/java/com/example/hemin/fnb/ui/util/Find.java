package com.example.hemin.fnb.ui.util;

public class Find {
    private String url;
    private String GiveNum;
    private String CollectionNum;
    private String WantNum;
    public Find(String url){
        this.url = url;
    }
    public  Find(String url,String GiveNum,String CollectionNum,String WantNum){
        this.url = url;
        this.GiveNum = GiveNum;
        this.CollectionNum = CollectionNum;
        this.WantNum = WantNum;
    }

    public String getUrl() {
        return url;
    }

    public String getCollectionNum() {
        return CollectionNum;
    }

    public String getGiveNum() {
        return GiveNum;
    }

    public String getWantNum() {
        return WantNum;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCollectionNum(String collectionNum) {
        CollectionNum = collectionNum;
    }

    public void setGiveNum(String giveNum) {
        GiveNum = giveNum;
    }

    public void setWantNum(String wantNum) {
        WantNum = wantNum;
    }
}
