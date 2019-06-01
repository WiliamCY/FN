package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.FoucesContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import java.util.Map;

import io.reactivex.Flowable;

public class FoucesModle implements FoucesContract.modle,FoucesContract.modle2 {

    @Override
    public Flowable<BaseObjectBean> Fouces(Context context, Map token, long finder, long userId) {
        return  RetrofitClient.getInstance().getApi().getFoucus(token,finder,userId);
    }

    @Override
    public Flowable<BaseObjectBean> getZan(Context context, Map token, long finder, long userId, long type) {
        return RetrofitClient.getInstance().getApi().getZan(token,finder,userId,type);
    }
}
