package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.Find5Bean;
import com.example.hemin.fnb.ui.contract.PHBContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import java.util.Map;

import io.reactivex.Flowable;

public class PHBModel implements PHBContract.dianzan {
    @Override
    public Flowable<BaseObjectBean<Find5Bean.DataBean>> getRankingList(Context context, Map<String, String> heard, long current, long size) {
        return RetrofitClient.getInstance().getApi().getRankingList(heard,current,size);
    }
}
