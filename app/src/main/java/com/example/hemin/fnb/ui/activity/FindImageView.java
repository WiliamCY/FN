package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.util.LoadImagesTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindImageView extends AppCompatActivity {

    @BindView(R.id.find_image)
    ImageView findImage;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_imageview);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String url = intent.getStringExtra("image");
        new LoadImagesTask(findImage).execute(url);
    }

    @OnClick({R.id.back, R.id.user_toolbar})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;

        }
    }
}
