package com.example.hemin.fnb.ui.presenter;

import android.content.Context;

import com.example.hemin.fnb.ui.activity.PasswordActivity;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.PwLoginContract;
import com.example.hemin.fnb.ui.model.PasswordModel;
import com.example.hemin.fnb.ui.net.RxScheduler;

import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

public class PasswordPresenter extends BasePresenter<PasswordActivity> implements PwLoginContract.Presenter {
    private PwLoginContract.Model model;
    public  PasswordPresenter(){
        model = new PasswordModel();
    }

    @Override
    public void pwLogn(Context context, RequestBody body) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        model.pwLogin(context, body)
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
