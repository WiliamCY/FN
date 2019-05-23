package com.example.hemin.fnb.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.hemin.fnb.ui.activity.PasswordActivity;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.AppraisaBean;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.AppraisaContract;
import com.example.hemin.fnb.ui.model.AppraisaModel;
import com.example.hemin.fnb.ui.net.RxScheduler;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.functions.Consumer;

public class AppraisaPresenter extends BasePresenter<AppraisaContract.View> implements  AppraisaContract.Presenter {
    private AppraisaContract.modle modle;
    private AppraisaContract.modles modles;
    public AppraisaPresenter(){
        modle = new AppraisaModel();
        modles = new AppraisaModel();
    }

    @Override
    public void Appraisa( Map token, long current, long size, long collectionAudit, long userId) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        modle.Appraisa( token,current,size,collectionAudit,userId)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                     AppraisaBean.DataBean beans = (AppraisaBean.DataBean) bean.getResult();
                     List<AppraisaBean.DataBean.RecordsBean> dates =  beans.getRecords();
                        mView.Date(dates);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
                        mView.hideLoading();
                    }
                });

    }

    @Override
    public void Appraisas(Context context, Map token, long current, long size, long collectionAudit, long userId) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        modles.Appraisas(context, token,current,size,collectionAudit,userId)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                        AppraisaBean.DataBean beans = (AppraisaBean.DataBean) bean.getResult();
                        List<AppraisaBean.DataBean.RecordsBean> dates =  beans.getRecords();
                        mView.Date(dates);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
                        mView.hideLoading();
                    }
                });
    }
}
