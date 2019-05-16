package com.example.hemin.fnb.ui.presenter;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.hemin.fnb.ui.activity.CodeLoginActivity;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.RegisterContract;
import com.example.hemin.fnb.ui.model.RegisterModel;
import com.example.hemin.fnb.ui.net.RxScheduler;

import java.util.HashMap;

import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

public class RegisterPresenter extends BasePresenter<RegisterContract.View >implements RegisterContract.Presenter {

    private RegisterContract.GetCodeModel model;
    private RegisterContract.RegisterModle modle2;
    public RegisterPresenter(){
        model = new RegisterModel();
        modle2 = new RegisterModel();
    }


    @Override
    public void register(final Context context, RequestBody body) {
              if(!isViewAttached()){
                  return;
              }
              mView.showLoading();
        modle2.register(context,body)
                      .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                      .as(mView.<BaseObjectBean>bindAutoDispose())
                      .subscribe(new Consumer<BaseObjectBean>() {
                          @Override
                          public void accept(BaseObjectBean bean) throws Exception {
                              mView.onSuccess(bean);
                              mView.hideLoading();
                              if(bean.getErrorCode() == 0){
                                  Intent intent = new Intent(context, CodeLoginActivity.class);
                                              context.startActivity(intent);
                              }


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
    public void getCode(String mobile) {
             if(!isViewAttached()){
                 return;
             }

        mView.showLoading();
        model.getCodes(mobile)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
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
