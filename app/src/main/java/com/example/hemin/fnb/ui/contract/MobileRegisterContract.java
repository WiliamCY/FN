package com.example.hemin.fnb.ui.contract;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

public interface MobileRegisterContract {
    interface  mobile{
        Flowable<BaseObjectBean> getCode(String mobile);
    }
    interface mobile2{
        Flowable<BaseObjectBean> getUpdateMobile(Map map, RequestBody body);
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
    interface persenter{
         void getCode(String mobile,int status);
         void updateMobile(Map map,RequestBody body,int status);
    }
}
