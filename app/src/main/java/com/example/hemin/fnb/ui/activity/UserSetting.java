package com.example.hemin.fnb.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.UserContract;
import com.example.hemin.fnb.ui.presenter.UserPresenter;
import com.example.hemin.fnb.ui.util.AlertDialog;

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
    @BindView(R.id.fix_password)
    RelativeLayout fixPassword;
    @BindView(R.id.button1)
    TextView button1;
    @BindView(R.id.button2)
    TextView button2;
    //    @BindView(R.id.header_left_img)
//    ImageView headerLeftImg;
    @BindView(R.id.r1)
    RelativeLayout r1;
    @BindView(R.id.about)
    RelativeLayout about;
    @BindView(R.id.mobileRegister)
    RelativeLayout mobileRegister;
    @BindView(R.id.notic)
    RelativeLayout notic;
    @BindView(R.id.lay_back)
    LinearLayout layBack;
    @BindView(R.id.title)
    TextView title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_setting;
    }

    @Override
    public void initView() {

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        mPresenter = new UserPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        title.setText("设置");

    }


    @OnClick({R.id.setting_button4, R.id.cis_exit_button, R.id.fix_password, R.id.button2, R.id.lay_back, R.id.r1, R.id.about, R.id.mobileRegister, R.id.notic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting_button4:
                break;
            case R.id.cis_exit_button:
                AlertDialog dialog = new AlertDialog(this).builder();
                dialog.setGone().setTitle("提示")
                        .setNegativeButton("取消", null)
                        .setMsg("是否退出")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                SharedPreferences sp = getSharedPreferences("userDate", Context.MODE_PRIVATE);
                                String Authorization = sp.getString("Authorization", "");
                                String tokenType = sp.getString("tokenType", "");
                                String userId = sp.getString("userId", "");
                                Map<String, String> map = new HashMap<>();
                                map.put("Authorization", tokenType + Authorization);
                                mPresenter.exit(getApplicationContext(), map, userId);
                            }
                        }).show();


                break;
            case R.id.fix_password:
                Intent intent = new Intent(this, ForgetPasswordActivity.class);
                startActivity(intent);
                break;
            case R.id.button2:
//                Intent intent1 = new Intent(this,ChangeUserNoticSetting.class);
//                startActivity(intent1);
                break;
            case R.id.lay_back:
                finish();
                break;
            case R.id.r1:
                Intent intent1 = new Intent(this, ActivityUserMessage.class);
                startActivity(intent1);
                break;
            case R.id.about:
                Intent about = new Intent(this, AboutActivity.class);
                startActivity(about);
                break;
            case R.id.mobileRegister:
                Intent reregisterM = new Intent(this, MobileRegister.class);
                startActivity(reregisterM);
                break;
            case R.id.notic:
                Intent notic = new Intent(this, NoticActivity.class);
                startActivity(notic);
        }

    }

    @Override
    public void onSuccess(BaseObjectBean bean) {
        if (bean.getErrorCode() == 0) {
            SharedPreferences sharedPreferences = this.getSharedPreferences("userDate", MODE_PRIVATE);
            sharedPreferences.edit().clear().commit();

            Intent intent1 = new Intent(this, PasswordActivity.class);
            startActivity(intent1);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
