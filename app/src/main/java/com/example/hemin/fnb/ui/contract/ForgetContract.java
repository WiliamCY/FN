package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

public interface ForgetContract {
    interface Model {
        Flowable<BaseObjectBean> forget(Context context, RequestBody body);
    }

    //获取验证码
    interface GetCodeModel {
        Flowable<BaseObjectBean> getCodes(String mobile);
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

        void Forget(Context context, RequestBody body);
        void getCode(String mobile);

    }


}
