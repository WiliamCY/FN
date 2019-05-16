package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.ForgetContract;
import com.example.hemin.fnb.ui.contract.LoginContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

public class ForgetModel implements ForgetContract.GetCodeModel,ForgetContract.Model{

    @Override
    public Flowable<BaseObjectBean> forget(Context context, RequestBody body) {
        return RetrofitClient.getInstance().getApi().forget(body);
    }

    @Override
    public Flowable<BaseObjectBean> getCodes(String mobile) {
        return RetrofitClient.getInstance().getApi().getCode(mobile);
    }



}
