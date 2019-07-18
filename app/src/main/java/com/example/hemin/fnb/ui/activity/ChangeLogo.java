package com.example.hemin.fnb.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hemin.fnb.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeLogo extends AppCompatActivity {

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.lay_back)
    LinearLayout layBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;
    @BindView(R.id.change_user_logo)
    ImageView changeUserLogo;
    @BindView(R.id.change_button1)
    TextView changeButton1;
    @BindView(R.id.change_button2)
    TextView changeButton2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_logo);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.lay_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
            case R.id.lay_back:
                finish();
                break;
        }
    }
}
