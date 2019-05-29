package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.UserAbout;
import com.example.hemin.fnb.ui.adapter.Find3Adapter;
import com.example.hemin.fnb.ui.adapter.Find4Adapter;
import com.example.hemin.fnb.ui.adapter.Find5Adapter;
import com.example.hemin.fnb.ui.adapter.FindAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.Find2Bean;
import com.example.hemin.fnb.ui.bean.Find4Bean;
import com.example.hemin.fnb.ui.bean.Find5Bean;
import com.example.hemin.fnb.ui.bean.FindBean;
import com.example.hemin.fnb.ui.bean.FindDeilyBean;
import com.example.hemin.fnb.ui.bean.FindFirstBean;
import com.example.hemin.fnb.ui.contract.FindContract;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.presenter.FindPresenter;
import com.example.hemin.fnb.ui.util.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TabFindFragment extends BaseMvpFragment<FindPresenter> implements FindContract.View {
    @BindView(R.id.find_recyclerview)
    RecyclerView findRecyclerview;
        Unbinder unbinder;
  private  int sendPost = 0;
  private Map Authorization = new HashMap();
    @Override
    protected void initView(View view) {
        mPresenter = new FindPresenter();
        mPresenter.attachView(this);
        initDate();
    }

    private void initDate() {
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
         Authorization = Utils.getAuthorization(getActivity());
        String userId = sp.getString("userId", "");
        mPresenter.pageListHuo(getActivity(), Authorization, 1, 3);

//        mPresenter.getDaily(getActivity(), Authorization, Long.parseLong(userId));
//        mPresenter.guessLove(getActivity(), Authorization);
//        mPresenter.getRankingList(getActivity(), Authorization, 1, 10);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.find_view;
    }


    @Override
    public void onSuccess(BaseObjectBean bean) {
        sendPost++;
        Toast.makeText(getActivity(), bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        initNetWork(sendPost);

    }
    private void initNetWork(int sendPost){
        Log.d("sendPost", String.valueOf(sendPost));
        if(sendPost <=1){
            mPresenter.addHua(getActivity(), Authorization, 1, 1);
        }

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Date(List<FindBean> bean, int status) {
             initRecyclerView(bean);

    }


    private void initRecyclerView(final List<FindBean> bean) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        findRecyclerview.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        FindAdapter adapter = new FindAdapter(getActivity(), bean);
        Log.d("beanDate", bean.toString());
        findRecyclerview.setAdapter(adapter);
        final int size = adapter.getItemCount();
        Log.d("findSize1:", String.valueOf(size));
        adapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int Position, String path) {

            }

            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), UserAbout.class);
                String paths = bean.get(position).getPathUrl();
                intent.putExtra("path",paths);
                startActivity(intent);

            }
        });


    }


    @Override
    public void onError(Throwable throwable) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

//    /**
//     * layout_parent = HorizontalScrollView
//     *
//     * @param
//     * @param index 代表这个数据源插入的位置 比如你一个数据源有3条数据 你的addView_2中的index就应该是3
//     */
//    private void addView_1(List<FindFirstBean.DataBean.RecordsBean> beans, int index) {
//        for (int i = 0; i < beans.size(); i++) {
//            View view = LayoutInflater.from(getActivity()).inflate(R.layout.find_card_view, null);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);//这个类型根据你自己的父亲布局来，
//            view.setLayoutParams(params);
//            //这里写对应的控件数据
////            TextView tv = view.findViewById(R.id.tv);
//
//            //添加进父布局
//            layoutParent.addView(view, index);
//            index++;
//        }
//    }
//
//    /**
//     * layout_parent = HorizontalScrollView
//     *
//     * @param
//     */
//    private void addView_2(List<Find2Bean.DataBean.RecordsBean> beans, int index) {
//        for (int i = 0; i < beans.size(); i++) {
//            View view = LayoutInflater.from(getActivity()).inflate(R.layout.find_card2_view, null);
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);//这个类型根据你自己的父亲布局来，
//            view.setLayoutParams(params);
//            //这里写对应的控件数据
////            TextView tv = view.findViewById(R.id.tv);
//
//            //添加进父布局
//            layoutParent.addView(view);
//            index++;
//        }
//    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder1 = ButterKnife.bind(this, rootView);
//        return rootView;
//    }
}
