package com.example.hemin.fnb.ui.bean;

import java.util.List;

import io.reactivex.annotations.NonNull;

public class ZZSItem {
    public  final @NonNull
    List<MessageBean1.DataBean.RecordsBean> bean;


    public ZZSItem( List<MessageBean1.DataBean.RecordsBean> bean) {
        this.bean = bean;
    }
}
