package com.example.hemin.fnb.ui.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.hemin.fnb.ui.activity.PasswordActivity;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.ForgetContract;
import com.example.hemin.fnb.ui.contract.LoginContract;
import com.example.hemin.fnb.ui.model.ForgetModel;
import com.example.hemin.fnb.ui.model.LoginModel;
import com.example.hemin.fnb.ui.net.RxScheduler;

import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

public class ForgetPresenter extends BasePresenter<ForgetContract.View >implements ForgetContract.Presenter {

    private ForgetContract.Model model;
    private ForgetContract.GetCodeModel modle2;


    public ForgetPresenter(){
        model = new ForgetModel();
        modle2 = new ForgetModel();


    }



    @Override
    public void Forget(final Context context, RequestBody body) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        model.forget(context, body)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                        Intent intent = new Intent(context, PasswordActivity.class);
                        context.startActivity(intent);
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
