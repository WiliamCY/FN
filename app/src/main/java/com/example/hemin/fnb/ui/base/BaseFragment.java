package com.example.hemin.fnb.ui.base;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {
    public Activity mActivity;
    private Unbinder unBinder;
    private View view;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(view == null){
            view = inflater.inflate(this.getLayoutId(), container, false);
            unBinder = ButterKnife.bind(this, view);
            initView(view);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            unBinder.unbind();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 初始化视图
     *
     * @param view
     */
    protected abstract void initView(View view);

    protected abstract int getLayoutId();
}
