package com.example.hemin.fnb.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.adapter.AppRaisaAdapter;
import com.example.hemin.fnb.ui.adapter.CollectionAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.AppraisaBean;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.ColletionBean;
import com.example.hemin.fnb.ui.contract.CollectionContract;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.presenter.CollectionPresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionInformationMessage extends BaseMvpActivity<CollectionPresenter> implements CollectionContract.View {
    @BindView(R.id.collect_recyclerview)
    RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.collection_information;
    }

    @Override
    public void initView() {
          mPresenter = new CollectionPresenter();
          mPresenter.attachView(this);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        SharedPreferences sp =this.getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String tokenType = sp.getString("tokenType", "");
        String Authorization = sp.getString("Authorization", "");
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", tokenType + Authorization);
        mPresenter.getColldetionMessage(this,map,id);
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {
        if(bean.getErrorCode() == 0) {
            Toast.makeText(this,bean.getErrorMsg(),Toast.LENGTH_SHORT).show();
        }
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(this);
    }

    @Override
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void ListDate(List<ColletionBean.DataBean.ListBean> bean) {

           initRecyclerView(bean);
    }
    private void initRecyclerView(final List<ColletionBean.DataBean.ListBean> bean) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        CollectionAdapter adapter = new CollectionAdapter(this,bean);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onError(Throwable throwable) {

    }
    public  void Date(Map map){

    }
}
