package com.example.hemin.fnb.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MobileRegister extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;
    @BindView(R.id.cm_title1)
    TextView cmTitle1;
    @BindView(R.id.cm_title2)
    TextView cmTitle2;
    @BindView(R.id.cm_title3)
    TextView cmTitle3;
    @BindView(R.id.cm_title4)
    TextView cmTitle4;
    @BindView(R.id.c_login_button)
    Button cLoginButton;
    @BindView(R.id.title)
    TextView title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_mobile;
    }

    @Override
    public void initView() {
        title.setText("绑定手机号");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.title:
                break;
        }
    }
}
