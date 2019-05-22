package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.adapter.AppRaisaAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.AppraisaBean;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.AppraisaContract;
import com.example.hemin.fnb.ui.presenter.AppraisaPresenter;
import com.uber.autodispose.AutoDisposeConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AppraisaFragment extends BaseMvpFragment<AppraisaPresenter> implements AppraisaContract.View {


    @BindView(R.id.apr_recylcerview)
    RecyclerView aprRecylcerview;
    Unbinder unbinder;
    private AppRaisaAdapter adapter = new AppRaisaAdapter();
    private List<AppraisaBean.DataBean.RecordsBean> dates = new ArrayList<>();

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {
        mPresenter = new AppraisaPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(getActivity());
        boolean status = getArguments().getBoolean("status");
        int index = getArguments().getInt("index");
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String tokenType = sp.getString("tokenType", "");
        String Authorization = sp.getString("Authorization", "");
        String userId = sp.getString("userId", "");
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", tokenType + Authorization);
        if (status == true) {
            mPresenter.Appraisa( map, 1, 1, index,9);
            initRecyclerView(dates);

        } else {
            mPresenter.Appraisas( getActivity(),map, 1, 1, index, Long.parseLong(userId));
            initRecyclerView(dates);
        }
    }
    private void initRecyclerView(List<AppraisaBean.DataBean.RecordsBean> bean){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        aprRecylcerview.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        adapter = new AppRaisaAdapter(getActivity(), bean);
        aprRecylcerview.setAdapter(adapter);
    }

    @Override
    protected void initView(View view) {
        mPresenter = new AppraisaPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(getActivity());
        boolean status = getArguments().getBoolean("status");
        int index = getArguments().getInt("index");
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String tokenType = sp.getString("tokenType", "");
        String Authorization = sp.getString("Authorization", "");
        String userId = sp.getString("userId", "");
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", tokenType + Authorization);
        if (status == true) {
            mPresenter.Appraisa( map, 1, 1, index, 9);
            initRecyclerView(dates);

        } else {
            mPresenter.Appraisas( getActivity(),map, 1, 1, index, Long.parseLong(userId));
            initRecyclerView(dates);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_appraisa_recyclerview;
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public List<AppraisaBean.DataBean.RecordsBean> Date(List<AppraisaBean.DataBean.RecordsBean> date) {
        this.dates = date;
        return dates;
    }


    @Override
    public void onError(Throwable throwable) {

    }



//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
