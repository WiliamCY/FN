package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.CollectionContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import java.util.Collections;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

public class CollectionModel implements CollectionContract.Model {

    @Override
    public Flowable<BaseObjectBean> getColldetionMessage(Context context, Map map, String id) {
        return RetrofitClient.getInstance().getApi().getColldetionMessage(map,id);
    }
}
