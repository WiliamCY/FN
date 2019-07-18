package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.ChangeLogoContract;
import com.example.hemin.fnb.ui.presenter.ChangeLogoPresenter;
import com.example.hemin.fnb.ui.util.MessageEvent;
import com.example.hemin.fnb.ui.util.Utils;
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class UserChangeLogo extends BaseMvpActivity<ChangeLogoPresenter> implements ChangeLogoContract.View {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;
    @BindView(R.id.change_user_logo)
    ImageView changeUserLogo;
    @BindView(R.id.change_button1)
    TextView changeButton1;
    @BindView(R.id.change_button2)
    TextView changeButton2;
    @BindView(R.id.lay_back)
    LinearLayout layBack;
    private String urls;


    @Override
    public int getLayoutId() {
        return R.layout.activity_change_logo;
    }

    @Override
    public void initView() {
        mPresenter = new ChangeLogoPresenter();
        mPresenter.attachView(this);
        String url = getIntent().getStringExtra("url");
        Glide.with(this).load(url).into(changeUserLogo);

    }

    @OnClick({R.id.lay_back, R.id.change_button1, R.id.change_button2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_back:
                finish();
                break;
            case R.id.change_button1:
                Intent intent = new Intent(this, PhotoSelectorActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent.putExtra("limit", 1);
                startActivityForResult(intent, 0);
                break;
            case R.id.change_button2:
                finish();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data != null) {
                    Map map = Utils.getAuthorization(this);
                    List<String> paths = (List<String>) data.getExtras().getSerializable("photos");//path是选择拍照或者图片的地址数组
                    Glide.with(this).load(paths.get(0)).into(changeUserLogo);
                    for (int i = 0; i < paths.size(); i++) {
                        String path = paths.get(i);
                        Log.d("pathsss:", path);
                        File file = new File(paths.get(i));
                        RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                        MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData("file", file.getName(), imageBody);
                        mPresenter.postImage(this, map, imageBodyPart);
                    }

                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {
        if (bean.getErrorCode() == 0) {
            finish();
        }
    }

    public void sendUrl(int status) {
        if (status == 1) {

            EventBus.getDefault().post(2, new MessageEvent(urls));
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

    public void getPostImageUrls(String url) {
        this.urls = url;
        Map token = Utils.getAuthorization(this);
        SharedPreferences sp = getSharedPreferences("userDate", MODE_PRIVATE);
        String id = sp.getString("userId", "");
        HashMap<String, String> map2 = new HashMap<>();
        map2.put("url", url);
        map2.put("userId", id);
        Log.d("ChageLogo", url + id);
        mPresenter.changeImage(this, token, Utils.RetrofitHead(map2));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
