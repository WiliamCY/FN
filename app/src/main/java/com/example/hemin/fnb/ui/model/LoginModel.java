package com.example.hemin.fnb.ui.model;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import io.reactivex.Flowable;

public class LoginModel {
    public  Flowable<BaseObjectBean> login(String mobile,String code){
        return RetrofitClient.getInstance().getApi().login(mobile,code);
    }
}
