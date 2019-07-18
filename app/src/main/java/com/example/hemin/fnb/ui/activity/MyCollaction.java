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
import com.example.hemin.fnb.ui.fragment.CollectionFragment;

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

public class MyCollaction extends BaseActivity {
    @BindView(R.id.titl1)
    TextView titl1;
    @BindView(R.id.header_right_tv)
    TextView headerRightTv;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.title2)
    TextView title2;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.magic_indicator2)
    MagicIndicator magicIndicator;
    @BindView(R.id.fragment_container)
    FrameLayout viewPager;
    @BindView(R.id.lay_back)
    LinearLayout layBack;
    @BindView(R.id.title)
    TextView title;
    private boolean status = true;
    private String[] titles = new String[]{"古玩", "现代艺术"};
    private List<String> mDataList2 = Arrays.asList(titles);
    private String[] titlesc = new String[]{"作曲家"};
    private List<String> mDataList2c = Arrays.asList(titlesc);
    private CollectionFragment fragment = new CollectionFragment();
    private FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();

    @Override
    public int getLayoutId() {
        return R.layout.my_collect;
    }

    @Override
    public void initView() {
        initFragment(0, status);
        initView2(mDataList2, status);
        title.setText("我的收藏");

    }


    @SuppressLint("ResourceAsColor")
    @OnClick({R.id.title1, R.id.title2, R.id.lay_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title1:
                status = true;

                magicIndicator.setVisibility(View.VISIBLE);
                view1.setVisibility(View.VISIBLE);
                view2.setVisibility(View.GONE);
                title1.getPaint().setFakeBoldText(true);
                title2.getPaint().setFakeBoldText(false);
                title2.setTextColor(Color.rgb(176, 176, 176));
                title1.setTextColor(Color.rgb(255, 255, 255));
                initView2(mDataList2, true);
                break;
            case R.id.title2:
                status = false;
                magicIndicator.setVisibility(View.GONE);
                view1.setVisibility(View.GONE);
                view2.setVisibility(View.VISIBLE);
                title2.getPaint().setFakeBoldText(true);
                title1.getPaint().setFakeBoldText(false);
                title1.setTextColor(Color.rgb(176, 176, 176));
                title2.setTextColor(Color.rgb(255, 255, 255));
//                initView2(mDataList2c,true);
                initFragment(99, false);
                break;
            case R.id.lay_back:
                finish();
                break;
        }
    }

    private void initView2(List<String> date, final boolean status) {
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

    private void initFragment(int indexx, boolean status) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
            fragment = new CollectionFragment();
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
