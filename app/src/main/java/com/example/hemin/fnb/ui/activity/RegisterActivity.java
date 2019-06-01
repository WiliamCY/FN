package com.example.hemin.fnb.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.RegisterContract;
import com.example.hemin.fnb.ui.presenter.RegisterPresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;
import com.example.hemin.fnb.ui.util.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseMvpActivity<RegisterPresenter> implements RegisterContract.View {
    @BindView(R.id.title_1)
    TextView title1;
    @BindView(R.id.title_2)
    TextView title2;
    @BindView(R.id.title_3)
    TextView title3;
    @BindView(R.id.c_phone)
    EditText cPhone;
    @BindView(R.id.title_4)
    TextView title4;
    @BindView(R.id.c_getCode)
    TextView cGetCode;
    @BindView(R.id.c_code)
    EditText cCode;
    @BindView(R.id.title_5)
    TextView title5;
    @BindView(R.id.title_6)
    ImageView title6;
    @BindView(R.id.c_passwords)
    EditText cPasswords;
    @BindView(R.id.title_7)
    TextView title7;
    @BindView(R.id.title_8)
    ImageView title8;
    @BindView(R.id.c_user_password)
    EditText cUserPassword;
    @BindView(R.id.c_register)
    TextView cRegister;
    @BindView(R.id.c_password)
    TextView cPassword;
    @BindView(R.id.c_login_button)
    Button cLoginButton;
    @BindView(R.id.c_wechat)
    ImageView cWechat;
    @BindView(R.id.qq)
    ImageView qq;
    @BindView(R.id.alipay)
    ImageView alipay;
    @BindView(R.id.select)
    ImageView select;
    @BindView(R.id.user_message)
    TextView userMessage;
    @BindView(R.id.back)
    ImageView back;
    private Utils.TimeCount timeCount;

    @Override
    public int getLayoutId() {
        return R.layout.activity_code_login;

    }

    @Override
    public void initView() {
        mPresenter = new RegisterPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        initViews();

    }


    private void initViews() {
        title1.setText("注册");
        cLoginButton.setText("注册");
        qq.setVisibility(View.GONE);
        cWechat.setVisibility(View.GONE);
        alipay.setVisibility(View.GONE);
        select.setVisibility(View.VISIBLE);
        userMessage.setVisibility(View.VISIBLE);
        title5.setVisibility(View.VISIBLE);
        title6.setVisibility(View.VISIBLE);
        cPasswords.setVisibility(View.VISIBLE);
        cRegister.setVisibility(View.GONE);
        cPassword.setVisibility(View.GONE);
        title6.setBackgroundResource(R.mipmap.passwords);
    }

    @OnClick({R.id.c_getCode, R.id.title_6, R.id.c_login_button, R.id.user_message,R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.c_getCode:
                if (getMobile() == null || Utils.isPhoneNumber(getMobile()) == false) {
                    Utils.showMyToast(Toast.makeText(this, "请输入完整或者输入的手机格式有误", Toast.LENGTH_SHORT), 400);
                    return;
                }
                mPresenter.getCode(this, getMobile());
                timeCount = new Utils.TimeCount(60000, 1000, cGetCode);
                timeCount.start();
                break;
            case R.id.title_6:
                break;
            case R.id.c_login_button:
                if (getMobile() == null || Utils.isPhoneNumber(getMobile()) == false || getCode() == null || getPassword() == null) {
                    Utils.showMyToast(Toast.makeText(this, "请输入完整或者输入的手机格式有误", Toast.LENGTH_SHORT), 400);
                    return;
                }
                HashMap<String, String> paramsMap = new HashMap<>();
                paramsMap.put("mobile", getMobile());
                paramsMap.put("password", getPassword());
                paramsMap.put("code", getCode());
                mPresenter.register(this, Utils.RetrofitHead(paramsMap));
                break;
            case R.id.user_message:
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private String getMobile() {
        return cPhone.getText().toString().trim();
    }

    private String getCode() {
        return cCode.getText().toString().trim();
    }

    private String getPassword() {
        return cPasswords.getText().toString().trim();
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {
        if(bean.getErrorCode() == 0){

        }else {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}