package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.CollectionInformationMessage;
import com.example.hemin.fnb.ui.adapter.AppRaisaAdapter;
import com.example.hemin.fnb.ui.adapter.MessageApdater;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.AppraisaBean;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.MessageBean1;
import com.example.hemin.fnb.ui.contract.MessageContract;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.presenter.MessagePresenter;
import com.example.hemin.fnb.ui.util.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MessageFragment extends BaseMvpFragment<MessagePresenter> implements MessageContract.View {
    @BindView(R.id.message_recyclerview)
    RecyclerView messageRecyclerview;
    Unbinder unbinder;
    private Map map = new HashMap();

    @Override
    protected void initView(View view) {
        mPresenter = new MessagePresenter();
        mPresenter.attachView(this);
        int index = this.getArguments().getInt("indexNumber");
        Log.d("MessageIndex", String.valueOf(index));
         map = Utils.getAuthorization(getActivity());
         initDate(index);


    }
    private  void initDate(int index){
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String userId = sp.getString("userId","");
                 if(index == 0){
                     mPresenter.getMaga(getActivity(),map,1,10,"1");
                 }else if(index == 1){
                     mPresenter.getTuiJian(getActivity(),map,1,1, Integer.parseInt(userId));
                 }else{
                     mPresenter.getTuiJian(getActivity(),map,1,10, Integer.parseInt(userId));
                 }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.message;
    }

    @Override
    public void Date(Object object,int index) {
       if(index == 1){

           initRecyclerview1((List<MessageBean1.DataBean.RecordsBean>) object);
       }
    }

    private void initRecyclerview1(final List<MessageBean1.DataBean.RecordsBean> bean) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        messageRecyclerview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
       MessageApdater adapter = new MessageApdater(getActivity(), bean);
        Log.d("beanDate", bean.toString());
        messageRecyclerview.setAdapter(adapter);

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
