package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.GetTypeContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

public class SubImageModel implements GetTypeContract.AddImageButton {
    @Override
    public Flowable<BaseObjectBean> submitImage(Context context, RequestBody body) {
        return RetrofitClient.getInstance().getApi().submitImage(body);
    }
}
