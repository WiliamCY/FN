package com.example.hemin.fnb.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;

public class FragmentMyWant extends BaseMvpFragment  {



    public  static FragmentMyWant getInstance(){
        return  new FragmentMyWant();
    }


//    @Override
//    protected void initView(View view) {
//
//    }

    @Override
    public void lazyInitView(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_want;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
