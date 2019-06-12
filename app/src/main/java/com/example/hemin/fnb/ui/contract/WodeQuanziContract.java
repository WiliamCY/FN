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
    interface modle4{
        Flowable<BaseObjectBean> getFidner(Context context, Map token, long friendId, int userId );
    }
   interface  modle5{
       Flowable<BaseObjectBean> Remove(Context context,Map token,String fuId);

    }

    interface View extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();
        void Date(Object object,int index);
        @Override
        void onError(Throwable throwable);
        void DateUserId(Object object,String userId,String content,String url,String nickName);
        void onSuccess(BaseObjectBean bean);
    }
    interface Presenter {

        void myGuanzhu(Context context,Map map, long current, long size, long userId,int status);

        void myFaBu(Context context,Map map, long current,long size,long userId,int status);

        void getFidner(Context context,Map token, long friendId,int userId );
        void  Remove(Context context,Map token,String fuid);
    }

}
