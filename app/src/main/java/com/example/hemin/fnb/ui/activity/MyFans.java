package com.example.hemin.fnb.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.adapter.FansAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.GuanZhuBean;
import com.example.hemin.fnb.ui.contract.FanContract;
import com.example.hemin.fnb.ui.presenter.FansPresenter;
import com.example.hemin.fnb.ui.util.AppUtils;
import com.example.hemin.fnb.ui.util.ProgressDialog;
import com.example.hemin.fnb.ui.util.Utils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyFans extends BaseMvpActivity<FansPresenter> implements FanContract.View {


    @BindView(R.id.recylcerview)
    RecyclerView recylcerview;
    @BindView(R.id.image_1)
    ImageView image1;
    @BindView(R.id.title_1)
    TextView title1;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;
    @BindView(R.id.y1)
    LinearLayout y1;
    private int status;
    private int page = 1, size = 10;
    private List<GuanZhuBean.DataBean.RecordsBean> bean = new ArrayList<>();
    private FansAdapter adapter = new FansAdapter(R.layout.gz_adapter, bean);

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_fans;
    }

    @Override
    public void initView() {
        mPresenter = new FansPresenter();
        mPresenter.attachView(this);
        initDate();

    }

    private void initDate() {
        status = getIntent().getIntExtra("status", 0);
        final Map map = Utils.getAuthorization(this);
        if (status == 1) {
            title.setText("我的关注");
            mPresenter.GZ(map, page, size, Integer.parseInt(Utils.getUserId(this)),0);
        } else if (status == 2) {
            title.setText("我的发布");
            mPresenter.fs(map, page, size,0);
        }
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    if(status == 1){
                        mPresenter.GZ(map, page, size, Integer.parseInt(Utils.getUserId(AppUtils.getContext())),1);
                    }else if(status == 2){
                        mPresenter.fs(map, page, size,1);
                    }
            }
        });
    }

    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(this);
    }

    @Override
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onSuccess(BaseObjectBean beans) {
         if(beans.getErrorCode() != 0){
             Toast.makeText(this,beans.getErrorMsg(),Toast.LENGTH_SHORT).show();
         }

    }

    public void date(List<GuanZhuBean.DataBean.RecordsBean> list,int statu) {
        if (list.size() > 0) {
            recylcerview.setVisibility(View.VISIBLE);
            title1.setVisibility(View.GONE);
            image1.setVisibility(View.GONE);
            if(statu == 0){
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recylcerview.setLayoutManager(linearLayoutManager);
                FansAdapter adapter = new FansAdapter(R.layout.gz_adapter, list);
                recylcerview.setAdapter(adapter);
            }else if(statu == 1) {
                adapter.replaceData(list);
                refreshLayout.finishRefresh();

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.user_toolbar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.user_toolbar:
                break;
        }
    }
}
