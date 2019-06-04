package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.MessageAdd;
import com.example.hemin.fnb.ui.base.BaseFragment;

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
import butterknife.Unbinder;

public class TabMessageFragment extends BaseFragment {


    @BindView(R.id.magic_indicator2)
    MagicIndicator magicIndicator;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    Unbinder unbinder;
    private static final String[] date = new String[]{"资料库", "推荐", "关注"};
    private List<String> mDataList = Arrays.asList(date);
    private FragmentContainerHelper mFragmentContainerHelper = new FragmentContainerHelper();
    private MessageFragment fragment = new MessageFragment();

    @Override
    protected void initView(View view) {
        initView2(mDataList);
        initFragment(0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_message;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void initView2(final List<String> date) {

        CommonNavigator commonNavigator = new CommonNavigator(getActivity());
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
                simplePagerTitleView.setNormalColor(R.color.c999999);
                simplePagerTitleView.setSelectedColor(Color.BLACK);
                simplePagerTitleView.setText(date.get(index));
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFragmentContainerHelper.handlePageSelected(index);
                        initFragment(index);

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
                return UIUtil.dip2px(getActivity(), 15);
            }
        });
        mFragmentContainerHelper.attachMagicIndicator(magicIndicator);
    }

    private void initFragment(int indexx) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
            fragment = new MessageFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("indexNumber", indexx);
            fragment.setArguments(bundle);
            fragmentTransaction.add(R.id.fragment_container, fragment);
            fragmentTransaction.commit();

        }
    }

    @OnClick({R.id.message_add, R.id.c1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.message_add:
                Intent intent = new Intent(getActivity(), MessageAdd.class);
                startActivity(intent);
                break;

        }
    }
}
