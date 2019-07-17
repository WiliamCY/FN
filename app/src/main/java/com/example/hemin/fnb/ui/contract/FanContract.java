package com.example.hemin.fnb.ui.contract;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;

import java.util.Map;

import io.reactivex.Flowable;

public interface FanContract {
    interface  modle{
        Flowable<BaseObjectBean> GZ(Map token,int current, int size, int userId);
    }
    interface  modle2{
        Flowable<BaseObjectBean> fs(Map token,int current,int size);
    }
    interface  View extends BaseView{
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean bean);
    }
    interface Presenter{
        void GZ(Map token,int current ,int size,int userId,int status);
        void fs(Map token,int current,int size,int status);
    }
}
