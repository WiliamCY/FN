package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.LoginBean;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

public interface UpdateAboutContract {
    interface  modle{
              Flowable<BaseObjectBean> updateAbout( Map map, RequestBody body);
    }
    interface  View extends BaseView{
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean bean,int status);
    }
    interface  Persenter{
        void updateAbout(Map map, RequestBody body,int status);
    }
}
