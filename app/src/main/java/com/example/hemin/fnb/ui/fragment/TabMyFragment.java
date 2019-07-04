package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.MyAppraisa;
import com.example.hemin.fnb.ui.activity.UserSetting;
import com.example.hemin.fnb.ui.adapter.ComFragmentAdapter;
import com.example.hemin.fnb.ui.base.BaseFragment;
import com.example.hemin.fnb.ui.util.CircleImageView;
import com.example.hemin.fnb.ui.util.DensityUtil;
import com.example.hemin.fnb.ui.util.ExamplePagerAdapter;
import com.example.hemin.fnb.ui.util.GlideLoadUtils;
import com.example.hemin.fnb.ui.util.MessageEvent;
import com.example.hemin.fnb.ui.util.ScaleTransitionPagerTitleView;
import com.example.hemin.fnb.ui.util.ScreenUtil;
import com.example.hemin.fnb.ui.util.StatusBarUtil;
import com.example.hemin.fnb.ui.view.JudgeNestedScrollView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.WrapPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TabMyFragment extends BaseFragment {
    @BindView(R.id.setting)
    ImageView setting;
    @BindView(R.id.user_logo)
    CircleImageView userLogo;
    @BindView(R.id.qm)
    TextView qm;
    Unbinder unbinder;
    @BindView(R.id.login)
    TextView login;
    Unbinder unbinder1;
    @BindView(R.id.lay1)
    LinearLayout lay1;
    @BindView(R.id.lay2)
    LinearLayout lay2;
    @BindView(R.id.lay3)
    LinearLayout lay3;
    @BindView(R.id.lay4)
    LinearLayout lay4;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.scrollView)
    JudgeNestedScrollView scrollView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.buttonBarLayout)
    ButtonBarLayout buttonBarLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.magic_indicator)
    MagicIndicator magicIndicator;
    @BindView(R.id.magic_indicator_title)
    MagicIndicator magicIndicatorTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.toolbar_username)
    TextView toolbarUsername;

    private String birthday, nicknames, signature, url, userid, sex;
    private final String[] date = new String[]{"发布", "收藏", "想要"};
    private final List<String> dates = Arrays.asList(date);
    int toolBarPositionY = 0;
    private int mOffset = 0;
    private int mScrollY = 0;
    private ExamplePagerAdapter adapter = new ExamplePagerAdapter(dates);

    @Override
    protected void initView(View view) {
        StatusBarUtil.immersive(getActivity());
        StatusBarUtil.setPaddingSmart(getActivity(), toolbar);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        unbinder = ButterKnife.bind(this, view);
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String nickname = sp.getString("nickName", "");
        url = sp.getString("url", "").trim();
        birthday = sp.getString("birthday", "");
        sex = sp.getString("sex", "");
        signature = sp.getString("signature", "");
        userid = sp.getString("userId", "");
        qm.setText(signature);
        login.setText(nickname);
        toolbarUsername.setText(nickname);
        Glide.with(this).load(url).into(userLogo);
        viewPager.setAdapter(adapter);
        initViewss();
    }

    private void initViewss() {

        refreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int bottomHeight, int extendHeight) {
                mOffset = offset / 2;
                ivHeader.setTranslationY(mOffset - mScrollY);
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }


            @Override
            public void onFooterMoving(RefreshFooter footer, boolean isDragging, float percent, int offset, int footerHeight, int maxDragHeight) {
                mOffset = offset / 2;
                ivHeader.setTranslationY(mOffset - mScrollY);
                toolbar.setAlpha(1 - Math.min(percent, 1));
            }
        });
        toolbar.post(new Runnable() {
            @Override
            public void run() {
                dealWithViewPager();
            }
        });
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            int lastScrollY = 0;
            int h = DensityUtil.dp2px(250);
            int color = ContextCompat.getColor(getActivity(), R.color.cffffff) & 0x00ffffff;

            @Override
            public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] location = new int[2];
                magicIndicator.getLocationOnScreen(location);
                int yPosition = location[1];
                if (yPosition < toolBarPositionY) {
                    magicIndicatorTitle.setVisibility(View.VISIBLE);
                    scrollView.setNeedScroll(false);
                } else {
                    magicIndicatorTitle.setVisibility(View.GONE);
                    scrollView.setNeedScroll(true);

                }

                if (lastScrollY < h) {
                    scrollY = Math.min(h, scrollY);
                    mScrollY = scrollY > h ? h : scrollY;
                    buttonBarLayout.setAlpha(1f * mScrollY / h);
                    toolbar.setBackgroundColor(((255 * mScrollY / h) << 24) | color);
                    ivHeader.setTranslationY(mOffset - mScrollY);
                }
                if (scrollY == 0) {
                    ivMenu.setVisibility(View.GONE);
                } else {
                    ivMenu.setVisibility(View.VISIBLE);

                }

                lastScrollY = scrollY;
            }
        });
        buttonBarLayout.setAlpha(0);
        toolbar.setBackgroundColor(0);

        viewPager.setAdapter(new ComFragmentAdapter(getActivity().getSupportFragmentManager(), getFragments()));
        viewPager.setOffscreenPageLimit(10);
        viewPager.setOffscreenPageLimit(10);
        initMagicIndicator();
        initMagicIndicatorTitle();
    }

    private void dealWithViewPager() {
        toolBarPositionY = toolbar.getHeight();
        ViewGroup.LayoutParams params = viewPager.getLayoutParams();
        params.height = ScreenUtil.getScreenHeightPx(getActivity().getApplicationContext()) - toolBarPositionY - magicIndicator.getHeight() + 1;
        viewPager.setLayoutParams(params);
    }

    private List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < dates.size(); i++) {
            fragments.add(QuanZiFragment.getInstance(dates.get(i)));
        }
        return fragments;
    }

    private void initMagicIndicator() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return dates == null ? 0 : dates.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(dates.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#666666"));
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setSelectedColor(Color.parseColor("#333333"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                WrapPagerIndicator indicator = new WrapPagerIndicator(context);
                indicator.setFillColor(Color.parseColor("#FBED44"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicator, viewPager);
    }

    private void initMagicIndicatorTitle() {
        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
        commonNavigator.setScrollPivotX(0.65f);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return dates == null ? 0 : dates.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleTransitionPagerTitleView(context);
                simplePagerTitleView.setText(dates.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#666666"));
                simplePagerTitleView.setTextSize(18);
                simplePagerTitleView.setSelectedColor(Color.parseColor("#333333"));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }


            @Override
            public IPagerIndicator getIndicator(Context context) {
                WrapPagerIndicator indicator = new WrapPagerIndicator(context);
                indicator.setFillColor(Color.parseColor("#FBED44"));
                return indicator;
            }
        });
        magicIndicatorTitle.setNavigator(commonNavigator);
        ViewPagerHelper.bind(magicIndicatorTitle, viewPager);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_my;
    }

    @OnClick({R.id.setting, R.id.user_logo, R.id.qm, R.id.lay1, R.id.lay2, R.id.lay3, R.id.lay4,R.id.iv_menu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting:
                Intent intent = new Intent(getActivity(), UserSetting.class);
                startActivity(intent);
                break;
            case R.id.user_logo:
                break;
            case R.id.qm:
                break;
            case R.id.lay1:
                break;
            case R.id.lay2:
                break;
            case R.id.lay3:
                Intent intent1 = new Intent(getActivity(), MyAppraisa.class);
                startActivity(intent1);
                break;
            case R.id.lay4:
                break;
            case R.id.iv_menu:
                Intent intent2 = new Intent(getActivity(), UserSetting.class);
                startActivity(intent2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Subscribe(id = 2)
    public void Event(MessageEvent messageEvent) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE).edit();
        editor.putString("url", messageEvent.getMessage());
        editor.commit();
        GlideLoadUtils.getInstance().glideLoad(getActivity(), messageEvent.getMessage(), userLogo);
    }

    @Subscribe(id = 3)
    public void prints(String message) {
        Log.i("sdawdwdwadadh", message);
        qm.setText(message);
    }

    @Subscribe(id = 1)
    public void printss(String message) {
        Log.i("sdawdwdwadadh", message);
        login.setText(message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
