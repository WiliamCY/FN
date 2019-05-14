package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemin.fnb.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CodeLoginActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_login);
        ButterKnife.bind(this);


    }

    @OnClick({R.id.c_getCode, R.id.c_register, R.id.c_password, R.id.c_login_button, R.id.c_wechat, R.id.qq, R.id.alipay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.c_getCode:
                break;
            case R.id.c_register:
                Intent register = new Intent(this,RegisterActivity.class);
                startActivity(register);
                break;
            case R.id.c_password:
                Intent password = new Intent(this,PasswordActivity.class);
                startActivity(password);
                break;
            case R.id.c_login_button:
                break;
            case R.id.c_wechat:
                break;
            case R.id.qq:
                break;
            case R.id.alipay:
                break;
        }
    }
}
