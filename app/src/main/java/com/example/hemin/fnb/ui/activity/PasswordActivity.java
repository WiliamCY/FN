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

public class PasswordActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code_login);
        ButterKnife.bind(this);
        initView();
    }
    private void initView(){
        title1.setText("密码登录");
        title4.setVisibility(View.GONE);
        cGetCode.setVisibility(View.GONE);
        cCode.setVisibility(View.GONE);
        title5.setVisibility(View.VISIBLE);
        title6.setVisibility(View.VISIBLE);
        cPasswords.setVisibility(View.VISIBLE);
        cPassword.setText("忘记密码");
    }

    @OnClick({R.id.c_register, R.id.c_password, R.id.c_login_button, R.id.c_wechat, R.id.qq, R.id.alipay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.c_register:
                Intent register = new Intent(this,RegisterActivity.class);
                startActivity(register);
                break;
            case R.id.c_password:
                Intent password = new Intent(this,ForgetPasswordActivity.class);
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
