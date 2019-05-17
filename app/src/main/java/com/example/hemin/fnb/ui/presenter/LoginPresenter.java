package com.example.hemin.fnb.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.hemin.fnb.ui.activity.CodeLoginActivity;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.LoginContract;
import com.example.hemin.fnb.ui.contract.RegisterContract;
import com.example.hemin.fnb.ui.model.LoginModel;
import com.example.hemin.fnb.ui.model.RegisterModel;
import com.example.hemin.fnb.ui.net.RxScheduler;

import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

public class LoginPresenter extends BasePresenter<LoginContract.View >implements LoginContract.Presenter {

    private LoginContract.Model model;
    private LoginContract.GetCodeModel modle2;


    public LoginPresenter(){
        model = new LoginModel();
        modle2 = new LoginModel();


    }



    @Override
    public void login(final Context context, String mobile , String code) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        model.login(context, mobile,code)
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
    @Override
    public void getCode(String mobile) {
        if(!isViewAttached()){
            return;
        }

        mView.showLoading();
        modle2.getCodes(mobile)
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
