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
import com.example.hemin.fnb.ui.bean.MessageBean1;
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
    private FindContract.modle1 za;
    private FindContract.DzScXy dcy;
    private List<FindBean> beansDate = new ArrayList<>();
    private List<String> imageUrls = new ArrayList<>();
    private List<String> pathUrl = new ArrayList<>();
    private String activityUrl, intentUrl, GiveNum, CollectionNum, WantNum;
    private Object dates = new ArrayList<>();
    private ArrayList<String> deilyUrlList = new ArrayList<>();

    public FindPresenter() {
        modle = new FindModel();
        modle2 = new FindModel();
        modle3 = new FindModel();
        modle4 = new FindModel();
        modle5 = new FindModel();
        modle6 = new FindModel();
        modle7 = new FindModel();
        modle8 = new FindModel();
        za = new FindModel();
        dcy = new FindModel();

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
                        FindHuoListBean.DataBean bean1 = (FindHuoListBean.DataBean) bean.getResult();
                        List<FindHuoListBean.DataBean.RecordsBean> list = bean1.getRecords();
                        for (int i = 0; i < list.size() && list.size() < 4; i++) {
                            activityUrl = list.get(i).getActivityUrl();
                            intentUrl = list.get(i).getActivityContentUrl();
                            FindBean beansDatec = new FindBean(activityUrl, intentUrl, 1, 4);
                            beansDate.add(beansDatec);
                        }


                        mView.Date(beansDate, 1);
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
                        Find2Bean.DataBean beans = (Find2Bean.DataBean) bean.getResult();
                        List<Find2Bean.DataBean.RecordsBean> list = beans.getRecords();
                        mView.Date(list, 2);
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
                        deilyUrlList.clear();
                        mView.onSuccess(bean);
                        FindDeilyBean.DataBean bean1 = (FindDeilyBean.DataBean) bean.getResult();
//                        List<FindDeilyBean.DataBean.ListBean> list = bean1.getList();
                        mView.Date(bean1, 3);
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
                        List<Find4Bean.DataBean> list = (List<Find4Bean.DataBean>) bean.getResult();
                        mView.Date(list.get(0), 4);


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
                        Find5Bean.DataBean bean1 = (Find5Bean.DataBean) bean.getResult();
                        List<Find5Bean.DataBean.RecordsBean> list = bean1.getRecords();
                        mView.Date(list.get(1), 5);
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

    }

    @Override
    public void getMaga(Context context, Map token, long current, long size, String type, final int status) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        za.getMaga(context, token, current, size, type)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                        MessageBean1.DataBean bean1 = (MessageBean1.DataBean) bean.getData();
                        List<MessageBean1.DataBean.RecordsBean> list = bean1.getRecords();
                        mView.Date(list, 6);


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
    public void getDCX(Context context, Map token, long type, long dailyId, long userId, int status) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        dcy.getDCX(context, token, type, dailyId, userId)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                      mView.Date("111",7);


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
