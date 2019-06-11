package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.TaskBigImgActivity;
import com.example.hemin.fnb.ui.adapter.RelaseApdater;
import com.example.hemin.fnb.ui.adapter.TranslateAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.GuanZhuBean;
import com.example.hemin.fnb.ui.bean.MessageImageBean;
import com.example.hemin.fnb.ui.bean.ReleaseBean;
import com.example.hemin.fnb.ui.contract.WodeQuanziContract;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListeners;
import com.example.hemin.fnb.ui.presenter.WoDoQuanZiPresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;
import com.example.hemin.fnb.ui.util.Utils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

public class QuanZiFragment extends BaseMvpFragment<WoDoQuanZiPresenter> implements WodeQuanziContract.View {

    @BindView(R.id.apr_recylcerview)
    XRecyclerView aprRecylcerview;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    Unbinder unbinder;
    @BindView(R.id.logo)
    ImageView logos;
    @BindView(R.id.logo_title)
    TextView logoTitle;

    private ArrayList<String> recordPaths = new ArrayList<>();
    private int finderid;
    private String userId;
    private TranslateAdapter adapter = new TranslateAdapter();
    private Map token = new HashMap();
    private TextView t1;

    private int pageIndex = 1;  //当前请求页
    private int index;


    public static QuanZiFragment getInstance(String i) {
        Log.d("indexNumbersssIIII", String.valueOf(i));
        QuanZiFragment quanZiFragment = new QuanZiFragment();
        Bundle bundle = new Bundle();
        if (i == "我的关注") {
            bundle.putInt("index", 0);
        } else {
            bundle.putInt("index", 1);
        }
        quanZiFragment.setArguments(bundle);
        return quanZiFragment;
    }

    @Override
    protected void initView(View view) {
        mPresenter = new WoDoQuanZiPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(getActivity());
        index = this.getArguments().getInt("index");
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        token = Utils.getAuthorization(getActivity());
        userId = sp.getString("userId", "");
        if (index == 0) {//我的关注
            mPresenter.myGuanzhu(getActivity(), token, pageIndex, 10, Long.parseLong(userId));
        } else {///我的发布
            mPresenter.myFaBu(getActivity(), token, pageIndex, 10, Long.parseLong(userId));
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
        if (index == 0) {
            initAdapterGZ((List<GuanZhuBean.DataBean.RecordsBean>) object);
        } else if (index == 1) {
            initAdapterFB((List<ReleaseBean.DataBean.RecordsBean>) object);
        } else {
            t1.setText("未关注");
        }

    }

    @Override
    public void DateUserId(Object object, String userId, String content, String userUrl, String nickName) {

        Images((List<MessageImageBean.DataBean.ImagesBean>) object, userId, content, userUrl, nickName);

    }

    private void Images(List<MessageImageBean.DataBean.ImagesBean> object, String userId, String content, String userUrl, String nickName) {
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
        startActivity(imgIntent);

    }

    private void initAdapterGZ(final List<GuanZhuBean.DataBean.RecordsBean> beans) {
        if (beans.size() == 0) {
            logos.setVisibility(View.VISIBLE);
            logos.setBackgroundResource(R.mipmap.guanzhu);
            logoTitle.setVisibility(View.VISIBLE);
            logoTitle.setText("快点去关注些什么吧～");
            aprRecylcerview.setVisibility(View.GONE);
        }
        Log.d("TransSize:", String.valueOf(beans.size()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        aprRecylcerview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new TranslateAdapter(getActivity(), beans);
        aprRecylcerview.setAdapter(adapter);
//        adapter.addtData(beans);
        aprRecylcerview.setLoadingMoreEnabled(true);
        aprRecylcerview.setPullRefreshEnabled(true);
        aprRecylcerview.setLoadingMoreProgressStyle(ProgressStyle.Pacman);
        aprRecylcerview.getDefaultRefreshHeaderView().setRefreshTimeVisible(true);

        adapter.setRecyclerItemClickListeners(new OnRecyclerItemClickListeners() {
            @Override
            public void onItemClick(int Position, TextView textView) {
                String userId = beans.get(Position).getFuId();
                Log.d("XrecyclerviewUserId",userId);
                mPresenter.Remove(getActivity(), token, userId);
                t1 = textView;
            }
        });
        aprRecylcerview.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                adapter.notifyDataSetChanged();
                mPresenter.myGuanzhu(getActivity(), token, 1, 10, Long.parseLong(userId));
                Toast.makeText(getActivity(), "刷新完成", Toast.LENGTH_SHORT).show();
                aprRecylcerview.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                pageIndex++;
                mPresenter.myFaBu(getActivity(), token, pageIndex, 10, Long.parseLong(userId));
                Toast.makeText(getActivity(), "加载完成", Toast.LENGTH_SHORT).show();
                aprRecylcerview.refreshComplete();

            }
        });
    }

    private void initAdapterFB(final List<ReleaseBean.DataBean.RecordsBean> beans) {
        if (beans.size() == 0) {
            logos.setVisibility(View.VISIBLE);
            logos.setBackgroundResource(R.mipmap.relaease);
            logoTitle.setVisibility(View.VISIBLE);
            logoTitle.setText("您还什么都没有发布呢～");
            aprRecylcerview.setVisibility(View.GONE);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        aprRecylcerview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        RelaseApdater adapter = new RelaseApdater(getActivity(), beans);
        Log.d("beanDate", beans.toString());
        aprRecylcerview.setAdapter(adapter);
        recordPaths.clear();

        adapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int Position, String path) {

            }

            @Override
            public void onItemClick(int position) {
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
