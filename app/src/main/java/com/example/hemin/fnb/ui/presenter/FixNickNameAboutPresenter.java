package com.example.hemin.fnb.ui.presenter;

import android.content.Context;

import com.example.hemin.fnb.ui.activity.FixNickName;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.UpdateAboutContract;
import com.example.hemin.fnb.ui.model.UpdateAboutModle;
import com.example.hemin.fnb.ui.net.RxScheduler;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

public class FixNickNameAboutPresenter extends BasePresenter<FixNickName> implements UpdateAboutContract.Persenter {
    private UpdateAboutContract.modle modle;
    public FixNickNameAboutPresenter(){
        modle = new UpdateAboutModle();
    }
    @Override
    public void updateAbout( Map map, RequestBody body, final int status) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        modle.updateAbout( map,body)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean,status);
                        mView.hideLoading();
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
