package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.bean.AppraisaBean;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.AppraisaContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

public class AppraisaModel implements AppraisaContract.modle,AppraisaContract.modles {

    @Override
    public Flowable<BaseObjectBean<List<AppraisaBean.DataBean>>> Appraisa( Map token, long current, long size, long collectionAudit, long userId) {
        return RetrofitClient.getInstance().getApi().appraisa(token,current,size,collectionAudit,userId);
    }


    @Override
    public Flowable<BaseObjectBean<List<AppraisaBean.DataBean>>> Appraisas(Context context, Map token, long current, long size, long collectionAudit, long userId) {
        return RetrofitClient.getInstance().getApi().appraisa(token,current,size,collectionAudit,userId);
    }
}
