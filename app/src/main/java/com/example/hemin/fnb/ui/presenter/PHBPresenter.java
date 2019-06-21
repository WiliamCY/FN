package com.example.hemin.fnb.ui.presenter;

import android.content.Context;

import com.example.hemin.fnb.ui.activity.PHBActivity;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.Find5Bean;
import com.example.hemin.fnb.ui.bean.PHBBean;
import com.example.hemin.fnb.ui.contract.PHBContract;
import com.example.hemin.fnb.ui.model.PHBModel;
import com.example.hemin.fnb.ui.net.RxScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;

public class PHBPresenter extends BasePresenter<PHBActivity> implements PHBContract.Presenter {
    private List<PHBBean> lists = new ArrayList<>();
    private PHBContract.dianzan modle;

    public PHBPresenter() {
        modle = new PHBModel();
    }

    @Override
    public void getRankingList(Context context, Map<String, String> heard, long current, long size) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        modle.getRankingList(context, heard, current, size)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                        lists.clear();
                        Find5Bean.DataBean bean1 = (Find5Bean.DataBean) bean.getData();
                        List<Find5Bean.DataBean.RecordsBean> list = bean1.getRecords();
                        for (int i = 1; i < list.size(); i++) {
                            String collectionId = list.get(i).getCollectionId();
                            String collectionNum = list.get(i).getCollectionNum();
                            String giveNum = list.get(i).getGiveNum();
                            String wantNum = list.get(i).getWantNum();
                            String imagesUrl = list.get(i).getImagesUrl();
                            String index = String.valueOf(i);
                            PHBBean bean2 = new PHBBean(collectionId, giveNum, collectionNum, wantNum, imagesUrl, index);
                            lists.add(bean2);
                        }
                        mView.Date(lists);
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
