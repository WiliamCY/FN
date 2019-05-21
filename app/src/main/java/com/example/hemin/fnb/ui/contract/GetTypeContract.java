package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.ImageUrlBean;
import com.example.hemin.fnb.ui.bean.TypeBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface GetTypeContract {
    interface GetTypeModle{
         Flowable<BaseObjectBean<List<TypeBean.DataBean>>> getType(Context context, Map token);
    }
    interface Model {
        Flowable<BaseObjectBean<ImageUrlBean.DataBean>> postImage(Context context, Map token, MultipartBody.Part partList);
    }
    interface  AddImageButton{
        Flowable<BaseObjectBean> submitImage(Context context, Map token,RequestBody body);
    }

    interface View  extends BaseView{
        @Override
        void showLoading();

        @Override
        void hideLoading();

        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean bean);
    }
    interface GetTytpePresebter{
         void getType(Context context,Map token);
        void postImage(Context context, Map token, MultipartBody.Part partList);
        void  submitImage(Context context,Map map,RequestBody body);
    }

}
