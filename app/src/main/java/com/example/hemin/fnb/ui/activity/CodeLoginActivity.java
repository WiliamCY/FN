package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.LoginContract;
import com.example.hemin.fnb.ui.presenter.LoginPresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;
import com.example.hemin.fnb.ui.util.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeLoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.c_phone)
    EditText cPhone;
    @BindView(R.id.c_getCode)
    TextView cGetCode;
    @BindView(R.id.c_code)
    EditText cCode;
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
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.lay_back)
    LinearLayout layBack;
    @BindView(R.id.title)
    TextView title;
    private Utils.TimeCount timeCount;

    @Override
    public int getLayoutId() {
        return R.layout.activity_code_login;
    }

    @Override
    public void initView() {
        mPresenter = new LoginPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        title.setVisibility(View.GONE);

    }


    @OnClick({R.id.c_getCode, R.id.c_register, R.id.c_password, R.id.c_login_button, R.id.c_wechat, R.id.qq, R.id.alipay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.c_getCode:
                if (getPhone() == null || Utils.isPhoneNumber(getPhone()) == false) {

                    Utils.showMyToast(Toast.makeText(this, "请输入完整或者输入的手机格式有误", Toast.LENGTH_SHORT), 400);
                    return;
                }
                mPresenter.getCode(this, getPhone());
                timeCount = new Utils.TimeCount(60000, 1000, cGetCode);
                timeCount.start();
                break;
            case R.id.c_register:
                Intent register = new Intent(this, RegisterActivity.class);
                startActivity(register);
                break;
            case R.id.c_password:
                Intent password = new Intent(this, PasswordActivity.class);
                startActivity(password);
                break;
            case R.id.c_login_button:
                if (getCode() == null || getPhone() == null || Utils.isPhoneNumber(getPhone()) == false) {
                    Utils.showMyToast(Toast.makeText(this, "请输入完整或者输入的手机号格式错误", Toast.LENGTH_SHORT), 400);
                    return;
                }
                HashMap<String, String> paramsMap = new HashMap<>();
                paramsMap.put("mobile", getPhone());
                paramsMap.put("password", getCode());
                paramsMap.put("code", getCode());
                mPresenter.login(this, getPhone(), getCode());
                break;
            case R.id.c_wechat:
                break;
            case R.id.qq:
                break;
            case R.id.alipay:
                break;
        }
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {
        Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
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


    private String getPhone() {
        return cPhone.getText().toString().trim();
    }

    private String getCode() {
        return cCode.getText().toString().trim();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
