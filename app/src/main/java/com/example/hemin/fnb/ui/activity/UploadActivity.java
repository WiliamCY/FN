package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UploadActivity extends BaseMvpActivity {
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.image_1)
    ImageView image1;
    @BindView(R.id.text1)
    TextView text1;
    @BindView(R.id.image_2)
    ImageView image2;
    @BindView(R.id.title2)
    TextView title2;
    @BindView(R.id.image_3)
    ImageView image3;
    @BindView(R.id.text3)
    TextView text3;
    @BindView(R.id.upload_dissmiss)
    ImageView uploadDissmiss;

    @Override
    public int getLayoutId() {
        return R.layout.upload_activity;
    }

    @Override
    public void initView() {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_1, R.id.image_2, R.id.image_3, R.id.upload_dissmiss})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_1:
                Intent intent = new Intent(this,MessageAdd.class);
                 startActivity(intent);

                break;
            case R.id.image_2:
                Intent intent2 = new Intent(this,MediaRecordActivity.class);
                startActivity(intent2);
                break;
            case R.id.image_3:
                Intent intent3 = new Intent(this,PublishingCollections.class);
                startActivity(intent3);
                finish();
                break;
            case R.id.upload_dissmiss:
                Intent intent4 = new Intent(UploadActivity.this,MainActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
