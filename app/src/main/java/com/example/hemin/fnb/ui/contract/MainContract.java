package com.example.hemin.fnb.ui.contract;



import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.LoginBean;

import io.reactivex.Flowable;



public interface MainContract {
    interface Model {
        Flowable<BaseObjectBean<LoginBean>> login(String username, String password);
    }

    interface View extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean<LoginBean> bean);
    }

    interface Presenter {
        //登录
        void login(String username, String password);

    }
}
