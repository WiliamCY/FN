package com.example.hemin.fnb.ui.contract;

import android.content.Context;

import com.example.hemin.fnb.ui.base.BaseView;
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

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

public interface FindContract {
    interface huo {
        Flowable<BaseObjectBean<FindFirstBean.DataBean>> pageListHuo(Context context, Map<String, String> heard, long id, long size);
    }

    interface addNum {
        Flowable<BaseObjectBean> addNum(Context context, Map<String, String> heard, long activityId, long userId);
    }

    interface hua {
        Flowable<BaseObjectBean<Find2Bean.DataBean>> addHua(Context context, Map<String, String> heard, long current, long size);
    }

    interface huas {
        Flowable<BaseObjectBean> huas(Context context, Map<String, String> heard, long topicId, long userId);
    }

    interface js {
        Flowable<BaseObjectBean<FindDeilyBean.DataBean>> getDaily(Context context, Map<String, String> heard, long userId);
    }

    interface cn {
        Flowable<BaseObjectBean<List<Find4Bean.DataBean>>> guessLove(Context context, Map<String, String> heard);
    }

    interface dianzan {
        Flowable<BaseObjectBean<Find5Bean.DataBean>> getRankingList(Context context, Map<String, String> heard, long current, long size);
    }

    interface dianzans {
        Flowable<BaseObjectBean> getRanking(Context context, Map<String, String> heard, long collectionId);
    }

    interface View extends BaseView {
        @Override
        void showLoading();

        @Override
        void hideLoading();
        void Date(List<FindFirstBean.DataBean.RecordsBean> bean, int status);
        void Date2(List<Find2Bean.DataBean.RecordsBean> bean, int status);
        void Date3(List<FindDeilyBean.DataBean.ListBean> bean, int status);
        void Date4(List<Find4Bean.DataBean> bean, int status);
        void Date5(List<Find5Bean.DataBean.RecordsBean> bean, int status);
        @Override
        void onError(Throwable throwable);

        void onSuccess(BaseObjectBean bean);
    }

    interface Presenter {
       void  pageListHuo(Context context, Map<String, String> heard, long id, long size);
        void addNum(Context context, Map<String, String> heard, long activityId, long userId);

        void addHua(Context context, Map<String, String> heard, long current, long size);

        void huas(Context context, Map<String, String> heard, long topicId, long userId);

        void getDaily(Context context, Map<String, String> heard, long userId);

        void guessLove(Context context, Map<String, String> heard);

        void getRankingList(Context context, Map<String, String> heard, long current, long size);

        void getRanking(Context context, Map<String, String> heard, long collectionId);

    }

}
