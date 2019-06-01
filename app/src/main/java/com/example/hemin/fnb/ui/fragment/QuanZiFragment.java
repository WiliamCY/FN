package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.CollectionInformationMessage;
import com.example.hemin.fnb.ui.adapter.AppRaisaAdapter;
import com.example.hemin.fnb.ui.adapter.AppRaisasAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.AppraisaBean;
import com.example.hemin.fnb.ui.bean.AppraisasBean;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.AppraisaContract;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.presenter.AppraisaPresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;
import com.example.hemin.fnb.ui.util.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class QuanZiFragment extends BaseMvpFragment<AppraisaPresenter> implements AppraisaContract.View {

    @BindView(R.id.apr_recylcerview)
    RecyclerView aprRecylcerview;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.title)
    TextView title;
    Unbinder unbinder;

//
//    private void initRecyclerView(final List<AppraisaBean.DataBean.RecordsBean> bean) {
//        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
//        aprRecylcerview.setLayoutManager(layoutManager);
//        layoutManager.setOrientation(OrientationHelper.VERTICAL);
//        AppRaisaAdapter adapter = new AppRaisaAdapter(getActivity(), bean);
//        Log.d("beanDate", bean.toString());
//        aprRecylcerview.setAdapter(adapter);
//        adapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
//            @Override
//            public void onItemClick(int Position, String path) {
//
//            }
//
//            @Override
//            public void onItemClick(int Position) {
//                Intent intent = new Intent(getActivity(), CollectionInformationMessage.class);
//                Bundle bundle = new Bundle();
//                bundle.putString("id", bean.get(Position).getCollectionId());
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
//
//    }

//    private void initRecyclerViews(final List<AppraisasBean.DataBean.RecordsBean> bean) {
//        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
//        aprRecylcerview.setLayoutManager(layoutManager);
//        layoutManager.setOrientation(OrientationHelper.VERTICAL);
//        AppRaisasAdapter adapter = new AppRaisasAdapter(getActivity(), bean);
//        Log.d("beanDate", bean.toString());
//        aprRecylcerview.setAdapter(adapter);
//        adapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
//            @Override
//            public void onItemClick(int Position, String path) {
//
//            }
//
//            @Override
//            public void onItemClick(int Position) {
//
//            }
//        });
//
//    }

    @Override
    protected void initView(View view) {
        mPresenter = new AppraisaPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(getActivity());
        int indexNumber = this.getArguments().getInt("indexNumber");
        Log.d("indexNumbers", String.valueOf(indexNumber));
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
         Map token = Utils.getAuthorization(getActivity());
        String userId = sp.getString("userId", "");

//        if (status == true) {
//            mPresenter.Appraisa(map, 1, 10, indexNumber, Long.parseLong(userId));
//
//
//        } else {
//            mPresenter.Appraisas(getActivity(), map, 1, 10, indexNumber, Long.parseLong(userId));
//        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_appraisa_recyclerview;
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {
        if(bean.getErrorCode() != 0){
            Toast.makeText(getActivity(), bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(getActivity());
    }

    @Override
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void Date(Object object, int status) {

    }


//    @Override
//    public void Date(Object o, int status) {
//
//        if (status == 0) {
//            aprRecylcerview.setVisibility(View.VISIBLE);
//            image.setVisibility(View.GONE);
//            title.setVisibility(View.GONE);
//            initRecyclerView((List<AppraisaBean.DataBean.RecordsBean>) o);
//        } else if (status == 1) {
//            aprRecylcerview.setVisibility(View.VISIBLE);
//            image.setVisibility(View.GONE);
//            title.setVisibility(View.GONE);
//            initRecyclerViews((List<AppraisasBean.DataBean.RecordsBean>) o);
//        }
//
//    }


    @Override
    public void onError(Throwable throwable) {

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
