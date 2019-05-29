package com.example.hemin.fnb.ui.model;

import android.content.Context;
import android.widget.Toast;

import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.MessageContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import java.util.Map;

import io.reactivex.Flowable;

public class MessageModle implements MessageContract.modle1,MessageContract.modle2,MessageContract.modle3 {

    @Override
    public Flowable<BaseObjectBean> getMaga(Context context, Map token, long current, long size, String type) {
        return RetrofitClient.getInstance().getApi().getMaga(token,current,size,type);
    }

    @Override
    public Flowable<BaseObjectBean> getTuiJian(Context context, Map token, long current, long size, int userId) {
        return RetrofitClient.getInstance().getApi().getTuiJian(token,current,size,userId);
    }

    @Override
    public Flowable<BaseObjectBean> getGuanZhu(Context context, Map token, long current, long size, int userId) {
        return RetrofitClient.getInstance().getApi().getGuanzhu(token,current,size,userId);
    }
}
