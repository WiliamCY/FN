package com.example.hemin.fnb.ui.net;


import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.LoginBean;
import com.example.hemin.fnb.ui.bean.TypeBean;
import com.example.hemin.fnb.ui.bean.UserDateBean;

import org.intellij.lang.annotations.Flow;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.HeaderMap;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APIService {

    /**
     * 登陆
     * @return
     */

    @GET("user/sms/Login")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean> login(@Query("mobile") String mobile,
                                   @Query("code") String code);

    /**
     * 登陆
     * @return
     */

    @GET("user/sms/Login")
    Flowable<BaseObjectBean<LoginBean>> logins(@Query("mobile") String mobile,
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

  /**
   * 忘记密码
   * */
  @PUT("user/updatePwd")
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean> forget(@Body RequestBody body);

  /**
   * 登录
   * */
  @POST("user/login")
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean> pWlogin(@Body RequestBody body);
  /**
   * 藏品分类
   * */
  @GET("app/collection/getType")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean<List<TypeBean.DataBean>>> getType(@HeaderMap Map<String,String> heard);


    /**
     * 图片上传
     * */
    @Multipart
    @POST("sys/upload/add")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean> postImage(@HeaderMap Map<String,String> heard,@Part  MultipartBody.Part partList);

    /**
     * 提交图鉴
     * */
    @POST("app/collection/add")
    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean>  submitImage (@Body RequestBody body);
}