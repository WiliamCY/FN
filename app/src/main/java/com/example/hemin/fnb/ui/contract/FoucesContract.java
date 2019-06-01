package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;

import java.util.Map;

import io.reactivex.Flowable;

public interface FoucesContract {
    interface modle{
        Flowable<BaseObjectBean> Fouces(Context context,Map token, long finder, long userId);
    }
    interface modle2{
        Flowable<BaseObjectBean> getZan(Context context,Map token, long finder, long userId,long type);
    }
    interface View extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();
        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean bean,int status);
    }
 interface  Presenter{
        void Fouces(Context context,Map token,long finder,long userId);
          void getZan(Context context,Map token,long finder,long userId,long type);
 }


}