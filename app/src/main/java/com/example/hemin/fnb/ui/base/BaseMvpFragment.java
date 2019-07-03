package com.example.hemin.fnb.ui.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.example.hemin.fnb.ui.util.AppUtils;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;


public abstract class BaseMvpFragment<T extends BasePresenter>  extends   BaseFragment implements BaseView{

    protected T mPresenter;

    private View view;
    /**
     * 是否加载过数据
     */
    private boolean hasLoaded = false;
    /**
     * 是否初始化过布局
     */
    private boolean isCreated = false;
    /**
     * 当前界面是否可见
     */
    private boolean isVisibleToUser = false;


    @Override
    protected void initView(View view) {
        isCreated = true;
        this.view = view;
        lazyLoad(this.view);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        this.isVisibleToUser = isVisibleToUser;//注：关键步骤
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad(view);
    }
    private void lazyLoad(View view) {
        if (!isVisibleToUser || hasLoaded || !isCreated) {
            return;
        }
        lazyInitView(view);
        hasLoaded = true;//注：关键步骤，确保数据只加载一次
    }
    public abstract void lazyInitView(View view);

    @Override
    public void onDestroyView() {
        isCreated = false;
        hasLoaded = false;
        super.onDestroyView();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroyView();
    }
    /**
     * 绑定生命周期 防止MVP内存泄漏
     *
     * @param <T>
     * @return
     */
    @Override
    public <T> AutoDisposeConverter<T> bindAutoDispose() {
        return AutoDispose.autoDisposable(AndroidLifecycleScopeProvider
                .from(this, Lifecycle.Event.ON_DESTROY));
    }
}
