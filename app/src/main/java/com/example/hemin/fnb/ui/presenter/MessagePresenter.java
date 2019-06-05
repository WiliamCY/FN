package com.example.hemin.fnb.ui.presenter;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.MessageBean1;
import com.example.hemin.fnb.ui.bean.MessageBean2;
import com.example.hemin.fnb.ui.bean.MessageBean3;
import com.example.hemin.fnb.ui.bean.MessageImageBean;
import com.example.hemin.fnb.ui.bean.MessageImages;
import com.example.hemin.fnb.ui.contract.MessageContract;
import com.example.hemin.fnb.ui.fragment.MessageFragment;
import com.example.hemin.fnb.ui.fragment.TabMessageFragment;
import com.example.hemin.fnb.ui.model.MessageModle;
import com.example.hemin.fnb.ui.net.RxScheduler;

import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;

public class MessagePresenter extends BasePresenter<MessageFragment>  implements MessageContract.Presenter {
    private MessageContract.modle1 modle1;
    private MessageContract.modle2  modle2;
    private MessageContract.modle3 modle3;
    private MessageContract.modle4 modle4;
    public  MessagePresenter(){
        modle1 = new MessageModle();
        modle2 = new MessageModle();
        modle3 = new MessageModle();
        modle4 = new MessageModle();
    }


    @Override
    public void getMaga(Context context, Map token, long current, long size, String type) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        modle1.getMaga(context, token,current,size,type)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                        MessageBean1.DataBean bean1 = (MessageBean1.DataBean) bean.getResult();
                        List<MessageBean1.DataBean.RecordsBean> list = bean1.getRecords();
                        mView.Date(list,1);
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
    public void getTuiJian(Context context, Map token, long current, long size, int userId) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        modle2.getTuiJian(context, token,current,size,userId)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                        MessageBean2.DataBean bean2 = (MessageBean2.DataBean) bean.getResult();
                        List<MessageBean2.DataBean.RecordsBean> list = bean2.getRecords();
                        mView.Date(list,2);
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
    public void getGuanZhu(Context context, Map token, long current, long size, int userId) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        modle3.getGuanZhu(context, token,current,size,userId)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                        MessageBean3.DataBean bean3 = (MessageBean3.DataBean) bean.getResult();
                        List<MessageBean3.DataBean.RecordsBean> list = bean3.getRecords();
                        mView.Date(list,3);
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
    public void getFidner(Context context, Map token, long friendId, final int userId) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        modle4.getFidner(context, token,friendId,userId)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                        MessageImageBean.DataBean bean1 = (MessageImageBean.DataBean) bean.getResult();
                        List<MessageImageBean.DataBean.ImagesBean> list = bean1.getImages();
                        String userid = bean1.getUserId();
                        String contnet = bean1.getFriendContent();
                        String urlImage = bean1.getUserUrl();
                        String nickName = bean1.getNickname();
                        mView.DateUserId(list,userid,contnet,urlImage,nickName);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
                        mView.hideLoading();
                    }
                });
    }
}
