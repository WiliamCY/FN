package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.UpdateAboutContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

public class UpdateAboutModle implements UpdateAboutContract.modle {

    @Override
    public Flowable<BaseObjectBean> updateAbout( Map map, RequestBody body) {
        return RetrofitClient.getInstance().getApi().UpdateAbout(map,body);
    }
}
