package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;
import com.example.hemin.fnb.ui.util.HackyViewPager;
import com.github.chrisbanes.photoview.PhotoView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TaskBigImgActivityS extends BaseActivity {
    //    @BindView(R.id.titl1)
//    TextView headerTitle;
//    @BindView(R.id.header_left_img)
    ImageView headerLeftImg;
    @BindView(R.id.big_img_vp)
    HackyViewPager bigImgVp;
    //    @BindView(R.id.header_right_tv)
    TextView headerRightTv;
    @BindView(R.id.number1)
    TextView number1;
    @BindView(R.id.lay_back)
    LinearLayout layBack;
    @BindView(R.id.title)
    TextView title;


    private int position = 0;
    private ArrayList<String> paths;
    private long finderid, finderids;
    private String userId, userIds, StringContent, userUrl, nickName, isCollectionSum, isGiveNum;
    private Map token = new HashMap();
    private Boolean isGiveNumStatus = false;
    private Boolean FocuseStatus = false;

    @Override
    public int getLayoutId() {
        return R.layout.activity_task_big_imgs;
    }

    public void initView() {

        ButterKnife.bind(this);
        Intent intent = getIntent();
        paths = intent.getStringArrayListExtra("paths");
        String titles = intent.getStringExtra("title");
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        title.setText(titles);
        number1.setText(1 + "/" + paths.size());
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
                View adView = LayoutInflater.from(TaskBigImgActivityS.this).inflate(R.layout.item_big_imgs, null);
                PhotoView icon = (PhotoView) adView.findViewById(R.id.flaw_img);

//                icon.setBackgroundColor(getResources().getColor(R.color.c333333));
                Glide.with(TaskBigImgActivityS.this)
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
                number1.setText(position + 1 + "/" + paths.size());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.lay_back, R.id.titl1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_back:
                finish();
                break;
            case R.id.titl1:
                break;
        }
    }
}
