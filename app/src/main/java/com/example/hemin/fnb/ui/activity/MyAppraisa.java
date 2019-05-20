package com.example.hemin.fnb.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;
import com.example.hemin.fnb.ui.util.ExamplePagerAdapter;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAppraisa extends BaseActivity {

    @BindView(R.id.view_pager2)
    ViewPager viewPager2;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.title2)
    TextView title2;
    private static final String[] date2 = new String[]{"全部", "仅鉴定", "有证书", "有保单"};
    @BindView(R.id.magic_indicator2)
    MagicIndicator magicIndicator;
    private List<String> mDataList2 = Arrays.asList(date2);
    private static final String[] date3 = new String[]{"全部", "审核中", "鉴定中", "鉴定失败"};
    private List<String> mDataList3 = Arrays.asList(date3);
    private ExamplePagerAdapter mExamplePagerAdapter;



    private void initView2(final List<String> date) {
//        magicIndicator.setBackgroundResource(R.drawable.round_indicator_bg);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return date.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.WHITE);
                simplePagerTitleView.setText(date.get(index));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager2.setCurrentItem(index);
                    }
                });
                badgePagerTitleView.setInnerPagerTitleView(simplePagerTitleView);

                return badgePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
//                linePagerIndicator.setColors(Color.WHITE);
                return linePagerIndicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerDrawable(new ColorDrawable() {
            @Override
            public int getIntrinsicWidth() {
                return UIUtil.dip2px(getApplication(), 15);
            }
        });
        ViewPagerHelper.bind(magicIndicator, viewPager2);

        mExamplePagerAdapter = new ExamplePagerAdapter(date);
        viewPager2.setAdapter(mExamplePagerAdapter);
    }


    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.title1, R.id.title2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title1:
                title2.setTextColor(Color.rgb(176, 176, 176));
                title1.setTextColor(Color.rgb(255, 255, 255));
                initView2(mDataList2);
                break;
            case R.id.title2:
                title1.setTextColor(Color.rgb(176, 176, 176));
                title2.setTextColor(Color.rgb(255, 255, 255));
                initView2(mDataList3);
                break;
        }
    }

    @Override
    public boolean isFullScreen() {
        return true;
    }

    @Override
    public int getLayoutId() {
        return R.layout.my_appraisa;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initView2(mDataList2);

    }
}
