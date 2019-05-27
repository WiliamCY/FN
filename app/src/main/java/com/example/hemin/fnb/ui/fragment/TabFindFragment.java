package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.adapter.FindAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.FindContract;
import com.example.hemin.fnb.ui.presenter.FindPresenter;
import com.example.hemin.fnb.ui.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TabFindFragment extends BaseMvpFragment<FindPresenter> implements FindContract.View {


    @BindView(R.id.find_recyclerview)
    RecyclerView findRecyclerview;
    private List<List<String>> imageURLS = new ArrayList<>();

    @Override
    protected void initView(View view) {
        mPresenter = new FindPresenter();
        mPresenter.attachView(this);
        initDate();
    }

    private void initDate() {
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        Map Authorization = Utils.getAuthorization(getActivity());
        String userId = sp.getString("userId", "");
        mPresenter.pageListHuo(getActivity(), Authorization, 1, 3);
        mPresenter.addHua(getActivity(), Authorization, 1, 1);
        mPresenter.getDaily(getActivity(), Authorization, Long.parseLong(userId));
        mPresenter.guessLove(getActivity(), Authorization);
        mPresenter.getRankingList(getActivity(), Authorization, 1, 1);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.find_view;
    }


    @Override
    public void onSuccess(BaseObjectBean bean) {
        Toast.makeText(getActivity(), bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Date(List<String> imsageURL, int status) {
        Log.d("imageUrls", imsageURL.toString());

            initRecyclerView(imsageURL, status);

    }

    private void initRecyclerView(final List<String> bean, int status) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        findRecyclerview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
         FindAdapter adapter = new FindAdapter(getActivity(), bean,status);
        Log.d("beanDate", bean.toString());
        findRecyclerview.setAdapter(adapter);
        int size = adapter.getItemCount();
        Log.d("findSize:", String.valueOf(size));


    }


    @Override
    public void onError(Throwable throwable) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
