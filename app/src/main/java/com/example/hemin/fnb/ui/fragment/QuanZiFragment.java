package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.TaskBigImgActivity;
import com.example.hemin.fnb.ui.adapter.RelaseApdater;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.MessageImageBean;
import com.example.hemin.fnb.ui.bean.ReleaseBean;
import com.example.hemin.fnb.ui.contract.WodeQuanziContract;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.presenter.WoDoQuanZiPresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;
import com.example.hemin.fnb.ui.util.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class QuanZiFragment extends BaseMvpFragment<WoDoQuanZiPresenter> implements WodeQuanziContract.View {
    Unbinder unbinder;
    @BindView(R.id.apr_recylcerview)
    RecyclerView aprRecylcerview;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.image_1)
    ImageView image1;
    @BindView(R.id.title_1)
    TextView title1;
    @BindView(R.id.y1)
    LinearLayout y1;

    private ArrayList<String> recordPaths = new ArrayList<>();
    private List<ReleaseBean.DataBean.RecordsBean> dates = new ArrayList();
    private RelaseApdater adapters = new RelaseApdater(getActivity(), dates);
    private int finderid;
    private String userId;
    private Map token = new HashMap();
    private TextView t1;

    private int pageIndex = 1;  //当前请求页
    private int pageIndexs = 1;  //当前请求页
    private int index;


    public static QuanZiFragment getInstance(String i) {
        Log.d("indexNumbersssIIII", String.valueOf(i));
        QuanZiFragment quanZiFragment = new QuanZiFragment();
        Bundle bundle = new Bundle();
        if (i == "发布") {
            bundle.putInt("index", 0);
        } else if (i == "收藏") {
            bundle.putInt("index", 1);

        }

        quanZiFragment.setArguments(bundle);
        return quanZiFragment;
    }

    @Override
    public void lazyInitView(View view) {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        mPresenter = new WoDoQuanZiPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(getActivity());

        index = this.getArguments().getInt("index");
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        token = Utils.getAuthorization(getActivity());
        userId = sp.getString("userId", "");
        mPresenter.getFansAndFocusSum(getActivity(), token);
        if (index == 0) {//我的发布
            mPresenter.myFaBu(getActivity(), token, pageIndex, 10, Long.parseLong(userId), 2);
        } else {///我的收藏
            mPresenter.MyCollect(getActivity(), token, pageIndexs, 10, Long.parseLong(userId), 1);
        }


    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_appraisa_recyclerview;
    }

    @Override
    public void Date(Object object, int index) {
        if (index == 1) {
            initAdapterFB((List<ReleaseBean.DataBean.RecordsBean>) object, index);
        } else if (index == 2) {
            initAdapterFB((List<ReleaseBean.DataBean.RecordsBean>) object, index);
        } else if (index == 11) {
            adapters.setDatas((List<ReleaseBean.DataBean.RecordsBean>) object);
            pageIndexs = 1;
            mPresenter.MyCollect(getActivity(), token, pageIndexs, 10, Long.parseLong(userId), 12);
        } else if (index == 22) {
            if (((List<ReleaseBean.DataBean.RecordsBean>) object).size() == 0) {
                Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
            } else {
                adapters.addtDatas((List<ReleaseBean.DataBean.RecordsBean>) object);
            }
            refreshLayout.finishLoadMore(100);
        } else if(index == 12){
//            adapter.setDatasE((List<ReleaseBeanS.DataBean.RecordsBean>) object);
            adapters.setDatas((List<ReleaseBean.DataBean.RecordsBean>) object);
            EventBus.getDefault().post(44,"dwd");
        }else {
            t1.setText("未关注");
        }
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
    public void DateUserId(Object object, String userId, String content, String userUrl, String nickName, String isGiveNum, String isFocus) {

        Images((List<MessageImageBean.DataBean.ImagesBean>) object, userId, content, userUrl, nickName, isGiveNum, isFocus);

    }

    private void Images(List<MessageImageBean.DataBean.ImagesBean> object, String userId, String content, String userUrl, String nickName, String isGiveNum, String isFocus) {
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
        imgIntent.putExtra("isFocus", isFocus);
        imgIntent.putExtra("StringContent", content);
        imgIntent.putExtra("userUrl", userUrl);
        imgIntent.putExtra("nickName", nickName);
        imgIntent.putExtra("isGiveNum", isGiveNum);
        startActivity(imgIntent);

    }

    private void initAdapterFB(final List<ReleaseBean.DataBean.RecordsBean> beans, final int index) {
        if (beans.size() == 0) {
            aprRecylcerview.setVisibility(View.GONE);
            image1.setVisibility(View.VISIBLE);
            title1.setText("暂无数据");
            title1.setVisibility(View.VISIBLE);
        } else {
            aprRecylcerview.setVisibility(View.VISIBLE);
            image1.setVisibility(View.GONE);
            title1.setVisibility(View.GONE);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        aprRecylcerview.setLayoutManager(layoutManager);
         adapters = new RelaseApdater(getActivity(), beans);
        aprRecylcerview.setAdapter(adapters);
        refreshLayout.setEnableRefresh(false);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

                if (index == 2) {
                    pageIndex++;
                    mPresenter.myFaBu(getActivity(), token, pageIndex, 10, Long.parseLong(userId), 22);
                } else if (index == 1) {
                    pageIndexs++;
                    mPresenter.MyCollect(getActivity(), token, pageIndexs, 10, Long.parseLong(userId), 22);
                }
            }
        });
        adapters.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int Position, String path) {

            }

            @Override
            public void onItemClick(int Position) {
                finderid = beans.get(Position).getFriendId();
                mPresenter.getFidner(getActivity(), token, finderid, Integer.parseInt(userId));
            }
        });
//        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                 if(index == 2){
//                     pageIndex = 1;
//                     mPresenter.myFaBu(getActivity(), token, pageIndex, 10, Long.parseLong(userId), 11);
//                 }else if(index == 1){
//                     pageIndexs = 1;
//                     mPresenter.MyCollect(getActivity(), token, pageIndexs, 10, Long.parseLong(userId), 11);
//                 }
//            }
//        });
//
//        adapters.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                finderid = beans.get(position).getFriendId();
//                mPresenter.getFidner(getActivity(), token, finderid, Integer.parseInt(userId));
//            }
//        });


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

    @Subscribe(id = 43)
    public void myIndex(String status) {
        pageIndex = 1;
        mPresenter.myFaBu(getActivity(), token, pageIndex, 10, Long.parseLong(userId), 11);



    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }


}
