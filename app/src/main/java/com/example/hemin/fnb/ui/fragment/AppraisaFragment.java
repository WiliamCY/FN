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
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.CollectionInformationMessage;
import com.example.hemin.fnb.ui.adapter.AppRaisaAdapter;
import com.example.hemin.fnb.ui.adapter.AppRaisasAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.AppraisaBean;
import com.example.hemin.fnb.ui.bean.AppraisasBean;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.AppraisaContract;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.presenter.AppraisaPresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AppraisaFragment extends BaseMvpFragment<AppraisaPresenter> implements AppraisaContract.View {

    @BindView(R.id.apr_recylcerview)
    RecyclerView aprRecylcerview;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    Unbinder unbinder;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private int pageIndex = 1;
    private int indexNumber;
    private Map<String, String> map = new HashMap<>();
    private  AppRaisaAdapter adapter = new AppRaisaAdapter();
    private  AppRaisasAdapter adapters = new AppRaisasAdapter();

    private String userId;


    private void initRecyclerView(final List<AppraisaBean.DataBean.RecordsBean> bean) {
        if(bean.size() == 0){
            refreshLayout.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
        }else {
            refreshLayout.setVisibility(View.VISIBLE);
            image.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        aprRecylcerview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
         adapter = new AppRaisaAdapter(getActivity(), bean);
        Log.d("beanDate", bean.toString());
        aprRecylcerview.setAdapter(adapter);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        adapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int Position, String path) {

            }

            @Override
            public void onItemClick(int Position) {
                Intent intent = new Intent(getActivity(), CollectionInformationMessage.class);
                Bundle bundle = new Bundle();
                bundle.putString("id", bean.get(Position).getCollectionId());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageIndex = 1;
                mPresenter.Appraisa(map, pageIndex, 10, indexNumber, Long.parseLong(userId),11);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageIndex++;
                Log.d("pageIndex", String.valueOf(pageIndex));
              mPresenter.Appraisa(map, pageIndex, 10, indexNumber, Long.parseLong(userId),12);
            }
        });
    }

    private void initRecyclerViews(final List<AppraisasBean.DataBean.RecordsBean> bean) {
        if(bean.size() == 0){
            refreshLayout.setVisibility(View.GONE);
            image.setVisibility(View.VISIBLE);
            title.setVisibility(View.VISIBLE);
        }else {
            refreshLayout.setVisibility(View.VISIBLE);
            image.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        aprRecylcerview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        adapters = new AppRaisasAdapter(getActivity(), bean);
        Log.d("beanDate", bean.toString());
        aprRecylcerview.setAdapter(adapters);
        refreshLayout.setRefreshHeader(new ClassicsHeader(getActivity()));
        adapters.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int Position, String path) {

            }

            @Override
            public void onItemClick(int Position) {

            }
        });
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageIndex = 1;
                mPresenter.Appraisas(getActivity(), map, pageIndex, 10, indexNumber, Long.parseLong(userId),21);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageIndex++;
                Log.d("pageIndex", String.valueOf(pageIndex));
                mPresenter.Appraisas(getActivity(), map, pageIndex, 10, indexNumber, Long.parseLong(userId),22);
            }
        });

    }

//    @Override
//    protected void initView(View view) {
//        mPresenter = new AppraisaPresenter();
//        mPresenter.attachView(this);
//        ButterKnife.bind(getActivity());
//        boolean status = this.getArguments().getBoolean("status");
//         indexNumber = this.getArguments().getInt("indexNumber");
//        Log.d("indexNumbers", String.valueOf(indexNumber));
//        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
//        String tokenType = sp.getString("tokenType", "");
//        String Authorization = sp.getString("Authorization", "");
//         userId = sp.getString("userId", "");
//         map = new HashMap<>();
//        map.put("Authorization", tokenType + Authorization);
//        if (status == true) {
//            mPresenter.Appraisa(map, 1, 10, indexNumber, Long.parseLong(userId),1);
//
//
//        } else {
//            mPresenter.Appraisas(getActivity(), map, 1, 10, indexNumber, Long.parseLong(userId),2);
//        }
//
//    }

    @Override
    public void lazyInitView(View view) {
        mPresenter = new AppraisaPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(getActivity());
        boolean status = this.getArguments().getBoolean("status");
        indexNumber = this.getArguments().getInt("indexNumber");
        Log.d("indexNumbers", String.valueOf(indexNumber));
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String tokenType = sp.getString("tokenType", "");
        String Authorization = sp.getString("Authorization", "");
        userId = sp.getString("userId", "");
        map = new HashMap<>();
        map.put("Authorization", tokenType + Authorization);
        if (status == true) {
            mPresenter.Appraisa(map, 1, 10, indexNumber, Long.parseLong(userId),1);


        } else {
            mPresenter.Appraisas(getActivity(), map, 1, 10, indexNumber, Long.parseLong(userId),2);
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
    public void Date(Object o, int status) {

        if (status == 1) {
            aprRecylcerview.setVisibility(View.VISIBLE);
            image.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            initRecyclerView((List<AppraisaBean.DataBean.RecordsBean>) o);
        } else if (status == 2) {
            aprRecylcerview.setVisibility(View.VISIBLE);
            image.setVisibility(View.GONE);
            title.setVisibility(View.GONE);
            initRecyclerViews((List<AppraisasBean.DataBean.RecordsBean>) o);
        }else if(status == 11){
            adapter.setData((List<AppraisaBean.DataBean.RecordsBean>) o);
            refreshLayout.finishRefresh(100);
        }else if(status == 12){
            if(((List<AppraisaBean.DataBean.RecordsBean>) o).size() == 0){
                Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
            }else {
                adapter.addtData((List<AppraisaBean.DataBean.RecordsBean>) o);
            }
            refreshLayout.finishLoadMore(100);
        }else if(status == 21){
           adapters.setData((List<AppraisasBean.DataBean.RecordsBean>) o);
            refreshLayout.finishRefresh(100);
        }else if(status == 22){
            if(((List<AppraisasBean.DataBean.RecordsBean>) o).size() == 0){
                Toast.makeText(getActivity(), "暂无数据", Toast.LENGTH_SHORT).show();
            }else {
                adapters.addtData((List<AppraisasBean.DataBean.RecordsBean>) o);
            }
            refreshLayout.finishLoadMore(100);
        }

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
