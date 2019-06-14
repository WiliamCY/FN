package com.example.hemin.fnb.ui.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.UpdateAboutContract;
import com.example.hemin.fnb.ui.presenter.FixNickNameAboutPresenter;
import com.example.hemin.fnb.ui.presenter.RegisterPresenter;
import com.example.hemin.fnb.ui.presenter.UpdateAboutPresenter;
import com.example.hemin.fnb.ui.util.Utils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FixNickName extends BaseMvpActivity<FixNickNameAboutPresenter> implements UpdateAboutContract.View {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;
    @BindView(R.id.e1)
    EditText e1;
    @BindView(R.id.e2)
    EditText e2;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.fixNickName)
    Button fixNickName;
    private Map<String,String> token = new HashMap<>();
    private int status;

    private String birthday,nicknames,signature,url,userid,sex;

    @Override
    public int getLayoutId() {
        return R.layout.activity_fix_nickname;
    }

    @Override
    public void initView() {
        mPresenter = new FixNickNameAboutPresenter();
        mPresenter.attachView(this);
         status = getIntent().getIntExtra("status", 0);
        if (status == 0) {
            e1.setVisibility(View.VISIBLE);
            e2.setVisibility(View.GONE);
            title.setText("修改昵称");
        } else {
            e1.setVisibility(View.GONE);
            e2.setVisibility(View.VISIBLE);
            title.setText("修改签名");
        }
        token = Utils.getAuthorization(this);
        SharedPreferences sp  = getSharedPreferences("userDate", Context.MODE_PRIVATE);
        birthday = sp.getString("birthday","");
        nicknames = sp.getString("nickName","");
        sex = sp.getString("sex","");
        signature = sp.getString("signature","");
        url = sp.getString("url","");
        userid = sp.getString("userId","");



    }


    @OnClick({R.id.back,R.id.fixNickName})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.fixNickName:
                if(status == 0) {
                    if (getNick().equals("") || getNick().length()<4 || getNick().length()>24 ) {
                        Utils.showMyToast(Toast.makeText(this, "请输入完整或者输入昵称字数不正确", Toast.LENGTH_SHORT), 400);
                    } else {
                        HashMap<String, String> map = new HashMap<>();
//                         map.put("birthday",birthday);
                        map.put("nickname", getNick());
//                         map.put("sex",sex);
//                         map.put("signature",signature);
//                         map.put("url",url);
                        map.put("userId", userid);
                        mPresenter.updateAbout(token, Utils.RetrofitHead(map), 1);

                    }
                }else {
                    if (getNicks().equals("") || getNicks().length()>100) {
                        Utils.showMyToast(Toast.makeText(this, "请输入完整或者输入签名超过100字数", Toast.LENGTH_SHORT), 400);
                             return;
                    } else {
                        HashMap<String, String> map = new HashMap<>();
//                         map.put("birthday",birthday);
                        map.put("nickname", nicknames);
//                         map.put("sex",sex);
                         map.put("signature",getNicks());
//                         map.put("url",url);
                        map.put("userId", userid);
                        mPresenter.updateAbout(token, Utils.RetrofitHead(map), 2);

                    }
                }
                break;

        }
    }
   public  String getNick(){
        return  e1.getText().toString().trim();
   }

    public  String getNicks(){
        return  e2.getText().toString().trim();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
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
    public void onSuccess(BaseObjectBean bean,int status) {
        if(bean.getCode() == 0 && status == 1){
            EventBus.getDefault().post(1,getNick());
            SharedPreferences.Editor sp = this.getSharedPreferences("userDate",Context.MODE_PRIVATE).edit();
             sp.putString("nickName",getNick());
             sp.commit();
             finish();

        }else if(bean.getCode() == 0 && status == 2){
            EventBus.getDefault().post(3,getNicks());
            SharedPreferences.Editor sp = this.getSharedPreferences("userDate",Context.MODE_PRIVATE).edit();
            sp.putString("signature",getNicks());
            sp.commit();
            finish();
        }else {
            Toast.makeText(this,bean.getErrorMsg(),Toast.LENGTH_SHORT).show();
        }
        }

    }



