package com.example.hemin.fnb.ui.presenter;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.base.FindRankBean;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.Find2Bean;
import com.example.hemin.fnb.ui.bean.Find4Bean;
import com.example.hemin.fnb.ui.bean.Find5Bean;
import com.example.hemin.fnb.ui.bean.FindBean;
import com.example.hemin.fnb.ui.bean.FindDeilyBean;
import com.example.hemin.fnb.ui.bean.FindFirstBean;
import com.example.hemin.fnb.ui.bean.FindHuaBean;
import com.example.hemin.fnb.ui.bean.FindHuoListBean;
import com.example.hemin.fnb.ui.bean.FindLoveBean;
import com.example.hemin.fnb.ui.contract.FindContract;
import com.example.hemin.fnb.ui.model.FindModel;
import com.example.hemin.fnb.ui.net.RxScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;

public class FindPresenter extends BasePresenter<FindContract.View> implements FindContract.Presenter {
    private FindContract.huo modle;
    private FindContract.addNum modle2;
    private FindContract.hua modle3;
    private FindContract.huas modle4;
    private FindContract.js modle5;
    private FindContract.cn modle6;
    private FindContract.dianzan modle7;
    private FindContract.dianzans modle8;
    private FindBean beansDate = new FindBean();
    private List<String> imageUrls = new ArrayList<>();
    private List<String> pathUrl = new ArrayList<>();
    private String activityUrl,intentUrl;

    public FindPresenter() {
        modle = new FindModel();
        modle2 = new FindModel();
        modle3 = new FindModel();
        modle4 = new FindModel();
        modle5 = new FindModel();
        modle6 = new FindModel();
        modle7 = new FindModel();
        modle8 = new FindModel();

    }

    @Override
    public void pageListHuo(Context context, Map<String, String> heard, long id, long size) {
        if (!isViewAttached()) {
            return;
        }

        modle.pageListHuo(context, heard, id, size)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        FindFirstBean.DataBean bean1 = (FindFirstBean.DataBean) bean.getResult();
                        List<FindFirstBean.DataBean.RecordsBean> list = bean1.getRecords();
                        mView.Date(list,1);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);

                    }
                });
    }

    @Override
    public void addNum(Context context, Map<String, String> heard, long activityId, long userId) {
        if (!isViewAttached()) {
            return;
        }

        modle2.addNum(context, heard, activityId, userId)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);

                    }
                });
    }

    @Override
    public void addHua(Context context, Map<String, String> heard, long current, long size) {
        if (!isViewAttached()) {
            return;
        }

        modle3.addHua(context, heard, current, size)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        List<String> imageUris = new ArrayList<>();
                        Find2Bean.DataBean beans = (Find2Bean.DataBean) bean.getResult();
                        List<Find2Bean.DataBean.RecordsBean> list = beans.getRecords();
////                        for (int i = 0; i <2; i++) {
//                             activityUrl = list.get(0).getTopicUrl();
//                           intentUrl = list.get(0).getTopicContent();
//
////                        }
//                         beansDate = new FindBean(activityUrl,intentUrl, 0);
////                        beansDate.add(bean2);
////                        mView.Date(beansDate);
                        mView.Date2(list,2);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);

                    }
                });

    }

    @Override
    public void huas(Context context, Map<String, String> heard, long topicId, long userId) {
        if (!isViewAttached()) {
            return;
        }

        modle4.huas(context, heard, topicId, userId)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);


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
    public void getDaily(Context context, Map<String, String> heard, long userId) {
        if (!isViewAttached()) {
            return;
        }

        modle5.getDaily(context, heard, userId)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        List<String> imageUris = new ArrayList<>();
                        mView.onSuccess(bean);
                        FindDeilyBean.DataBean bean1 = (FindDeilyBean.DataBean) bean.getResult();
                        List<FindDeilyBean.DataBean.ListBean> list = bean1.getList();
//                             activityUrl = list.get(0).getImagesUrl();

//                            beansDate = new FindBean(activityUrl,activityUrl, 1);
//                        mView.Date(beansDate);
                  mView.Date3(list,3);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);

                    }
                });
    }

    @Override
    public void guessLove(Context context, Map<String, String> heard) {
        if (!isViewAttached()) {
            return;
        }

        modle6.guessLove(context, heard)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        List<String> imageUris = new ArrayList<>();
                        List<Find4Bean.DataBean> list = (List<Find4Bean.DataBean>) bean.getResult();
//                        for (int i = 0; i < 2; i++) {
//                            activityUrl = list.get(0).getImagesUrl();
//
//                             beansDate = new FindBean(activityUrl,activityUrl, 1);
//                            beansDate.add(bean2);
//                        }
//                          mView.Date(beansDate);
                        mView.Date4(list,4);


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);

                    }
                });
    }

    @Override
    public void getRankingList(Context context, Map<String, String> heard, long current, long size) {
        if (!isViewAttached()) {
            return;
        }

        modle7.getRankingList(context, heard, current, size)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        List<String> imageUris = new ArrayList<>();
                        Find5Bean.DataBean bean1 = (Find5Bean.DataBean) bean.getResult();
                        List<Find5Bean.DataBean.RecordsBean> list = bean1.getRecords();
//                        for (int i = 0; i < 2; i++) {
//                            activityUrl = list.get(0).getImagesUrl();
//                        beansDate  = new FindBean(activityUrl,activityUrl, 2);
//                            beansDate.add(bean2);
//                        }
//                        mView.Date(beansDate);
                        mView.Date5(list,5);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);

                    }
                });
    }

    @Override
    public void getRanking(Context context, Map<String, String> heard, long collectionId) {
        if (!isViewAttached()) {
            return;
        }

        modle8.getRanking(context, heard, collectionId)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);


                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);

                    }
                });
    }
}
