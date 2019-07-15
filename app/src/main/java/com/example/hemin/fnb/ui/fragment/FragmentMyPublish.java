package com.example.hemin.fnb.ui.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.WodeQuanziContract;
import com.example.hemin.fnb.ui.presenter.WoDoQuanZiPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentMyPublish extends BaseMvpFragment<WoDoQuanZiPresenter> implements WodeQuanziContract.View {


    @BindView(R.id.apr_recylcerview)
    RecyclerView aprRecylcerview;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.c1)
    ConstraintLayout c1;
    Unbinder unbinder;

    public static FragmentMyPublish getInstance() {
        return new FragmentMyPublish();
    }


    @Override
    public void lazyInitView(View view) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_publish;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Date(Object object, int index) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void DateUserId(Object object, String userId, String content, String url, String nickName, String isGiveNum,String edf) {

    }

    @Override
    public void onSuccess(BaseObjectBean bean) {
        if (bean.getErrorCode() != 0) {
            Toast.makeText(getActivity(), bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
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
}
