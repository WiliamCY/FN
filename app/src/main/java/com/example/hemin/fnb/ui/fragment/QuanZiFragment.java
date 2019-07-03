package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.TaskBigImgActivity;
import com.example.hemin.fnb.ui.activity.UserAbout;
import com.example.hemin.fnb.ui.adapter.RelaseApdater;
import com.example.hemin.fnb.ui.adapter.TranslateAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.GuanZhuBean;
import com.example.hemin.fnb.ui.bean.MessageBean2;
import com.example.hemin.fnb.ui.bean.MessageImageBean;
import com.example.hemin.fnb.ui.bean.ReleaseBean;
import com.example.hemin.fnb.ui.contract.WodeQuanziContract;
import com.example.hemin.fnb.ui.presenter.WoDoQuanZiPresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;
import com.example.hemin.fnb.ui.util.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class QuanZiFragment extends BaseMvpFragment<WoDoQuanZiPresenter> implements WodeQuanziContract.View {
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    Unbinder unbinder;
    @BindView(R.id.logo)
    ImageView logos;
    @BindView(R.id.logo_title)
    TextView logoTitle;
    @BindView(R.id.apr_recylcerview)
    RecyclerView aprRecylcerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private ArrayList<String> recordPaths = new ArrayList<>();
    private List<ReleaseBean.DataBean.RecordsBean> dates = new ArrayList();
    private List<GuanZhuBean.DataBean.RecordsBean> data = new ArrayList<>();
    private TranslateAdapter adapter = new TranslateAdapter(R.layout.gz_adapter, data);
    private RelaseApdater adapters = new RelaseApdater(R.layout.message_adapters, dates);
    private int finderid;
    private String userId;
    //    private TranslateAdapter adapter = new TranslateAdapter();
//    private RelaseApdater adapters = new RelaseApdater();
    private Map token = new HashMap();
    private TextView t1;

    private int pageIndex = 1;  //当前请求页
    private int index;


    public static QuanZiFragment getInstance(String i) {
        Log.d("indexNumbersssIIII", String.valueOf(i));
        QuanZiFragment quanZiFragment = new QuanZiFragment();
        Bundle bundle = new Bundle();
        if (i == "发布") {
            bundle.putInt("index", 0);
        } else if (i == "收藏") {
              bundle.putInt("index",1);
        } else if (i == "想要") {

        }
//        if (i == "关注") {
//            bundle.putInt("index", 0);
//        } else {
//   bundle.putInt("index", 1);
//        }
        quanZiFragment.setArguments(bundle);
        return quanZiFragment;
    }

    @Override
    public void lazyInitView(View view) {
        mPresenter = new WoDoQuanZiPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(getActivity());
        index = this.getArguments().getInt("index");
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        token = Utils.getAuthorization(getActivity());
        userId = sp.getString("userId", "");
        if (index == 0) {//我的发布
           mPresenter.myFaBu(getActivity(), token, pageIndex, 10, Long.parseLong(userId), 2);
        } else if(index == 1) {///我的收藏
       mPresenter.MyCollect(getActivity(),token,pageIndex,10,Long.parseLong(userId),0);
        }


    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_appraisa_recyclerview;
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {
        if (bean.getErrorCode() != 0) {
            Toast.makeText(getActivity(), bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(getActivity());
    }

    @Override
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void Date(Object object, int index) {
        if (index == 1) {
            initAdapterGZ((List<GuanZhuBean.DataBean.RecordsBean>) object);
        } else if (index == 2) {
            initAdapterFB((List<ReleaseBean.DataBean.RecordsBean>) object);
        } else if (index == 11) {
            adapter.replaceData((List<GuanZhuBean.DataBean.RecordsBean>) object);
            refreshLayout.finishRefresh(100);
        } else if (index == 12) {
            if (((List<MessageBean2.DataBean.RecordsBean>) object).size() == 0) {
                Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
            } else {
                adapter.addData((List<GuanZhuBean.DataBean.RecordsBean>) object);
            }
            refreshLayout.finishLoadMore(100);
        } else if (index == 21) {
            adapters.replaceData((List<ReleaseBean.DataBean.RecordsBean>) object);
            refreshLayout.finishRefresh(100);
        } else if (index == 22) {
            if (((List<MessageBean2.DataBean.RecordsBean>) object).size() == 0) {
                Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
            } else {
                adapters.addData((List<ReleaseBean.DataBean.RecordsBean>) object);
            }
            refreshLayout.finishLoadMore(100);
        } else {
            t1.setText("未关注");

        }
    }

    @Override
    public void DateUserId(Object object, String userId, String content, String userUrl, String nickName, String isGiveNum) {

        Images((List<MessageImageBean.DataBean.ImagesBean>) object, userId, content, userUrl, nickName, isGiveNum);

    }

    private void Images(List<MessageImageBean.DataBean.ImagesBean> object, String userId, String content, String userUrl, String nickName, String isGiveNum) {
        recordPaths.clear();
        for (int i = 0; i < object.size(); i++) {
            String path = object.get(i).getImagesUrl();
            Log.d("messageAdapter3", path);
            recordPaths.add(path);
        }

        Log.d("messaePaths", recordPaths.toString());
        Intent imgIntent = new Intent(getActivity(), TaskBigImgActivity.class);
        imgIntent.putStringArrayListExtra("paths", recordPaths);
        imgIntent.putExtra("title", "关注");
        imgIntent.putExtra("position", object.size());
        imgIntent.putExtra("finderid", finderid);
        imgIntent.putExtra("userId", userId);
        imgIntent.putExtra("StringContent", content);
        imgIntent.putExtra("userUrl", userUrl);
        imgIntent.putExtra("nickName", nickName);
        imgIntent.putExtra("isGiveNum", isGiveNum);
        startActivity(imgIntent);

    }

    private void initAdapterGZ(final List<GuanZhuBean.DataBean.RecordsBean> beans) {
        if (beans.size() == 0) {
            refreshLayout.setVisibility(View.GONE);
            logos.setVisibility(View.VISIBLE);
            logos.setBackgroundResource(R.mipmap.guanzhu);
            logoTitle.setVisibility(View.VISIBLE);
            logoTitle.setText("快点去关注些什么吧～");

        }
        Log.d("TransSize:", String.valueOf(beans.size()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        aprRecylcerview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new TranslateAdapter(R.layout.gz_adapter, beans);
        aprRecylcerview.setAdapter(adapter);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageIndex = 1;
                mPresenter.myGuanzhu(getActivity(), token, pageIndex, 10, Long.parseLong(userId), 11);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageIndex++;
                Log.d("pageIndex", String.valueOf(pageIndex));
                mPresenter.myGuanzhu(getActivity(), token, pageIndex, 10, Long.parseLong(userId), 12);
            }
        });

        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String userId = beans.get(position).getFuId();
                Log.d("XrecyclerviewUserId", userId);
                mPresenter.Remove(getActivity(), token, userId);
                t1 = (TextView) adapter.getViewByPosition(aprRecylcerview, position, R.id.t_3);

            }
        });

    }

    private void initAdapterFB(final List<ReleaseBean.DataBean.RecordsBean> beans) {
        if (beans.size() == 0) {
            logos.setVisibility(View.VISIBLE);
            logos.setBackgroundResource(R.mipmap.relaease);
            logoTitle.setVisibility(View.VISIBLE);
            logoTitle.setText("您还什么都没有发布呢～");
            refreshLayout.setVisibility(View.GONE);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        aprRecylcerview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        RelaseApdater adapters = new RelaseApdater(R.layout.message_adapters, beans);
        Log.d("beanDate", beans.toString());
        aprRecylcerview.setAdapter(adapters);
        recordPaths.clear();
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageIndex = 1;
                mPresenter.myFaBu(getActivity(), token, pageIndex, 10, Long.parseLong(userId), 21);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageIndex++;
                Log.d("pageIndex", String.valueOf(pageIndex));
                mPresenter.myFaBu(getActivity(), token, pageIndex, 10, Long.parseLong(userId), 22);
            }
        });

        adapters.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                finderid = beans.get(position).getFriendId();
                mPresenter.getFidner(getActivity(), token, finderid, Integer.parseInt(userId));
            }
        });

    }

    @Override
    public void onError(Throwable throwable) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
