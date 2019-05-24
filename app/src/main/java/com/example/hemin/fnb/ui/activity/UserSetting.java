package com.example.hemin.fnb.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.UserContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;
import com.example.hemin.fnb.ui.presenter.RegisterPresenter;
import com.example.hemin.fnb.ui.presenter.UserPresenter;
import com.example.hemin.fnb.ui.util.AlertDialog;
import com.example.hemin.fnb.ui.util.Utils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserSetting extends BaseMvpActivity<UserPresenter> implements UserContract.View {


    @BindView(R.id.setting_button3)
    ImageView settingButton3;
    @BindView(R.id.setting_button4)
    ImageView settingButton4;
    @BindView(R.id.cis_exit_button)
    Button cisExitButton;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_setting;
    }

    @Override
    public void initView() {
        mPresenter = new UserPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        Utils.initLogin(this);
    }



    @OnClick({R.id.setting_button4, R.id.cis_exit_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_button4:
                break;
            case R.id.cis_exit_button:
                SharedPreferences sp = this.getSharedPreferences("userDate", Context.MODE_PRIVATE);
                String c = sp.getString("Authorization", "");
                    AlertDialog dialog = new AlertDialog(this).builder();
                    dialog.setGone().setTitle("提示")
                            .setMsg("确定退出")
                            .setNegativeButton("取消", null)
                            .setPositiveButton("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SharedPreferences sp = getSharedPreferences("userDate", Context.MODE_PRIVATE);
                                    String Authorization = sp.getString("Authorization", "");
                                    String tokenType = sp.getString("tokenType","");
                                    String userId = sp.getString("userId","");
                                    Map<String,String> map = new HashMap<>();
                                    map.put("Authorization",tokenType+Authorization);
                           mPresenter.exit(getApplicationContext(),map,userId);
                                }
                            }).show();


                break;
        }
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
}
