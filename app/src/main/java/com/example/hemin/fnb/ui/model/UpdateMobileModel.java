package com.example.hemin.fnb.ui.model;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.MobileRegisterContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

public class UpdateMobileModel implements MobileRegisterContract.mobile,MobileRegisterContract.mobile2 {
    @Override
    public Flowable<BaseObjectBean> getCode(String mobile) {
       return RetrofitClient.getInstance().getApi().getCode(mobile);
    }

    @Override
    public Flowable<BaseObjectBean> getUpdateMobile(Map map, RequestBody body) {
        return  RetrofitClient.getInstance().getApi().UpdateMobile(map,body);
    }
}
