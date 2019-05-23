package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.AppraisaBean;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

public interface AppraisaContract {
    interface  modle{
     Flowable<BaseObjectBean<AppraisaBean.DataBean>> Appraisa( Map token,long current,long size,long collectionAudit,long userId );
    }
    interface  modles{
        Flowable<BaseObjectBean<AppraisaBean.DataBean>> Appraisas(Context context, Map token,long current,long size,long collectionAudit,long userId );
    }

    interface View extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();
        void Date(List<AppraisaBean.DataBean.RecordsBean> list);
        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean bean);
    }
    interface Presenter {

        void Appraisa( Map token,long current,long size,long collectionAudit,long userId);
        void Appraisas(Context context, Map token,long current,long size,long collectionAudit,long userId);

    }
}
