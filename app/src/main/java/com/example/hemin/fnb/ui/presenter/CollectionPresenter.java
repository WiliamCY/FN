package com.example.hemin.fnb.ui.presenter;

import android.content.Context;
import android.content.Intent;

import com.example.hemin.fnb.ui.activity.PasswordActivity;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.ColletionBean;
import com.example.hemin.fnb.ui.contract.CollectionContract;
import com.example.hemin.fnb.ui.model.CollectionModel;
import com.example.hemin.fnb.ui.net.RxScheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;

public class CollectionPresenter extends BasePresenter<CollectionContract.View> implements CollectionContract.Presenter {
    private CollectionContract.Model model;
    public CollectionPresenter(){
             model = new CollectionModel();
    }
    @Override
    public void getColldetionMessage(Context context, Map map, String id) {
           if(!isViewAttached()){
               return;
           }
        mView.showLoading();
        model.getColldetionMessage(context, map,id)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();
                        ColletionBean.DataBean date = (ColletionBean.DataBean) bean.getResult();
                        String  collectionId = date.getCollectionId();
                        String collectionNum = date.getCollectionNum();
                        String ctName = date.getCtName();
                        String collectionDetails = date.getCollectionDetails();
                       List<ColletionBean.DataBean.ListBean > list = date.getList();
                       for(int i =0 ;i<list.size();i++){
                           String imagesId = list.get(i).getImagesId();
                           String imagesUrl = list.get(i).getImagesUrl();
                           String ieId = list.get(i).getIeId();
                           String ieName = list.get(i).getIeName();

                       }
                        Map<String,String> map = new HashMap<>();
                           map.put("collectionId",collectionId);
                           map.put("collectionNum",collectionNum);
                           map.put("ctName", ctName);
                           map.put("collectionDetails",collectionDetails);
                           map.put("image",list.get(1).getImagesUrl());
                        mView.Date(map);
                        mView.ListDate(list);
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
