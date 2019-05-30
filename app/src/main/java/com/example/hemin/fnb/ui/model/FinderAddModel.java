package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.ImageUrlBean;
import com.example.hemin.fnb.ui.contract.MessageFinderAddContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FinderAddModel implements MessageFinderAddContract.modle1 ,MessageFinderAddContract.Mode2{
    @Override
    public Flowable<BaseObjectBean> friendAdd(Context context, Map token, RequestBody body) {
        return RetrofitClient.getInstance().getApi().friendAdd(token,body);
    }

    @Override
    public Flowable<BaseObjectBean<ImageUrlBean.DataBean>> postImage(Context context, Map token, MultipartBody.Part partList) {
        return RetrofitClient.getInstance().getApi().postImage(token,partList);
    }
}