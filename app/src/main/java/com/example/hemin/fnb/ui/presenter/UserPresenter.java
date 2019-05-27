package com.example.hemin.fnb.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.hemin.fnb.ui.activity.MainActivity;
import com.example.hemin.fnb.ui.activity.PasswordActivity;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.LoginContract;
import com.example.hemin.fnb.ui.contract.UserContract;
import com.example.hemin.fnb.ui.model.LoginModel;
import com.example.hemin.fnb.ui.model.UserModel;
import com.example.hemin.fnb.ui.net.RxScheduler;

import java.util.Map;

import io.reactivex.functions.Consumer;

public class UserPresenter extends BasePresenter<UserContract.View >implements UserContract.Presenter {

    private UserContract.Model model;


    public UserPresenter(){
        model = new UserModel();


    }





    @Override
    public void exit(final Context context, Map map, String userId) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        model.exit(context,map,userId)
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
