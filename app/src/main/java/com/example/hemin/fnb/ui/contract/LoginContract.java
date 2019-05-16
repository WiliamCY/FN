package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;

import io.reactivex.Flowable;

public interface LoginContract {
    interface LoginModle{
        Flowable<BaseObjectBean> login(Context context,String mobile,String code);
    }
    interface  LoginView extends BaseView{
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean bean);
    }

}
