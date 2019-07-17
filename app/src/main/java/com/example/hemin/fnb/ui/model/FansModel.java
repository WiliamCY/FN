package com.example.hemin.fnb.ui.model;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.FanContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import java.util.Map;

import io.reactivex.Flowable;

public class FansModel implements FanContract.modle,FanContract.modle2{
    @Override
    public Flowable<BaseObjectBean> GZ(Map token,int current, int size, int userId) {
        return RetrofitClient.getInstance().getApi().myGuanzhu(token,current,size,userId);
    }

    @Override
    public Flowable<BaseObjectBean> fs(Map token,int current, int size) {
        return RetrofitClient.getInstance().getApi().myFans(token,current,size);
    }
}
