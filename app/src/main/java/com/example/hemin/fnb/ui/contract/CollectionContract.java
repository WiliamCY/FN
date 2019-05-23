package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

public interface CollectionContract {
    interface Model {
        Flowable<BaseObjectBean> getColldetionMessage(Context context, Map map, String id);
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

        void getColldetionMessage(Context context,Map map, String id);


    }


}
