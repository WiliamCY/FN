package com.example.hemin.fnb.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.FoucesContract;
import com.example.hemin.fnb.ui.presenter.FoucrsPresenter;
import com.example.hemin.fnb.ui.util.Utils;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskBigImgActivity extends BaseMvpActivity<FoucrsPresenter> implements FoucesContract.View {
    @BindView(R.id.titl1)
    TextView headerTitle;
    @BindView(R.id.header_left_img)
    ImageView headerLeftImg;
    @BindView(R.id.big_img_vp)
    ViewPager bigImgVp;
    @BindView(R.id.header_right_tv)
    TextView headerRightTv;
    private int position = 0;
    private ArrayList<String> paths;
    private long finderid, finderids;
    private String userId, userIds,StringContent;
    private ImageView zan;

    @Override
    public int getLayoutId() {
        return R.layout.activity_task_big_img;
    }

    public void initView() {
        mPresenter = new FoucrsPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        SharedPreferences sp = getSharedPreferences("userDate", Context.MODE_PRIVATE);
//        position = intent.getIntExtra("position", 0);
        paths = intent.getStringArrayListExtra("paths");
        String title = intent.getStringExtra("title");
        userId = intent.getStringExtra("userId");
        finderid = intent.getLongExtra("finderid", 0);
        StringContent = intent.getStringExtra("StringContent");
        Log.d("TaskFinderId", String.valueOf(finderid));
        headerTitle.setText(title);
        headerLeftImg.setVisibility(View.VISIBLE);
        headerRightTv.setVisibility(View.VISIBLE);
        headerRightTv.setText(1 + "/" + paths.size());
        userIds = sp.getString("userId", "");
        if (userIds.equals(userId)) {
            headerTitle.setVisibility(View.GONE);
        } else {
            headerTitle.setVisibility(View.VISIBLE);
        }
        bigImgVp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return paths == null ? 0 : paths.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                Log.d("messaePaths", paths.toString());
                View adView = LayoutInflater.from(TaskBigImgActivity.this).inflate(R.layout.item_big_img, null);
                PhotoView icon = (PhotoView) adView.findViewById(R.id.flaw_img);
               TextView StringContents = adView.findViewById(R.id.title);
                 zan = adView.findViewById(R.id.zan);
                 zan.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Map token = Utils.getAuthorization(getApplication());
                         mPresenter.getZan(getApplication(),token,finderid, Long.parseLong(userIds),0);
                     }
                 });
               StringContents.setText(StringContent);
                icon.setBackgroundColor(getResources().getColor(R.color.c333333));
                Glide.with(TaskBigImgActivity.this)
                        .load(paths.get(position).trim())
                        .into(icon);
                container.addView(adView);
                return adView;
            }
            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((View) object);
            }
        });

        bigImgVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                headerRightTv.setText(position + 1 + "/" + paths.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
//
//        bigImgVp.setCurrentItem(position, false);

    }

    @OnClick(R.id.header_left_img)
    public void onClick() {
        finish();
    }

    @Override
    public void onSuccess(BaseObjectBean bean,int status) {
        if(status == 0 && bean.getErrorCode() == 0){
            headerTitle.setText("已关注");
        }else if(status == 1){
                zan.setBackgroundResource(R.mipmap.zan_black);
        }

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

    @OnClick({R.id.titl1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.titl1:
                Map token = Utils.getAuthorization(this);
                mPresenter.Fouces(this, token, finderid, Long.parseLong(userIds));
                break;
        }
    }
}
