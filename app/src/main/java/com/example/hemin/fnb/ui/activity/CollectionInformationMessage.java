package com.example.hemin.fnb.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.CollectionContract;
import com.example.hemin.fnb.ui.presenter.CollectionPresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

public class CollectionInformationMessage extends BaseMvpActivity<CollectionPresenter> implements CollectionContract.View {


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
    public void onError(Throwable throwable) {

    }
}
