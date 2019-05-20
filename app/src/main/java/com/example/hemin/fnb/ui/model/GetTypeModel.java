package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.TypeBean;
import com.example.hemin.fnb.ui.contract.GetTypeContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;

public class GetTypeModel implements GetTypeContract.GetTypeModle{
    @Override
    public Flowable<BaseObjectBean<List<TypeBean.DataBean>>> getType(Context context, Map token) {
        return RetrofitClient.getInstance().getApi().getType(token);
    }


}
