package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

public interface PwLoginContract {
    interface Model {
        Flowable<BaseObjectBean> pwLogin(Context context, RequestBody body);
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

        void pwLogn(Context context, RequestBody body);

    }


}
