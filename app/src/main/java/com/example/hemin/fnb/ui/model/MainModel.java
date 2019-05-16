package com.example.hemin.fnb.ui.model;



import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.LoginBean;
import com.example.hemin.fnb.ui.contract.MainContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import io.reactivex.Flowable;


public class MainModel implements MainContract.Model {
    @Override
    public Flowable<BaseObjectBean<LoginBean>> login(String username, String password) {
        return RetrofitClient.getInstance().getApi().logins(username,password);
    }
}
