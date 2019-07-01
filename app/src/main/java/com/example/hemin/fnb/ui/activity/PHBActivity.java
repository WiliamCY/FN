package com.example.hemin.fnb.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.adapter.PHBAdapter;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.Find5Bean;
import com.example.hemin.fnb.ui.bean.PHBBean;
import com.example.hemin.fnb.ui.contract.PHBContract;
import com.example.hemin.fnb.ui.presenter.PHBPresenter;
import com.example.hemin.fnb.ui.util.Utils;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PHBActivity extends BaseMvpActivity<PHBPresenter> implements PHBContract.View {

    Map<String, String> map = new ArrayMap<>();
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;
    @BindView(R.id.phb_recycletview)
    RecyclerView phbRecycletview;

    @Override
    public int getLayoutId() {
        return R.layout.activity_phb;
    }

    @Override
    public void initView() {
        mPresenter = new PHBPresenter();
        mPresenter.attachView(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        title.setVisibility(View.GONE);
        map = Utils.getAuthorization(this);
        mPresenter.getRankingList(this, map, 1, 100);
    }


    @Override
    public void onSuccess(BaseObjectBean bean) {
    if(bean.getErrorCode() != 0){
        Utils.showMyToast(Toast.makeText(this,bean.getErrorMsg(),Toast.LENGTH_SHORT),400);
    }


    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
    public  void Date(List<PHBBean> list){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        phbRecycletview.setLayoutManager(linearLayoutManager);
        PHBAdapter adapter = new PHBAdapter(R.layout.phb_adapter_view, list);
        phbRecycletview.setAdapter(adapter);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.title:
                break;
        }
    }
}
