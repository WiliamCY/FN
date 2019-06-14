package com.example.hemin.fnb.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CollectionFragment extends BaseFragment {

  private String collectType;
  private int indexNumber;
    @Override
    protected void initView(View view) {
        boolean status = this.getArguments().getBoolean("status");
        indexNumber = this.getArguments().getInt("indexNumber");

    }



    @Override
    protected int getLayoutId() {
        return R.layout.collcect_fragment;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
