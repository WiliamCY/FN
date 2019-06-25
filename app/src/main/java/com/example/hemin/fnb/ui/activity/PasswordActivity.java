package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.view.KeyEvent;
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
import com.example.hemin.fnb.ui.contract.PwLoginContract;
import com.example.hemin.fnb.ui.presenter.PasswordPresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;
import com.example.hemin.fnb.ui.util.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PasswordActivity extends BaseMvpActivity<PasswordPresenter> implements PwLoginContract.View {
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
    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;
    @BindView(R.id.image_see)
    ImageView title6s;


    @Override
    public int getLayoutId() {
        return R.layout.activity_code_login;
    }

    @Override
    public void initView() {
        mPresenter = new PasswordPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        initViews();
    }

    private void initViews() {
        userToolbar.setVisibility(View.GONE);
        title1.setText("密码登录");
//        title4.setVisibility(View.GONE);
//        cGetCode.setVisibility(View.GONE);
//        cCode.setVisibility(View.GONE);
//        title5.setVisibility(View.VISIBLE);
//        cPasswords.setVisibility(View.VISIBLE);
//        cPassword.setText("忘记密码");
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }

    @OnClick({R.id.c_getCode, R.id.c_register, R.id.c_password, R.id.c_login_button, R.id.c_wechat, R.id.qq, R.id.alipay, R.id.image_see, R.id.title_6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.c_register:
                Intent register = new Intent(this, RegisterActivity.class);
                startActivity(register);
                break;
            case R.id.c_password:
                Intent password = new Intent(this, ForgetPasswordActivity.class);
                startActivity(password);
                break;
            case R.id.c_login_button:
                if (getPhone() == null || getPassword() == null || Utils.isPhoneNumber(getPhone()) == false) {

                    Utils.showMyToast(Toast.makeText(this, "请输入完整或者手机格式不正确", Toast.LENGTH_SHORT), 400);
                    return;
                }
                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("username", getPhone());
                hashMap.put("password", getPassword());
                mPresenter.pwLogn(this, Utils.RetrofitHead(hashMap));
                break;
            case R.id.c_wechat:
                break;
            case R.id.qq:
                break;
            case R.id.alipay:
                break;
            case R.id.c_getCode:

                break;
            case R.id.image_see:
                Utils.PwdShow(cPasswords,title6,title6s);

                break;
            case R.id.title_6:
                Utils.PwdHide(cPasswords,title6,title6s);
                break;
                }


    }

    private String getPhone() {
        return cPhone.getText().toString().trim();
    }

    private String getPassword() {
        return cPasswords.getText().toString().trim();
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {

        if (bean.getErrorCode() == 0) {

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
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
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            //do something.
            Utils.showMyToast(Toast.makeText(this, "请先登录或者注册", Toast.LENGTH_SHORT), 400);
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


}

