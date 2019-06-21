package com.example.hemin.fnb.ui.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.example.hemin.fnb.ui.util.Find;

public class MultipleItem implements MultiItemEntity {
    public static final  int One = 1;
    public static  final int Two = 2;
    public  static  final  int Three = 3;
    public  static  final  int Four = 4;
    private Find find;

    public MultipleItem(int itemType,Find find) {
        this.itemType = itemType;
        this.find = find;
    }
    public int itemType;
    @Override
    public int getItemType() {
        return itemType;
    }


    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public Find getFind() {
        return find;
    }

    public void setFind(Find find) {
        this.find = find;
    }

}
