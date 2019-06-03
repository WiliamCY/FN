package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.WodeQuanziContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import java.util.Map;

import io.reactivex.Flowable;

public class WoDeQuanZiModel implements WodeQuanziContract.Model,WodeQuanziContract.Mode2,WodeQuanziContract.modle4,WodeQuanziContract.modle5 {
    @Override
    public Flowable<BaseObjectBean> myGuanzhu(Context context, Map token, long current, long size, long userId) {
        return RetrofitClient.getInstance().getApi().myGuanzhu(token,current,size,userId);
    }

    @Override
    public Flowable<BaseObjectBean> myFaBu(Context context, Map token, long current, long size, long userId) {
        return RetrofitClient.getInstance().getApi().myFabu(token,current,size,userId);
    }

    @Override
    public Flowable<BaseObjectBean> getFidner(Context context, Map token, long friendId, int userId) {
        return RetrofitClient.getInstance().getApi().getFinder(token,friendId,userId);
    }

    @Override
    public Flowable<BaseObjectBean> Remove(Context context, Map token, String fuId) {
        return RetrofitClient.getInstance().getApi().Remove(token,fuId);
    }
}
