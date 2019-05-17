package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.UserDateBean;
import com.example.hemin.fnb.ui.contract.PwLoginContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

public class PasswordModel implements PwLoginContract.Model {
    @Override
    public Flowable<BaseObjectBean> pwLogin(Context context, RequestBody body) {
        return RetrofitClient.getInstance().getApi().pWlogin(body);
    }
}
