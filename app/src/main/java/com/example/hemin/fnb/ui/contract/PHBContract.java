package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.Find5Bean;
import com.example.hemin.fnb.ui.bean.FindBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

public interface  PHBContract {
    interface dianzan {
        Flowable<BaseObjectBean<Find5Bean.DataBean>> getRankingList(Context context, Map<String, String> heard, long current, long size);
    }


    interface View extends BaseView{
        @Override
        void showLoading();

        @Override
        void hideLoading();


        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean bean);
    }
    interface  Presenter{
        void getRankingList(Context context, Map<String, String> heard, long current, long size);
    }
}
