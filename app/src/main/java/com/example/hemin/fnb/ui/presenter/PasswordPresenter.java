package com.example.hemin.fnb.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.hemin.fnb.ui.activity.PasswordActivity;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.UserDateBean;
import com.example.hemin.fnb.ui.contract.PwLoginContract;
import com.example.hemin.fnb.ui.fragment.TabMyFragment;
import com.example.hemin.fnb.ui.model.PasswordModel;
import com.example.hemin.fnb.ui.net.RxScheduler;
import com.example.hemin.fnb.ui.util.MyConsumer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.lang.reflect.Type;

import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

public class PasswordPresenter extends BasePresenter<PasswordActivity> implements PwLoginContract.Presenter {
    private PwLoginContract.Model model;

    public PasswordPresenter() {
        model = new PasswordModel();
    }

    @Override
    public void pwLogn(final Context context, RequestBody body) {
        if (!isViewAttached()) {
            return;
        }

        mView.showLoading();
        model.pwLogin(context, body)
                .compose(RxScheduler.<BaseObjectBean>Flo_io_main())
                .as(mView.<BaseObjectBean>bindAutoDispose())
                .subscribe(new MyConsumer<UserDateBean>() {

                    @Override
                    public void accept(UserDateBean bean, boolean success, BaseObjectBean baseObjectBean) {
                        // code == 0 bean 是重新处理以后的数据
                        mView.onSuccess(baseObjectBean);
                        mView.hideLoading();
//                        UserDateBean result = gson.fromJson(jsonObject.getJSONObject("data").toString(), UserDateBean.class);
                        String Authorization = bean.getAuthorization();
                        String tokenType = bean.getToken_type();
                        String expiresIn = bean.getExpires_in();
                        UserDateBean.User user = bean.getUser();
                        String userId = user.getUserId();
                        String nickName = user.getNickname();
                        String url = user.getUrl();
                        String mobile = user.getMobile();
                        String birthday = user.getBirthday();
                        String signature = user.getSignature();
                        String sex = user.getSex();
                        SharedPreferences.Editor editor = context.getSharedPreferences("userDate", Context.MODE_PRIVATE).edit();
                        editor.putString("Authorization", Authorization);
                        editor.putString("tokenType", tokenType);
                        editor.putString("expiresIn", expiresIn);
                        editor.putString("userId", userId);
                        editor.putString("nickName", nickName);
                        editor.putString("url", url);
                        editor.putString("mobile", mobile);
                        editor.putString("birthday", birthday);
                        editor.putString("signature", signature);
                        editor.putString("sex", sex);
                        editor.putBoolean("key", false);
                        editor.commit();
                    }

                    @Override
                    public void accept_error(String bean, boolean success, String message) {
                        //不是 code == 0 失败
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.onError(throwable);
                        mView.hideLoading();
                    }
                });
    }
}
