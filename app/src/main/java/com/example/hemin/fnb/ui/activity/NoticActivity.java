package com.example.hemin.fnb.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NoticActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;
    @BindView(R.id.titl1)
    TextView titl1;
    @BindView(R.id.titl2)
    TextView titl2;
    @BindView(R.id.titl3)
    TextView titl3;
    @BindView(R.id.titl4)
    TextView titl4;
    @BindView(R.id.titl5)
    TextView titl5;
    @BindView(R.id.titl6)
    TextView titl6;
    @BindView(R.id.titl7)
    TextView titl7;
    @BindView(R.id.lay_back)
    LinearLayout layBack;

    @Override
    public int getLayoutId() {
        return R.layout.activity_notice_setting;
    }

    @Override
    public void initView() {
        title.setText("通知设置");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.lay_back, R.id.title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_back:
                finish();
                break;
            case R.id.title:

                break;
        }
    }
}
