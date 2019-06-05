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

public class MyAppraisa extends BaseActivity {

    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.title2)
    TextView title2;
    private static final String[] date3 = new String[]{"全部", "仅鉴定", "有证书", "有保单"};
    @BindView(R.id.magic_indicator2)
    MagicIndicator magicIndicator;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.header_left_img)
    ImageView headerLeftImg;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.view2)
    View view2;
    private List<String> mDataList2 = Arrays.asList(date2);
    private static final String[] date2 = new String[]{"全部", "审核中", "鉴定中", "鉴定失败"};
    private List<String> mDataList3 = Arrays.asList(date3);
    private boolean status = true;
    private FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();
    private AppraisaFragment fragment = new AppraisaFragment();

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
                simplePagerTitleView.setNormalColor(Color.GRAY);
                simplePagerTitleView.setSelectedColor(Color.BLACK);
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
    @OnClick({R.id.title1, R.id.title2, R.id.header_left_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title1:
                status = true;
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                title1.getPaint().setFakeBoldText(true);
                title2.getPaint().setFakeBoldText(false);
                title2.setTextColor(Color.rgb(176, 176, 176));
                title1.setTextColor(Color.rgb(255, 255, 255));
                initFragment(0, true);
                initView2(mDataList2, true);
                break;
            case R.id.title2:
                status = false;
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                title2.getPaint().setFakeBoldText(true);
                title1.getPaint().setFakeBoldText(false);
                title1.setTextColor(Color.rgb(176, 176, 176));
                title2.setTextColor(Color.rgb(255, 255, 255));
                initFragment(0, false);
                initView2(mDataList3, false);
                break;
            case R.id.header_left_img:
                finish();
                break;
        }
    }


    @Override
    public int getLayoutId() {
        return R.layout.my_appraisa;
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
            fragment = new AppraisaFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("indexNumber", indexx);
            bundle.putBoolean("status", status);
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
