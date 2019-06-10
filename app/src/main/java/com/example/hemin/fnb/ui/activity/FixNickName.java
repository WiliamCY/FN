package com.example.hemin.fnb.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FixNickName extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;
    @BindView(R.id.e1)
    EditText e1;
    @BindView(R.id.e2)
    EditText e2;
    @BindView(R.id.title)
    TextView title;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fix_nickname;
    }

    @Override
    public void initView() {

        int status = getIntent().getIntExtra("status", 0);
        if (status == 0) {
            e1.setVisibility(View.VISIBLE);
            e2.setVisibility(View.GONE);
            title.setText("修改昵称");
        } else {
            e1.setVisibility(View.GONE);
            e2.setVisibility(View.VISIBLE);
            title.setText("修改签名");
        }

    }


    @OnClick({R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
