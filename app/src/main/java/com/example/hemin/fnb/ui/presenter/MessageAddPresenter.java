package com.example.hemin.fnb.ui.presenter;

import android.content.Context;

import com.example.hemin.fnb.ui.activity.MessageAdd;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.ImageUrlBean;
import com.example.hemin.fnb.ui.contract.MessageFinderAddContract;
import com.example.hemin.fnb.ui.model.FinderAddModel;
import com.example.hemin.fnb.ui.net.RxScheduler;

import java.util.Map;

import io.reactivex.functions.Consumer;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MessageAddPresenter extends BasePresenter<MessageAdd> implements MessageFinderAddContract.Presenter {
    private MessageFinderAddContract.modle1 modle1;
    private MessageFinderAddContract.Mode2 mode2;
    public  MessageAddPresenter(){
        this.modle1 = new FinderAddModel();
        this.mode2 = new FinderAddModel();
    }
    @Override
    public void friendAdd(Context context, Map token, RequestBody body) {
        if(!isViewAttached()){
            return;
        }

        mView.showLoading();
        modle1.friendAdd(context,token,body)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                        if(bean.getErrorCode() == 0){
                            mView.Status(1);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
                        mView.hideLoading();
                    }
                });

    }

    @Override
    public void postImage(Context context, Map token, MultipartBody.Part partList) {
        if (!isViewAttached()) {
            return;
        }

//
//        mView.showLoading();
        mode2.postImage(context, token, partList)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
//                        picLength++;
//                        if(picLength>1){
//                            mView.hideLoading();
//                        }
//                      if(picLength>=2){
//                          mView.hideLoading();
//                      }

                        ImageUrlBean.DataBean url = (ImageUrlBean.DataBean) bean.getResult();
                        String urls = url.getUrl();
                        mView.getPostImageUrls(urls);
                        if(bean.getErrorCode() == 0){
                            mView.Status(0);
                        }


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
//                        mView.hideLoading();
                    }
                });
    }
}
