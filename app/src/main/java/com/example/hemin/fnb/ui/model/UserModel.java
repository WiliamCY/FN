package com.example.hemin.fnb.ui.model;

import android.content.Context;

import com.example.hemin.fnb.ui.activity.UserSetting;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.contract.LoginContract;
import com.example.hemin.fnb.ui.contract.UserContract;
import com.example.hemin.fnb.ui.net.RetrofitClient;

import java.util.Map;

import io.reactivex.Flowable;

public class UserModel implements UserContract.Model {


    @Override
    public Flowable<BaseObjectBean> exit(Context context,Map map, String userId) {
        return RetrofitClient.getInstance().getApi().exit(map,userId);
    }
}
