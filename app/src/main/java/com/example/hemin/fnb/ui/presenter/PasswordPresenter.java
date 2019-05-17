package com.example.hemin.fnb.ui.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.hemin.fnb.ui.activity.PasswordActivity;
import com.example.hemin.fnb.ui.base.BasePresenter;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.UserDateBean;
import com.example.hemin.fnb.ui.contract.PwLoginContract;
import com.example.hemin.fnb.ui.fragment.TabMyFragment;
import com.example.hemin.fnb.ui.model.PasswordModel;
import com.example.hemin.fnb.ui.net.RxScheduler;
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
                .subscribe(new Consumer<BaseObjectBean>() {
                    @Override
                    public void accept(BaseObjectBean bean) throws Exception {
                        mView.onSuccess(bean);
                        mView.hideLoading();

                        //UserDateBean result = (UserDateBean) bean.getResult();
                        Gson gson = new Gson();
                        String bean2 = gson.toJson(bean);
                        try {
                            JSONObject jsonObject = new JSONObject(bean2);
                            UserDateBean result = gson.fromJson(jsonObject.getJSONObject("data").toString(), UserDateBean.class);
                            String Authorization = result.getAuthorization();
                            String tokenType = result.getToken_type();
                            String expiresIn = result.getExpires_in();
//                            UserDateBean.DataBean.UserBean user = result.getUser();
//                            int userId = user.getUserId();
//                            String nickName = user.getNickname();
//                            String url = user.getUrl();
//                            String mobile = user.getMobile();
//                            String birthday = user.getBirthday();
//                            String signature = user.getSignature();
//                            String sex = user.getSex();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        Intent intent = new Intent(context, TabMyFragment.class);
                        context.startActivity(intent);

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
