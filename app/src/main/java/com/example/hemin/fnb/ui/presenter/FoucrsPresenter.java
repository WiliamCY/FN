package com.example.hemin.fnb.ui.presenter;

import android.content.Context;

import com.example.hemin.fnb.ui.activity.TaskBigImgActivity;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.FoucesContract;
import com.example.hemin.fnb.ui.model.FoucesModle;
import com.example.hemin.fnb.ui.net.RxScheduler;

import java.util.Map;

import io.reactivex.functions.Consumer;

public class FoucrsPresenter extends BasePresenter<TaskBigImgActivity> implements FoucesContract.Presenter{
    private FoucesContract.modle modle;
    private FoucesContract.modle2 modle2;
   public  FoucrsPresenter(){
       modle = new FoucesModle();
       modle2 = new FoucesModle();
   }
    @Override
    public void Fouces(Context context, Map token, long finder, long userId) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        modle.Fouces(context, token,finder,userId)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean,0);
                        mView.hideLoading();
                        mView.FoucesStatus(bean.getErrorCode());
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
    public void getZan(Context context, Map token, long finder, long userId, long type) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        modle2.getZan(context, token,finder,userId,type)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean,1);
                        mView.hideLoading();
                        mView.zanResult(bean.getErrorCode());
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
