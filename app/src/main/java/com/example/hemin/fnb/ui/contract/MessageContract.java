package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.AppraisaBean;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.MessageImageBean;
import com.example.hemin.fnb.ui.bean.MessageImages;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

public interface MessageContract  {
    interface modle1{
           Flowable<BaseObjectBean> getMaga(Context context,Map token, long current, long size, String type );

    }
    interface modle2{
        Flowable<BaseObjectBean> getTuiJian(Context context,Map token, long current, long size, int userId );

    }
    interface modle3{
        Flowable<BaseObjectBean> getGuanZhu(Context context,Map token, long current, long size,  int userId );

    }
    interface modle4{
        Flowable<BaseObjectBean> getFidner(Context context, Map token, long friendId, int userId );

    }


    interface View extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();
        void Date(Object object,int index);
        void DateUserId(Object object,String userId,String content,String userUrl,String nickName,String isCollectionSum,String isGiveNum,String isFocus,int focusNum ,int giveNum );
        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean bean);
    }
    interface Presenter {

        void getMaga(Context context, Map token,long current,long size,String type,int status);
        void getTuiJian(Context context,Map token, long current, long size,int userId,int status );
        void getGuanZhu(Context context,Map token, long current, long size,int userId ,int status);
        void getFidner(Context context,Map token, long friendId,int userId );
    }
}
