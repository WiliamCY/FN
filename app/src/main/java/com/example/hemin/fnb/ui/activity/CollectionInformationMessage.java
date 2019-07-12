package com.example.hemin.fnb.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.ColletionBean;
import com.example.hemin.fnb.ui.contract.CollectionContract;
import com.example.hemin.fnb.ui.presenter.CollectionPresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectionInformationMessage extends BaseMvpActivity<CollectionPresenter> implements CollectionContract.View {
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.image)
    ImageView images;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.back)
    ImageView back;

//    @BindView(R.id.collect_recyclerview)
//    RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.collection_information;
    }

    @Override
    public void initView() {
        mPresenter = new CollectionPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("id");
        SharedPreferences sp = this.getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String tokenType = sp.getString("tokenType", "");
        String Authorization = sp.getString("Authorization", "");
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", tokenType + Authorization);
        mPresenter.getColldetionMessage(this, map, id);
        title.setText("藏品信息单");
    }


    @Override
    public void onSuccess(BaseObjectBean bean) {
        if (bean.getErrorCode() != 0) {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
        ProgressDialog.getInstance().dismiss();
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
    public void ListDate(List<ColletionBean.DataBean.ListBean> bean) {

//           initRecyclerView(bean);
    }
//    private void initRecyclerView(final List<ColletionBean.DataBean.ListBean> bean) {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        CollectionAdapter adapter = new CollectionAdapter(this,bean);
//        recyclerView.setAdapter(adapter);
//    }

    @Override
    public void onError(Throwable throwable) {

    }

    public void Date(Map map) {
        String ctName = map.get("ctName").toString();
        String image = map.get("image").toString();
        String collectionDetails = map.get("collectionDetails").toString();
        type.setText(ctName);
        text.setText(collectionDetails);
        Glide.with(this).load(image.trim()).listener(mRequestListener).into(images);
    }

    RequestListener mRequestListener = new RequestListener() {
        @Override
        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
            Log.d("adawdawd", "onException: " + e.toString() + "  model:" + model + " isFirstResource: " + isFirstResource);
            images.setImageResource(R.mipmap.logos);
            return false;
        }

        @Override
        public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
            Log.e("", "model:" + model + " isFirstResource: " + isFirstResource);
            return false;
        }
    };

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
