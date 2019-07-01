package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.base.FindRankBean;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.Find2Bean;
import com.example.hemin.fnb.ui.bean.Find4Bean;
import com.example.hemin.fnb.ui.bean.Find5Bean;
import com.example.hemin.fnb.ui.bean.FindDeilyBean;
import com.example.hemin.fnb.ui.bean.FindFirstBean;
import com.example.hemin.fnb.ui.bean.FindHuaBean;
import com.example.hemin.fnb.ui.bean.FindHuoListBean;
import com.example.hemin.fnb.ui.bean.FindLoveBean;
import com.example.hemin.fnb.ui.contract.FindContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

public class FindModel implements FindContract.huo,FindContract.addNum,FindContract.hua,FindContract.huas,FindContract.js,FindContract.cn,FindContract.dianzan,FindContract.dianzans,FindContract.modle1 {
    @Override
    public Flowable<BaseObjectBean<FindHuoListBean.DataBean>> pageListHuo(Context context, Map<String, String> heard, long id, long size) {
        return RetrofitClient.getInstance().getApi().pageListHuo(heard,id,size);
    }

    @Override
    public Flowable<BaseObjectBean> addNum(Context context, Map<String, String> heard, long activityId, long userId) {
        return  RetrofitClient.getInstance().getApi().addNum(heard,activityId,userId);
    }

    @Override
    public Flowable<BaseObjectBean<Find2Bean.DataBean>> addHua(Context context, Map<String, String> heard, long current, long size) {
        return RetrofitClient.getInstance().getApi().addHua(heard,current,size);
    }

    @Override
    public Flowable<BaseObjectBean<FindDeilyBean.DataBean>> getDaily(Context context, Map<String, String> heard, long userId) {
        return RetrofitClient.getInstance().getApi().getDaily(heard,userId);
    }

//    @Override
//    public Flowable<BaseObjectBean<List<FindLoveBean.DataBean>>> guessLove(Context context, Map<String, String> heard) {
//        return RetrofitClient.getInstance().getApi().guessLove(heard);
//    }

    @Override
    public Flowable<BaseObjectBean<Find5Bean.DataBean>> getRankingList(Context context, Map<String, String> heard, long current, long size) {
        return RetrofitClient.getInstance().getApi().getRankingList(heard,current,size);
    }

    @Override
    public Flowable<BaseObjectBean> getRanking(Context context, Map<String, String> heard, long collectionId) {
        return RetrofitClient.getInstance().getApi().getRanking(heard,collectionId);
    }

    @Override
    public Flowable<BaseObjectBean> huas(Context context, Map<String, String> heard, long topicId, long userId) {
        return RetrofitClient.getInstance().getApi().addHuas(heard,topicId,userId);
    }

    @Override
    public Flowable<BaseObjectBean<List<Find4Bean.DataBean>>> guessLove(Context context, Map<String, String> heard) {
          return RetrofitClient.getInstance().getApi().guessLove(heard);
    }

    @Override
    public Flowable<BaseObjectBean> getMaga(Context context, Map token, long current, long size, String type) {
        return RetrofitClient.getInstance().getApi().getMaga(token,current,size,type);
    }
}
