package com.example.hemin.fnb.ui.bean;

import java.util.List;

public class FindBean {
    private List<String> image;
    private int status;
  public FindBean(List<String> url,int status){
      this.image = url;
      this.status = status;
  }
    public int getStatus() {
        return status;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
