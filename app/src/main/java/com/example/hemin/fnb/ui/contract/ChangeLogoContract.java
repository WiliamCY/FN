package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.ImageUrlBean;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface  ChangeLogoContract {
    interface modle1 {
        Flowable<BaseObjectBean> changeImage(Context context, Map token, RequestBody body);

    }

    interface Mode2 {
        Flowable<BaseObjectBean<ImageUrlBean.DataBean>> postImage(Context context, Map token, MultipartBody.Part partList);
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

        void changeImage(Context context, Map token, RequestBody body);

        void postImage(Context context, Map token, MultipartBody.Part partList);

    }
}