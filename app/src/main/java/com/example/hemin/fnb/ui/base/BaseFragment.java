package com.example.hemin.fnb.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment {

    private Unbinder unBinder;
    protected View rootView;
    private boolean isVisibleToUser;
    protected boolean isLoadData;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if(rootView == null){
            rootView = inflater.inflate(this.getLayoutId(), container, false);
            if(!isLazyLoad() || isVisibleToUser){
                         loadViewData(savedInstanceState);
            }
        }
        return rootView;
    }
    private void loadViewData(@Nullable Bundle savedInstanceState){
        unBinder = ButterKnife.bind(this, rootView);
        isLoadData = true;
        initView(savedInstanceState);


    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        if(isVisibleToUser && isLazyLoad() && rootView !=null && !isLoadData){
            loadViewData(null);
        }
    }
    protected  boolean isLazyLoad(){
        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }
    protected abstract void initView(@Nullable Bundle savedInstanceState);
//    /**
//     * 初始化视图
//     *
//     * @param view
//     */
//    protected abstract void initView(View view);

    protected abstract int getLayoutId();
}
