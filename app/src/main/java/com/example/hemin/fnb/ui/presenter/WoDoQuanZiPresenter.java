package com.example.hemin.fnb.ui.presenter;

import android.content.Context;

import com.example.hemin.fnb.ui.activity.MyAppraisaS;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.GuanZhuBean;
import com.example.hemin.fnb.ui.bean.MessageImageBean;
import com.example.hemin.fnb.ui.bean.ReleaseBean;
import com.example.hemin.fnb.ui.contract.WodeQuanziContract;
import com.example.hemin.fnb.ui.fragment.QuanZiFragment;
import com.example.hemin.fnb.ui.model.WoDeQuanZiModel;
import com.example.hemin.fnb.ui.net.RxScheduler;

import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;

public class WoDoQuanZiPresenter extends BasePresenter<QuanZiFragment> implements WodeQuanziContract.Presenter {
    private WodeQuanziContract.Model model;
    private WodeQuanziContract.Mode2 mode2;
    private WodeQuanziContract.modle4 modle4;
    private WodeQuanziContract.modle5 modle5;
    private WodeQuanziContract.Collect modle6;

    public WoDoQuanZiPresenter() {
        mode2 = new WoDeQuanZiModel();
        model = new WoDeQuanZiModel();
        modle4 = new WoDeQuanZiModel();
        modle5 = new WoDeQuanZiModel();
        modle6 = new WoDeQuanZiModel();
    }


    @Override
    public void myGuanzhu(Context context, Map map, long current, long size, long userId, final int status) {
        if(!isViewAttached()){
            return;
        }
//        mView.showLoading();
        model.myGuanzhu(context, map,current,size,userId)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
//                        mView.hideLoading();
                        GuanZhuBean.DataBean bean1 = (GuanZhuBean.DataBean) bean.getResult();
                       List<GuanZhuBean.DataBean.RecordsBean> list =  bean1.getRecords();
//                       List<GuanZhuBean.DataBean.RecordsBean> lists = list.getRecords();
//                        List<GuanZhuBean.DataBean.RecordsBean> list = bean1.getData().getRecords();
                        mView.Date(list,status);
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
    public void myFaBu(Context context, final Map map, long current, long size, long userId, final int status) {
        if(!isViewAttached()){
            return;
        }
//        mView.showLoading();
        mode2.myFaBu(context, map,current,size,userId)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);

                        ReleaseBean.DataBean bean1 = (ReleaseBean.DataBean) bean.getResult();
                        List<ReleaseBean.DataBean.RecordsBean> list = bean1.getRecords();
                        mView.Date(list,status);
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
                        String userUrl = bean1.getUserUrl();
                        String nickName = bean1.getNickname();
                        List<MessageImageBean.DataBean.ImagesBean> list = bean1.getImages();
                        String userid = bean1.getUserId();
                        String contnet = bean1.getFriendContent();
                        String isGiveNum = bean1.getIsGiveNum();
                        mView.DateUserId(list,userid,contnet,userUrl,nickName,isGiveNum);
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
    public void Remove(Context context, Map token, String fuid) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        modle5.Remove(context, token,fuid)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                        mView.Date("1",99);
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
    public void MyCollect(Context context, Map token, long current, long size, long userId,int status) {
        if(!isViewAttached()){
            return;
        }
        mView.showLoading();
        modle6.MyCollect(context, token,current,size,userId)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
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
