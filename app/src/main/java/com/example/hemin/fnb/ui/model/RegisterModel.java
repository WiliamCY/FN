package com.example.hemin.fnb.ui.model;



import android.content.Context;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.RegisterContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import io.reactivex.Flowable;
import okhttp3.RequestBody;


public class RegisterModel implements RegisterContract.GetCodeModel,RegisterContract.RegisterModle {
    @Override
    public Flowable<BaseObjectBean> getCodes(Context context,String mobile) {
        return RetrofitClient.getInstance().getApi().getCode(mobile);
    }

    @Override
    public Flowable<BaseObjectBean> register(Context context,RequestBody body) {
        return RetrofitClient.getInstance().getApi().register(body);
    }
}
