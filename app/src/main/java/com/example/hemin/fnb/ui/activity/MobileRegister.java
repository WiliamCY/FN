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
import com.example.hemin.fnb.ui.contract.MobileRegisterContract;
import com.example.hemin.fnb.ui.presenter.UpdateMobilePresenter;
import com.example.hemin.fnb.ui.util.Utils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MobileRegister extends BaseMvpActivity<UpdateMobilePresenter> implements MobileRegisterContract.View {
    @BindView(R.id.back)
    ImageView back;

    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;
    @BindView(R.id.cm_title1)
    TextView cmTitle1;
    @BindView(R.id.cm_title2)
    TextView cmTitle2;
    @BindView(R.id.cm_title3)
    TextView cmTitle3;
    @BindView(R.id.cm_title4)
    TextView cmTitle4;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.t1)
    TextView t1;
    @BindView(R.id.t2)
    EditText t2;
    @BindView(R.id.t3)
    EditText t3;
    @BindView(R.id.b1)
    TextView b1;
    @BindView(R.id.b2)
    Button b2;
    @BindView(R.id.lay_back)
    LinearLayout layBack;
    private String mobile, userId;
    private Map token = new HashMap<>();
    private Utils.TimeCount timeCount;

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_mobile;
    }

    @Override
    public void initView() {
        mPresenter = new UpdateMobilePresenter();
        mPresenter.attachView(this);
        title.setText("绑定手机号");
        SharedPreferences sp = getSharedPreferences("userDate", Context.MODE_PRIVATE);
        mobile = sp.getString("mobile", "");
        t1.setText(mobile);
        userId = sp.getString("userId", "");
        token = Utils.getAuthorization(this);
    }


    @OnClick({R.id.lay_back, R.id.b1, R.id.b2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.lay_back:
                finish();
                break;
            case R.id.b2:
                if (getNewMobile().equals("") || !Utils.isPhoneNumber(getNewMobile()) || getCode().equals("")) {
                    Toast.makeText(this, "请输入完整", Toast.LENGTH_SHORT).show();
                } else {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("code", getCode());
                    map.put("mobile", getNewMobile());
                    map.put("userId", userId);
                    mPresenter.updateMobile(token, Utils.RetrofitHead(map), 1);
                }
                break;
            case R.id.b1:
                if (getNewMobile().equals("") || !Utils.isPhoneNumber(getNewMobile())) {
                    Toast.makeText(this, "输入的手机号有误", Toast.LENGTH_SHORT).show();
                } else {
                    mPresenter.getCode(getNewMobile(), 0);
                    timeCount = new Utils.TimeCount(60000, 1000, b1);
                    timeCount.start();
                }
                break;
        }
    }

    private String getNewMobile() {
        return t2.getText().toString().trim();
    }

    private String getCode() {
        return t3.getText().toString().trim();
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
    public void onSuccess(BaseObjectBean bean, int status) {
        if (bean.getErrorCode() == 0 && status == 1) {
            SharedPreferences.Editor sp = getSharedPreferences("userDate", Context.MODE_PRIVATE).edit();
            sp.putString("mobile", getNewMobile());
            sp.commit();
            finish();
        } else {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
