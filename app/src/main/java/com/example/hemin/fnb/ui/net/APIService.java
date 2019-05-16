package com.example.hemin.fnb.ui.net;


import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.LoginBean;

import org.intellij.lang.annotations.Flow;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIService {

    /**
     * 登陆
     * @return
     */

    @GET("/user/sms/Login")
    Flowable<BaseObjectBean> login(@Query("mobile") String mobile,
                                              @Query("code") String code);

    /**
     * 获取验证码
     * */

    @GET("user/getCode")
    Flowable<BaseObjectBean> getCode(@Query("mobile") String mobile);


    /**
     * 注册
     * */
    @POST("user/save")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean> register(@Body RequestBody body);
}
