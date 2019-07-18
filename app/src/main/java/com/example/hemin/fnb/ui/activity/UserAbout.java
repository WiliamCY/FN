package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;
import com.example.hemin.fnb.ui.util.ProgressWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//跳转到官网链接
public class UserAbout extends BaseActivity {
    //    @BindView(R.id.back)
//    ImageView back;
//    @BindView(R.id.user_toolbar)
//    LinearLayout userToolbar;
    @BindView(R.id.webview)
    ProgressWebView mWebView;
    @BindView(R.id.lay_back)
    LinearLayout layBack;
    @BindView(R.id.title)
    TextView title;

    private String TAG = "UserAbout";


    @Override
    public int getLayoutId() {
        return R.layout.activity_userabout;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        mWebView = findViewById(R.id.webview);
        Intent intent = getIntent();
        String url = intent.getStringExtra("path");
        Log.d("findIntentUrl", url);
        mWebView.loadUrl(url);//加载网址
        mWebView.setFocusable(true);//设置有焦点
        title.setText("玩鉴");
        mWebView.setFocusableInTouchMode(true);//设置可触摸
    }


    // 设置回退监听
    // 5、覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (mWebView.canGoBack()) {
                mWebView.goBack(); //goBack()表示返回WebView的上一页面
                return true;
            } else {
                finish();
                return true;
            }

        }
        return false;
    }

    @OnClick({R.id.lay_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_back:
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
