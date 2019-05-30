package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.ImageUrlBean;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface MessageFinderAddContract {
    interface modle1{
           Flowable<BaseObjectBean> friendAdd(Context context, Map token, RequestBody body);

    }
    interface Mode2 {
        Flowable<BaseObjectBean<ImageUrlBean.DataBean>> postImage(Context context, Map token, MultipartBody.Part partList);
    }


    interface View extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();
        void Date(Object object, int index);
        void Status( int index);
        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean bean);
    }
    interface Presenter {

        void friendAdd(Context context, Map token, RequestBody body);
        void postImage(Context context, Map token, MultipartBody.Part partList);

    }

}
