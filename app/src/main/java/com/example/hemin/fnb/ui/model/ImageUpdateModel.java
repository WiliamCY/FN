package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.GetTypeContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;

public class ImageUpdateModel implements GetTypeContract.Model {
    @Override
    public Flowable<BaseObjectBean> postImage(Context context, Map token, MultipartBody.Part partList) {
        return RetrofitClient.getInstance().getApi().postImage(token,partList);
    }
}
