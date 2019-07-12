package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;
import com.example.hemin.fnb.ui.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;
    @BindView(R.id.image)
    ImageView image;
    @BindView(R.id.titles)
    TextView titles;
    @BindView(R.id.r1)
    RelativeLayout r1;
    @BindView(R.id.r2)
    RelativeLayout r2;
    @BindView(R.id.r3)
    RelativeLayout r3;
    @BindView(R.id.r4)
    RelativeLayout r4;
    private String url1 = "http://www.funwl.com/#/clause1";
    private String url2 = "http://www.funwl.com/#/clause2";
    private String url3 = "http://www.funwl.com/#/clause3";


    @Override
    public int getLayoutId() {
        return R.layout.about;
    }

    @Override
    public void initView() {
        title.setText("关于玩鉴");
        titles.setText("版本号："+ Utils.getVerName(this));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @OnClick({R.id.back,R.id.r1, R.id.r2, R.id.r3, R.id.r4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.r1:
                Toast.makeText(this,"当前为最新版本!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.r2:
                Intent intent2 = new Intent(this,UserAbout.class);
                intent2.putExtra("path",url3);
                startActivity(intent2);
                break;
            case R.id.r3:
                Intent intent3 = new Intent(this,UserAbout.class);
                intent3.putExtra("path",url1);
                startActivity(intent3);
                break;
            case R.id.r4:
                Intent intent4 = new Intent(this,UserAbout.class);
                intent4.putExtra("path",url2);
                startActivity(intent4);
                break;
            case R.id.back:
                finish();
                break;
        }
    }
}
