package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class FindBean {
    private String imageUrl;
    private String pathUrl;
    private int status;

    public FindBean() {

    }

    public FindBean(String url,String pathUrl, int status) {
        this.imageUrl = url;
        this.status = status;
        this.pathUrl = pathUrl;
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

}