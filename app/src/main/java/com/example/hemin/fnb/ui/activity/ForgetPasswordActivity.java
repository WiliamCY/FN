package com.example.hemin.fnb.ui.activity;

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
import com.example.hemin.fnb.ui.contract.ForgetContract;
import com.example.hemin.fnb.ui.presenter.ForgetPresenter;
import com.example.hemin.fnb.ui.util.ProgressDialog;
import com.example.hemin.fnb.ui.util.Utils;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseMvpActivity<ForgetPresenter> implements ForgetContract.View {
    @BindView(R.id.title_1)
    TextView title1;
    @BindView(R.id.title_2)
    TextView title2;
    @BindView(R.id.title_3)
    TextView title3;
    @BindView(R.id.c_phone)
    EditText cPhone;
    @BindView(R.id.title_4)
    TextView title4;
    @BindView(R.id.c_getCode)
    TextView cGetCode;
    @BindView(R.id.c_code)
    EditText cCode;
    @BindView(R.id.title_5)
    TextView title5;
    @BindView(R.id.title_6)
    ImageView title6;
    @BindView(R.id.c_passwords)
    EditText cPasswords;
    @BindView(R.id.title_7)
    TextView title7;
    @BindView(R.id.title_8)
    ImageView title8;
    @BindView(R.id.c_user_password)
    EditText cUserPassword;
    @BindView(R.id.c_register)
    TextView cRegister;
    @BindView(R.id.c_password)
    TextView cPassword;
    @BindView(R.id.c_login_button)
    Button cLoginButton;
    @BindView(R.id.c_wechat)
    ImageView cWechat;
    @BindView(R.id.qq)
    ImageView qq;
    @BindView(R.id.alipay)
    ImageView alipay;
    @BindView(R.id.select)
    ImageView select;
    @BindView(R.id.user_message)
    TextView userMessage;
    //    @BindView(R.id.backs)
//    ImageView backs;
    @BindView(R.id.image_see)
    ImageView imageSee;
    @BindView(R.id.image_sees)
    ImageView imageSees;
    @BindView(R.id.lay2)
    LinearLayout lay2;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.lay4)
    LinearLayout lay4;
    @BindView(R.id.view4)
    View view4;

    //    @BindView(R.id.number_down)
//    ImageView numberDown;
    @BindView(R.id.lay3)
    LinearLayout lay3;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.title_3_down)
    ImageView title3Down;
    @BindView(R.id.lay1)
    LinearLayout lay1;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.lay_back)
    LinearLayout layBack;
    @BindView(R.id.title)
    TextView title;
    private Utils.TimeCount timeCount;


    @Override
    public int getLayoutId() {
        return R.layout.activity_code_login;
    }


    @Override
    public void initView() {
        mPresenter = new ForgetPresenter();
        mPresenter.attachView(this);
        ButterKnife.bind(this);
        initViews();
    }


    private void initViews() {
        title1.setText("修改密码");
        cLoginButton.setText("提交");
        title3.setVisibility(View.GONE);
        title3Down.setVisibility(View.GONE);
//        numberDown.setVisibility(View.GONE);
        cRegister.setVisibility(View.GONE);
        cPassword.setVisibility(View.GONE);
        qq.setVisibility(View.GONE);
        cWechat.setVisibility(View.GONE);
        alipay.setVisibility(View.GONE);
        title5.setVisibility(View.VISIBLE);
        imageSees.setVisibility(View.VISIBLE);
        title7.setVisibility(View.VISIBLE);
        lay2.setVisibility(View.VISIBLE);
        lay4.setVisibility(View.VISIBLE);
        view2.setVisibility(View.VISIBLE);
        view4.setVisibility(View.VISIBLE);
        title.setVisibility(View.GONE);
//        cUserPassword.setVisibility(View.VISIBLE);
//        cPasswords.setVisibility(View.VISIBLE);

    }

    @OnClick({R.id.c_getCode, R.id.title_6, R.id.title_8, R.id.c_login_button, R.id.c_wechat, R.id.qq, R.id.alipay, R.id.lay_back, R.id.image_see, R.id.image_sees})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.c_getCode:
                if (getPhone() == null || Utils.isPhoneNumber(getPhone()) == false) {
                    Utils.showMyToast(Toast.makeText(this, "输入的手机号格式错误", Toast.LENGTH_SHORT), 400);

                    break;
                }
                mPresenter.getCode(getPhone());
                timeCount = new Utils.TimeCount(60000, 1000, cGetCode);
                timeCount.start();
                break;
            case R.id.title_6:
                Utils.PwdHide(cPasswords, title6, imageSee);
                break;
            case R.id.title_8:
                Utils.PwdHide(cUserPassword, title8, imageSees);
                break;
            case R.id.c_login_button:
                if (getCode() == null || getPhone() == null || getPassword() == null || getPasswords() == null || Utils.isPhoneNumber(getPhone()) == false) {

                    Utils.showMyToast(Toast.makeText(this, "请输入完整", Toast.LENGTH_SHORT), 400);
                    break;
                } else if (!getPassword().equals(getPasswords())) {
                    Utils.showMyToast(Toast.makeText(this, "输入的密码不一致", Toast.LENGTH_SHORT), 400);
                    break;
                }
                HashMap<String, String> paramsMap = new HashMap<>();
                paramsMap.put("mobile", getPhone());
                paramsMap.put("code", getCode());
                paramsMap.put("newPassword", getPassword());
                mPresenter.Forget(this, Utils.RetrofitHead(paramsMap));
                break;
            case R.id.c_wechat:
                break;
            case R.id.qq:
                break;
            case R.id.alipay:
                break;
            case R.id.lay_back:
                finish();
            case R.id.image_see:
                Utils.PwdShow(cPasswords, title6, imageSee);
                break;
            case R.id.image_sees:
                Utils.PwdShow(cUserPassword, title8, imageSees);
                break;
        }
    }

    private String getCode() {
        return cCode.getText().toString().trim();
    }

    private String getPhone() {
        return cPhone.getText().toString().trim();
    }

    private String getPassword() {
        return cPasswords.getText().toString().trim();
    }

    private String getPasswords() {
        return cUserPassword.getText().toString().trim();
    }

    @Override
    public void onSuccess(BaseObjectBean bean) {
        Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        ProgressDialog.getInstance().show(this);
    }

    @Override
    public void hideLoading() {
        ProgressDialog.getInstance().dismiss();
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
}
