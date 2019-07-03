package com.example.hemin.fnb.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.FoucesContract;
import com.example.hemin.fnb.ui.presenter.FoucrsPresenter;
import com.example.hemin.fnb.ui.util.CircleImageView;
import com.example.hemin.fnb.ui.util.HackyViewPager;
import com.example.hemin.fnb.ui.util.Utils;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.HashMap;
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
    HackyViewPager bigImgVp;
    TextView headerRightTv;
    @BindView(R.id.user_logo)
    CircleImageView userLogo;
    @BindView(R.id.titleUser)
    TextView titleUser;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.zan)
    ImageView zan;
    private int position = 0;
    private ArrayList<String> paths;
    private long finderid, finderids;
    private String userId, userIds, StringContent, userUrl, nickName,isCollectionSum,isGiveNum ;
    private Map token = new HashMap();
    private Boolean isGiveNumStatus = false;
    private Boolean FocuseStatus = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_task_big_img;
    }

    @SuppressLint("ResourceAsColor")
    public void initView() {
        mPresenter = new FoucrsPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        Intent intent = getIntent();
        SharedPreferences sp = getSharedPreferences("userDate", Context.MODE_PRIVATE);
        paths = intent.getStringArrayListExtra("paths");
        String titles = intent.getStringExtra("title");
        userId = intent.getStringExtra("userId");
        finderid = intent.getLongExtra("finderid", 0);
        StringContent = intent.getStringExtra("StringContent");
        userUrl = intent.getStringExtra("userUrl").trim();
        nickName = intent.getStringExtra("nickName");
        isCollectionSum = intent.getStringExtra("isCollectionSum");
        isGiveNum = intent.getStringExtra("isGiveNum");
        titleUser.setText(nickName);
        title.setText(StringContent);
        Glide.with(this).load(userUrl).into(userLogo);
        Log.d("TaskFinderId", String.valueOf(finderid));
        headerTitle.setText(titles);
        headerLeftImg.setVisibility(View.VISIBLE);
        userIds = sp.getString("userId", "");
        if (userIds.equals(userId)) {
            headerTitle.setVisibility(View.GONE);
        } else {
            headerTitle.setVisibility(View.VISIBLE);
        }
        if(isCollectionSum != null){
            if(isCollectionSum.equals("1")){
                FocuseStatus = false;
                headerTitle.setText("已关注");
                headerTitle.setBackgroundResource(R.drawable.shape_yellows);
                headerTitle.setTextColor(R.color.c999999);

            }else if(isCollectionSum.equals("2")){
                headerTitle.setText("关注");
                headerTitle.setBackgroundResource(R.drawable.shape_yellow);
                headerTitle.setTextColor(R.color.c333333);
                FocuseStatus = true;

        }else {
                headerTitle.setVisibility(View.GONE);
            }
        }
        if(isGiveNum.equals("1")){
            isGiveNumStatus = true;
            zan.setBackgroundResource(R.mipmap.zan_black);
        }else{
            isGiveNumStatus = false;
            zan.setBackgroundResource(R.mipmap.white_zan);
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
//                TextView StringContents = adView.findViewById(R.id.title);
                headerRightTv = adView.findViewById(R.id.header_right_tv);
                headerRightTv.setText(1 + "/" + paths.size());
//                zan = adView.findViewById(R.id.zan);
//                zan.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Map token = Utils.getAuthorization(getApplication());
//                        mPresenter.getZan(getApplication(), token, finderid, Long.parseLong(userIds), 0);
//                    }
//                });
//                StringContents.setText(StringContent);
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
//
//    @OnClick(R.id.header_left_img)
//    public void onClick() {
//        finish();
//    }

    @Override
    public void onSuccess(BaseObjectBean bean, int status) {
//        if (status == 0 && bean.getErrorCode() == 0) {
//            headerTitle.setText("已关注");
//        } else if (status == 1) {
//            zan.setBackgroundResource(R.mipmap.zan_black);
//        }

    }
  public void  zanResult(int status){

         if(status == 0){
             if(isGiveNumStatus == false){
                 zan.setBackgroundResource(R.mipmap.zan_black);
                 isGiveNumStatus = true;
             }else {
                 zan.setBackgroundResource(R.mipmap.white_zan);
                 isGiveNumStatus = false;
             }
         }
  }
  @SuppressLint("ResourceAsColor")
  public  void  FoucesStatus(int status){
   if(status == 0){
       if(FocuseStatus == false){
           headerTitle.setText("关注");
           headerTitle.setBackgroundResource(R.drawable.shape_yellow);
           headerTitle.setTextColor(R.color.c333333);
           FocuseStatus = true;
       }else {
           headerTitle.setText("已关注");
           headerTitle.setBackgroundResource(R.drawable.shape_yellows);
           headerTitle.setTextColor(R.color.c999999);
         FocuseStatus = false;
       }
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

    @OnClick({R.id.titl1,R.id.zan,R.id.header_left_img,R.id.collect})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.titl1:
                 token = Utils.getAuthorization(this);
                mPresenter.Fouces(this, token, finderid, Long.parseLong(userIds));
                Log.d("dwdwagtdjhdr", String.valueOf(finderid));
                Log.d("dwdwagtdjhdrs", userIds);

                break;
            case R.id.zan:
                token = Utils.getAuthorization(getApplication());
                        mPresenter.getZan(getApplication(), token, finderid, Long.parseLong(userIds), 0);
                        break;
            case R.id.header_left_img:
                finish();
                break;
            case R.id.collect:


        }
    }
}
