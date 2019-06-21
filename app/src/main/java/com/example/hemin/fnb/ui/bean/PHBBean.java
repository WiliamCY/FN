package com.example.hemin.fnb.ui.bean;

public class PHBBean {
    private String collectionId,giveNum,collectionNum,wantNum,imagesUrl,index;
    public  PHBBean(String collectionId,String giveNum,String collectionNum,String wantNum,String imagesUrl,String index){
        this.collectionId = collectionId;
        this.giveNum = giveNum;
        this.collectionNum = collectionNum;
        this.wantNum = wantNum;
        this.imagesUrl = imagesUrl;
        this.index = index;
    }

    public String getCollectionNum() {
        return collectionNum;
    }

    public String getWantNum() {
        return wantNum;
    }

    public String getGiveNum() {
        return giveNum;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public String getImagesUrl() {
        return imagesUrl;
    }

    public String getIndex() {
        return index;
    }

    public void setCollectionNum(String collectionNum) {
        this.collectionNum = collectionNum;
    }

    public void setGiveNum(String giveNum) {
        this.giveNum = giveNum;
    }

    public void setWantNum(String wantNum) {
        this.wantNum = wantNum;
    }

    public void setCollectionId(String collectionId) {
        this.collectionId = collectionId;
    }

    public void setImagesUrl(String imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public void setIndex(String index) {
        this.index = index;
    }

}
