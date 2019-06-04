package com.example.hemin.fnb.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;
import com.example.hemin.fnb.ui.fragment.AppraisaFragment;
import com.example.hemin.fnb.ui.fragment.QuanZiFragment;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.badge.BadgePagerTitleView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAppraisaS extends BaseActivity {
    @BindView(R.id.magic_indicator2)
    MagicIndicator magicIndicator;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.header_left_img)
    ImageView headerLeftImg;
    private static final String[] date2 = new String[]{"我的关注", "我的发布"};
    private List<String> mDataList2 = Arrays.asList(date2);

    private boolean status = true;
    private FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();
    private QuanZiFragment fragment = new QuanZiFragment();

    private void initDate() {
        initFragment(0, status);
        initView2(mDataList2, status);
    }

    private void initView2(final List<String> date, final boolean status) {

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
                simplePagerTitleView.setNormalColor(R.color.c333333);
                simplePagerTitleView.setSelectedColor(Color.BLACK);
              simplePagerTitleView.setTextSize(20);
                simplePagerTitleView.setText(date.get(index));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFragmentContainerHelper.handlePageSelected(index);
                        initFragment(index, status);

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
    }


    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.header_left_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.header_left_img:
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


    private void initFragment(int indexx, boolean status) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
            fragment = new QuanZiFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("indexNumber", indexx);
            fragment.setArguments(bundle);
            fragmentTransaction.add(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
