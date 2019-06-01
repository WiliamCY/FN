package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;

import java.util.Map;

import io.reactivex.Flowable;

public interface WodeQuanziContract  {
    interface Model {
        Flowable<BaseObjectBean> myGuanzhu(Context context, Map map, long current, long size, long userId);
    }
        interface Mode2 {
            Flowable<BaseObjectBean> myFaBu(Context context, Map map, long current,long size,long userId);
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

        void myGuanzhu(Context context,Map map, String userId);

    }

}
