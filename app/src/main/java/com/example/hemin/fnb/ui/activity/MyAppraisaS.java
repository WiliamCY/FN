package com.example.hemin.fnb.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.adapter.BaseViewPagerAdapter;
import com.example.hemin.fnb.ui.base.BaseActivity;
import com.example.hemin.fnb.ui.fragment.QuanZiFragment;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAppraisaS extends BaseActivity {
    @BindView(R.id.magic_indicator2)
    MagicIndicator magicIndicator;
    //    @BindView(R.id.header_left_img)
//    ImageView headerLeftImg;
    private static final String[] date2 = new String[]{"我的关注", "我的发布"};
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.lay_back)
    LinearLayout layBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;
    private List<String> mDataList2 = Arrays.asList(date2);
    List<Fragment> fragmentList = new ArrayList<>();
    private FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();

    private void initDate() {
        for (int i = 0; i < mDataList2.size(); i++) {
            fragmentList.add(QuanZiFragment.getInstance(mDataList2.get(i)));
        }

        initView2();
    }

    private void initView2() {
        viewPager.setAdapter(new BaseViewPagerAdapter(getSupportFragmentManager(), fragmentList));
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return mDataList2 == null ? 0 : mDataList2.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                BadgePagerTitleView badgePagerTitleView = new BadgePagerTitleView(context);
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setNormalColor(R.color.c333333);
                simplePagerTitleView.setSelectedColor(Color.BLACK);
                simplePagerTitleView.setTextSize(15);
                simplePagerTitleView.setText(mDataList2.get(index));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                badgePagerTitleView.setInnerPagerTitleView(simplePagerTitleView);

                return badgePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
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
        mFragmentContainerHelper.attachMagicIndicator(magicIndicator);
        ViewPagerHelper.bind(magicIndicator, viewPager);

    }


    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.lay_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_back:
                finish();
                break;
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.my_appraisas;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initDate();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
