package com.example.hemin.fnb.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

public class PlayViedeo extends BaseActivity {

    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard videoplayer;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.lay_back)
    LinearLayout layBack;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;
    private String path, StringContent;


    @Override
    public int getLayoutId() {
        return R.layout.activity_play_viedeo;
    }

    @Override
    public void initView() {

        path = this.getIntent().getStringExtra("urlpath");
        StringContent = getIntent().getStringExtra("StringContent");
        title.setText(StringContent);
        videoplayer.setUp(path, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
        Glide.with(this).load(path + "?x-oss-process=video/snapshot,t_5000,f_jpg,w_0,h_0,m_fast,ar_auto").into(videoplayer.thumbImageView);
        videoplayer.startVideo();


// Glide.with(this).load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640").into(videoplayer);


//        videoplayer.thumbImageView.setImageURI(Uri.parse("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640"));
//        videoplayer.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");


    }


    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back, R.id.lay_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                break;
            case R.id.lay_back:
                finish();
                break;
        }
    }
}
