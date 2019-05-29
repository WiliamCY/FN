package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;
import com.example.hemin.fnb.ui.util.Utils;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.entity.LocalImageInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Bannser extends BaseActivity {
    @BindView(R.id.welcome_first_btn)
    Button welcomeFirstBtn;
    @BindView(R.id.xbanner)
    XBanner xbanner;

    @Override
    public int getLayoutId() {
        return R.layout.welcome_banner_activity;
    }

    @Override
    public void initView() {
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);
        Utils.hideBottomUIMenu(this);
        initDate();
        initViews();

    }
    private void initDate(){
        if(Utils.booleanisLogin(this)){
            initViews();
        }else {
            Intent intent = new Intent(this,WelcomeLoading.class);
            startActivity(intent);
        }
    }

    public void initViews() {
        List<LocalImageInfo> localImageInfoList = new ArrayList<>();
        localImageInfoList.add(new LocalImageInfo(R.mipmap.p1));
        localImageInfoList.add(new LocalImageInfo(R.mipmap.p2));
        localImageInfoList.add(new LocalImageInfo(R.mipmap.p3));
        xbanner.setBannerData(localImageInfoList);
        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                ((ImageView) view).setImageResource(((LocalImageInfo) model).getXBannerUrl());
            }
        });
        xbanner.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == xbanner.getRealCount() - 1) {
//                    welcomeFirstBtn.setVisibility(View.VISIBLE);
                        Intent intent = new Intent(getApplicationContext(),PasswordActivity.class);
                        startActivity(intent);
                } else {
                    welcomeFirstBtn.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.xbanner, R.id.welcome_first_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.welcome_first_btn:
                 Intent intent = new Intent(getApplicationContext(), PasswordActivity.class);
                        startActivity(intent);
                break;
        }
    }
}
