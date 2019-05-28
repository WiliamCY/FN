package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
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
import com.example.hemin.fnb.ui.adapter.Find3Adapter;
import com.example.hemin.fnb.ui.adapter.Find4Adapter;
import com.example.hemin.fnb.ui.adapter.Find5Adapter;
import com.example.hemin.fnb.ui.base.BaseMvpFragment;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.Find2Bean;
import com.example.hemin.fnb.ui.bean.Find4Bean;
import com.example.hemin.fnb.ui.bean.Find5Bean;
import com.example.hemin.fnb.ui.bean.FindDeilyBean;
import com.example.hemin.fnb.ui.bean.FindFirstBean;
import com.example.hemin.fnb.ui.contract.FindContract;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.presenter.FindPresenter;
import com.example.hemin.fnb.ui.util.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class TabFindFragment extends BaseMvpFragment<FindPresenter> implements FindContract.View {
    @BindView(R.id.find_recyclerview2)
    RecyclerView findRecyclerview2;
    @BindView(R.id.find_recyclerview3)
    RecyclerView findRecyclerview3;
    @BindView(R.id.find_recyclerview4)
    RecyclerView findRecyclerview4;
    @BindView(R.id.find_recyclerview5)
    RecyclerView findRecyclerview5;
    //    @BindView(R.id.find_recyclerview)
//    RecyclerView findRecyclerview;
    Unbinder unbinder;
    @BindView(R.id.scroll)
    HorizontalScrollView scroll;
    Unbinder unbinder1;
    @BindView(R.id.layout_parent)
    LinearLayout layoutParent;
    private List<FindFirstBean.DataBean.RecordsBean> list = new ArrayList<>();
    private List<Find2Bean.DataBean.RecordsBean> list2 = new ArrayList<>();
    private List<FindDeilyBean.DataBean.ListBean> list3 = new ArrayList<>();
    private List<Find4Bean.DataBean> list4 = new ArrayList<>();
    private List<Find5Bean.DataBean.RecordsBean> list5 = new ArrayList<>();

    @Override
    protected void initView(View view) {
        mPresenter = new FindPresenter();
        mPresenter.attachView(this);
        initDate();
    }

    private void initDate() {
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        Map Authorization = Utils.getAuthorization(getActivity());
        String userId = sp.getString("userId", "");
//        mPresenter.pageListHuo(getActivity(), Authorization, 1, 3);
//        mPresenter.addHua(getActivity(), Authorization, 1, 1);
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
        Toast.makeText(getActivity(), bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void Date(List<FindFirstBean.DataBean.RecordsBean> bean, int status) {
        addView_1(bean, 1);
    }

    @Override
    public void Date2(List<Find2Bean.DataBean.RecordsBean> bean, int status) {
        addView_2(bean, 3);
    }

    @Override
    public void Date3(List<FindDeilyBean.DataBean.ListBean> bean, int status) {
        initRecyclerView3(bean);
    }

    @Override
    public void Date4(List<Find4Bean.DataBean> bean, int status) {
        initRecyclerView4(bean);
    }

    @Override
    public void Date5(List<Find5Bean.DataBean.RecordsBean> bean, int status) {
        initRecyclerView5(bean);
    }


//    private void initRecyclerView(final List<FindFirstBean.DataBean.RecordsBean> bean) {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        findRecyclerview.setLayoutManager(layoutManager);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        FindAdapter adapter = new FindAdapter(getActivity(), bean);
//        Log.d("beanDate", bean.toString());
//        findRecyclerview.setAdapter(adapter);
//        final int size = adapter.getItemCount();
//        Log.d("findSize1:", String.valueOf(size));
//        adapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
//            @Override
//            public void onItemClick(int Position, String path) {
//
//            }
//
//            @Override
//            public void onItemClick(int position) {
//
//
//            }
//        });
//
//
//    }
//

//    private void initRecyclerView2(final List<Find2Bean.DataBean.RecordsBean> bean) {
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        findRecyclerview.setLayoutManager(layoutManager);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        Find2Adapter adapter = new Find2Adapter(getActivity(), bean);
//        Log.d("beanDate", bean.toString());
//        findRecyclerview.setAdapter(adapter);
//        final int size = adapter.getItemCount();
//        Log.d("findSize2:", String.valueOf(size));
//        adapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
//            @Override
//            public void onItemClick(int Position, String path) {
//
//            }
//
//            @Override
//            public void onItemClick(int position) {
//
//
//            }
//        });
//
//
//    }

    private void initRecyclerView3(final List<FindDeilyBean.DataBean.ListBean> bean) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        findRecyclerview3.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        Find3Adapter adapter = new Find3Adapter(getActivity(), bean);
        Log.d("beanDate", bean.toString());
        findRecyclerview3.setAdapter(adapter);
        final int size = adapter.getItemCount();
        Log.d("findSize:", String.valueOf(size));
        adapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int Position, String path) {

            }

            @Override
            public void onItemClick(int position) {


            }
        });


    }

    private void initRecyclerView4(final List<Find4Bean.DataBean> bean) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        findRecyclerview4.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        Find4Adapter adapter = new Find4Adapter(getActivity(), bean);
        Log.d("beanDate", bean.toString());
        findRecyclerview4.setAdapter(adapter);
        final int size = adapter.getItemCount();
        Log.d("findSize:", String.valueOf(size));
        adapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int Position, String path) {

            }

            @Override
            public void onItemClick(int position) {


            }
        });


    }


    private void initRecyclerView5(final List<Find5Bean.DataBean.RecordsBean> bean) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        findRecyclerview5.setLayoutManager(layoutManager);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        Find5Adapter adapter = new Find5Adapter(getActivity(), bean);
        Log.d("beanDate", bean.toString());
        findRecyclerview5.setAdapter(adapter);
        final int size = adapter.getItemCount();
        Log.d("findSize:", String.valueOf(size));
        adapter.setRecyclerItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(int Position, String path) {

            }

            @Override
            public void onItemClick(int position) {


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

    /**
     * layout_parent = HorizontalScrollView
     *
     * @param
     * @param index 代表这个数据源插入的位置 比如你一个数据源有3条数据 你的addView_2中的index就应该是3
     */
    private void addView_1(List<FindFirstBean.DataBean.RecordsBean> beans, int index) {
        for (int i = 0; i < beans.size(); i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.find_card_view, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);//这个类型根据你自己的父亲布局来，
            view.setLayoutParams(params);
            //这里写对应的控件数据
//            TextView tv = view.findViewById(R.id.tv);

            //添加进父布局
            layoutParent.addView(view, index);
            index++;
        }
    }

    /**
     * layout_parent = HorizontalScrollView
     *
     * @param
     */
    private void addView_2(List<Find2Bean.DataBean.RecordsBean> beans, int index) {
        for (int i = 0; i < beans.size(); i++) {
            View view = LayoutInflater.from(getActivity()).inflate(R.layout.find_card2_view, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);//这个类型根据你自己的父亲布局来，
            view.setLayoutParams(params);
            //这里写对应的控件数据
//            TextView tv = view.findViewById(R.id.tv);

            //添加进父布局
            layoutParent.addView(view);
            index++;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }
}
