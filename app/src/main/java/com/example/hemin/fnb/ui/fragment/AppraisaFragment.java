package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.CollectionInformationMessage;
import com.example.hemin.fnb.ui.adapter.AppRaisaAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.AppraisaBean;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.AppraisaContract;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.presenter.AppraisaPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AppraisaFragment extends BaseMvpFragment<AppraisaPresenter> implements AppraisaContract.View {

    public static final String EXTRA_TEXT = "extra_text";
    @BindView(R.id.apr_recylcerview)
    RecyclerView aprRecylcerview;
    Unbinder unbinder;
    @BindView(R.id.text)
    TextView text;
    Unbinder unbinder1;
    private AppRaisaAdapter adapter;
    private List<AppraisaBean.DataBean.RecordsBean> lists = new ArrayList<>();


    protected void initView(@Nullable Bundle savedInstanceState) {
        mPresenter = new AppraisaPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(getActivity());
        boolean status = this.getArguments().getBoolean("status");
        int indexNumber = this.getArguments().getInt("indexNumber");
        Log.d("indexNumbers", String.valueOf(indexNumber));
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String tokenType = sp.getString("tokenType", "");
        String Authorization = sp.getString("Authorization", "");
        String userId = sp.getString("userId", "");
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", tokenType + Authorization);
        if (status == true) {
            mPresenter.Appraisa(map, 1, 10, indexNumber, Long.parseLong(userId));


        } else {
            mPresenter.Appraisas(getActivity(), map, 1, 1, 1, Long.parseLong(userId));
        }

    }

    private void initRecyclerView(final List<AppraisaBean.DataBean.RecordsBean> bean) {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        aprRecylcerview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        adapter = new AppRaisaAdapter(getActivity(), bean);
        Log.d("beanDate", bean.toString());
        aprRecylcerview.setAdapter(adapter);
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

    }

//    @Override
//        protected void initView(View view) {
//        adapter = new AppRaisaAdapter();
//        mPresenter = new AppraisaPresenter();
//        mPresenter.attachView(this);
//        ButterKnife.bind(getActivity());
//        boolean status = getArguments().getBoolean("status");
//        int index = getArguments().getInt("index");
//        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
//        String tokenType = sp.getString("tokenType", "");
//        String Authorization = sp.getString("Authorization", "");
//        String userId = sp.getString("userId", "");
//        Map<String, String> map = new HashMap<>();
//        map.put("Authorization", tokenType + Authorization);
//        if (status == true) {
//            mPresenter.Appraisa( map, 10, 10, index, Long.parseLong(userId));
//                adapter.notifyDataSetChanged();
//
//        } else {
//            mPresenter.Appraisas( getActivity(),map, 1, 1, index, Long.parseLong(userId));
//        }
//    }


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
    public void Date(List<AppraisaBean.DataBean.RecordsBean> date) {
//        lists.clear();
//        lists.addAll(date);
//        adapter.notifyDataSetChanged();
        text.setText(date.toString());
        initRecyclerView(date);


    }


    @Override
    public void onError(Throwable throwable) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
