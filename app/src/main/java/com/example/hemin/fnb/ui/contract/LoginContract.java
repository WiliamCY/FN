package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

public interface LoginContract {
    interface Model {
        Flowable<BaseObjectBean> login(Context context,String mobile, String code);
    }

    //获取验证码
    interface GetCodeModel {
        Flowable<BaseObjectBean> getCodes(Context context,String mobile);
    }

    interface View extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean bean);
    }

    interface Presenter {

        void login(Context context, String mobile,String code);
        void getCode(Context context,String mobile);

    }


}
