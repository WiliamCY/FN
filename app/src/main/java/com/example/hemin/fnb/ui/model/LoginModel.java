package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.LoginContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

public class LoginModel implements LoginContract.Model,LoginContract.GetCodeModel{

    @Override
    public Flowable<BaseObjectBean> login(Context context, String mobile ,String code) {
        return RetrofitClient.getInstance().getApi().login(mobile,code);
    }

    @Override
    public Flowable<BaseObjectBean> getCodes(Context context,String mobile) {
        return RetrofitClient.getInstance().getApi().getCode(mobile);
    }
}
