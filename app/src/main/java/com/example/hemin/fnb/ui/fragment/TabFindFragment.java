package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.adapter.FindDailydapter;
import com.example.hemin.fnb.ui.adapter.FindHTAdapter;
import com.example.hemin.fnb.ui.adapter.FindHDAdapter;
import com.example.hemin.fnb.ui.adapter.FindRankdapter;
import com.example.hemin.fnb.ui.adapter.FindYLAdapter;
import com.example.hemin.fnb.ui.adapter.FindZaZhiSAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.DailyItem;
import com.example.hemin.fnb.ui.bean.Find2Bean;
import com.example.hemin.fnb.ui.bean.Find2Tiem;
import com.example.hemin.fnb.ui.bean.Find4Bean;
import com.example.hemin.fnb.ui.bean.Find5Bean;
import com.example.hemin.fnb.ui.bean.FindBean;
import com.example.hemin.fnb.ui.bean.FindDeilyBean;
import com.example.hemin.fnb.ui.bean.HDItem;
import com.example.hemin.fnb.ui.bean.MessageBean1;
import com.example.hemin.fnb.ui.bean.RankItem;
import com.example.hemin.fnb.ui.bean.YlItem;
import com.example.hemin.fnb.ui.bean.ZZSItem;
import com.example.hemin.fnb.ui.contract.FindContract;
import com.example.hemin.fnb.ui.interfaces.OnFindClickListener;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.presenter.FindPresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;
import com.example.hemin.fnb.ui.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

public class TabFindFragment extends BaseMvpFragment<FindPresenter> implements FindContract.View {
    @BindView(R.id.find_recyclerview)
    RecyclerView findRecyclerview;
    Unbinder unbinder;
    @BindView(R.id.c1)
    ConstraintLayout c1;
    Unbinder unbinder1;
    @BindView(R.id.find_logo)
    ImageView findLogo;
    private int sendPost = 0;
    private Map Authorization = new HashMap();
    private String userId;
    private MultiTypeAdapter adapter;
    private Items items;
    private List<FindBean> findBeanList = new ArrayList<>();
    private List<MessageBean1.DataBean.RecordsBean> zzList = null;
    private List<Find2Bean.DataBean.RecordsBean> find2List = null;
    private FindDeilyBean.DataBean find3List = null;
    private Find4Bean.DataBean find4List = null;
    private Find5Bean.DataBean.RecordsBean find5List = null;
    private FindDailydapter findDailydapter = new FindDailydapter();


    @Override
    protected void initView(View view) {
        mPresenter = new FindPresenter();
        mPresenter.attachView(this);
        initDate();
    }

    @Override
    public void lazyInitView(View view) {

    }

    private void initDate() {
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        Authorization = Utils.getAuthorization(getActivity());
        userId = sp.getString("userId", "");
        showLoading();
        mPresenter.pageListHuo(getActivity(), Authorization, 1, 3);


    }

    @Override
    protected int getLayoutId() {
        return R.layout.find_view;
    }


    @Override
    public void onSuccess(BaseObjectBean bean) {
        if (bean.getErrorCode() != 0) {
            Toast.makeText(getActivity(), bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        } else {
            sendPost++;
            initNetWork(sendPost);
        }

    }

    private void initNetWork(int sendPost) {
        Log.d("sendPost", String.valueOf(sendPost));

        if (sendPost == 1) {
            mPresenter.addHua(getActivity(), Authorization, 1, 1);
            return;
        } else if (sendPost == 2) {
            mPresenter.getDaily(getActivity(), Authorization, Long.parseLong(userId));
            return;
        } else if (sendPost == 3) {
            mPresenter.guessLove(getActivity(), Authorization);
            return;
        } else if (sendPost == 4) {
            mPresenter.getRankingList(getActivity(), Authorization, 1, 10);
            return;
        } else if (sendPost == 5) {
            mPresenter.getMaga(getActivity(), Authorization, 1, 10, "1", 1);
        }


    }

    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(getContext());
    }

    @Override
    public void hideLoading() {
       ProgressDialog.getInstance().dismiss();
    }


    @Override
    public void Date(Object bean, int status) {

        if (status == 1) {
            findBeanList = (List<FindBean>) bean;
        } else if (status == 2) {
            find2List = (List<Find2Bean.DataBean.RecordsBean>) bean;
        } else if (status == 3) {
            find3List = (FindDeilyBean.DataBean) bean;
        } else if (status == 4) {
            find4List = (Find4Bean.DataBean) bean;
        } else if (status == 5) {
            find5List = (Find5Bean.DataBean.RecordsBean) bean;
        } else if (status == 6) {
            zzList = (List<MessageBean1.DataBean.RecordsBean>) bean;
            initRecyclerView(findBeanList, find2List, find3List, find4List, find5List, zzList);
            hideLoading();

        }

    }


    private void initRecyclerView(final List<FindBean> bean, List<Find2Bean.DataBean.RecordsBean> find2List, FindDeilyBean.DataBean find3List, Find4Bean.DataBean find4List, Find5Bean.DataBean.RecordsBean find5List, List<MessageBean1.DataBean.RecordsBean> zz) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        findRecyclerview.setLayoutManager(layoutManager);
        adapter = new MultiTypeAdapter();
        adapter.register(HDItem.class, new FindHDAdapter());
        adapter.register(Find2Tiem.class, new FindHTAdapter());
        adapter.register(DailyItem.class, findDailydapter);
        adapter.register(YlItem.class, new FindYLAdapter());
        adapter.register(RankItem.class, new FindRankdapter());
        adapter.register(ZZSItem.class, new FindZaZhiSAdapter());
        findRecyclerview.setAdapter(adapter);
        items = new Items();
        for (int m = 0; m < bean.size(); m++) {
            HDItem item1 = new HDItem(bean.get(m));
            items.add(item1);
        }
        for (int a = 0; a < find2List.size(); a++) {
            Find2Tiem item2 = new Find2Tiem(find2List.get(a));
            items.add(item2);
        }
        DailyItem item3 = new DailyItem(find3List);
        items.add(item3);
        YlItem item4 = new YlItem(find4List);
        items.add(item4);
        RankItem item5 = new RankItem(find5List);
        items.add(item5);
        ZZSItem item6 = new ZZSItem(zz);
        items.add(item6);
        adapter.setItems(items);
        findDailydapter.setRecyclerItemClickListener(new OnFindClickListener() {
            @Override
            public void onItemClick(long id,TextView textView, int position) {
                Log.d("wdawda", String.valueOf(position));
                if (position == 0) {
                    mPresenter.getDCX(getActivity(),Authorization,1,id, Long.parseLong(userId),7);
                } else if (position == 1) {
                    mPresenter.getDCX(getActivity(),Authorization,2,id, Long.parseLong(userId),8);
                } else if (position == 2) {
                    mPresenter.getDCX(getActivity(),Authorization,3,id, Long.parseLong(userId),9);
                }
            }
        });

        adapter.notifyDataSetChanged();

    }


    @Override
    public void onError(Throwable throwable) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
        unbinder1.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
