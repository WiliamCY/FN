package com.example.hemin.fnb.ui.net;


import com.example.hemin.fnb.ui.base.FindRankBean;
import com.example.hemin.fnb.ui.bean.AppraisaBean;
import com.example.hemin.fnb.ui.bean.AppraisasBean;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.ColletionBean;
import com.example.hemin.fnb.ui.bean.Find2Bean;
import com.example.hemin.fnb.ui.bean.Find4Bean;
import com.example.hemin.fnb.ui.bean.Find5Bean;
import com.example.hemin.fnb.ui.bean.FindDeilyBean;
import com.example.hemin.fnb.ui.bean.FindFirstBean;
import com.example.hemin.fnb.ui.bean.FindHuaBean;
import com.example.hemin.fnb.ui.bean.FindHuoListBean;
import com.example.hemin.fnb.ui.bean.FindLoveBean;
import com.example.hemin.fnb.ui.bean.GuanZhuBean;
import com.example.hemin.fnb.ui.bean.ImageUrlBean;
import com.example.hemin.fnb.ui.bean.LoginBean;
import com.example.hemin.fnb.ui.bean.MessageBean1;
import com.example.hemin.fnb.ui.bean.MessageBean2;
import com.example.hemin.fnb.ui.bean.MessageBean3;
import com.example.hemin.fnb.ui.bean.MessageImageBean;
import com.example.hemin.fnb.ui.bean.MessageImages;
import com.example.hemin.fnb.ui.bean.Mp4Bean;
import com.example.hemin.fnb.ui.bean.ReleaseBean;
import com.example.hemin.fnb.ui.bean.TypeBean;
import com.example.hemin.fnb.ui.bean.UserDateBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
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
    Flowable<BaseObjectBean<UserDateBean.DataBean>> pWlogin(@Body RequestBody body);
  /**
   * 藏品分类
   * */
  @GET("app/collection/getType")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean<List<TypeBean.DataBean>>> getType(@HeaderMap Map<String,String> heard);



    /**
     * 提交图鉴
     * */
    @POST("app/collection/add")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean>  submitImage (@HeaderMap Map<String,String> heard, @Body RequestBody body);

    /**
     * 正在鉴定
     * */
    @GET("app/collection/getMyCollection/{current}/{size}/{collectionAudit}/{userId}")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean<AppraisaBean.DataBean>>  appraisa (@HeaderMap Map<String,String> heard, @Path("current") long current,@Path("size") long size,@Path("collectionAudit") long collectionAudit,@Path("userId") long  userId );

    /**
     * 鉴定结束
     * */
    @GET("app/collection/getMyCollectionResults/{current}/{size}/{collectionAudit}/{userId}")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean<AppraisasBean.DataBean>>  appraisas(@HeaderMap Map<String,String> heard, @Path("current") long current, @Path("size") long size, @Path("collectionAudit") long collectionAudit, @Path("userId") long  userId );

    /**
     * 获取鉴定的详情
     * */
    @GET("app/collection/getCollection")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean<ColletionBean.DataBean>>  getColldetionMessage(@HeaderMap Map<String,String> heard, @Query("collectionId") String id );

    /**
     * 退出
     * */
    @GET("user/logout")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean>  exit(@HeaderMap Map<String,String> heard, @Query("userId") String id );


    /**
     * 活动
     * */
    @GET("app/activity/pageList/{current}/{size}")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean<FindHuoListBean.DataBean>>  pageListHuo(@HeaderMap Map<String,String> heard, @Path("current") long id, @Path("size") long size );



    /**
     * 活动详情
     * */
    @GET("app/activity/addNum/{activityId}/{userId}")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean>  addNum(@HeaderMap Map<String,String> heard, @Path("activityId") long activityId,@Path("userId") long userId );


    /**
     * 话题
     * */
    @GET("app/topic/pageList/{current}/{size}")
    Flowable<BaseObjectBean<Find2Bean.DataBean>>  addHua(@HeaderMap Map<String,String> heard, @Path("current") long current, @Path("size") long size);


    /**
     * 话题详情
     * */
    @GET("app/topic/addNum/{topicId}/{userId}")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean>  addHuas(@HeaderMap Map<String,String> heard, @Path("topicId") long topicId,@Path("userId") long userId );



    /**
     * 每日鉴赏
     * */
    @GET("app/collection/getDaily/{userId}")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean<FindDeilyBean.DataBean>>  getDaily(@HeaderMap Map<String,String> heard, @Path("userId") long userId );


    /**
     * 猜你喜欢
     * */
    @GET("app/collection/guessLove")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean<List<Find4Bean.DataBean>>>  guessLove(@HeaderMap Map<String,String> heard);


    /**
     * 点赞排行榜
     * */
    @GET("app/collection/getRankingList/{current}/{size}")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean<Find5Bean.DataBean>>  getRankingList(@HeaderMap Map<String,String> heard, @Path("current")long current, @Path("size") long size);




    /**
     * 点赞排行榜详情
     * */
    @GET("app/collection/getRanking")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean>  getRanking(@HeaderMap Map<String,String> heard,@Query("collectionId") long collectionId);


    /**
     * 杂志
     * */
    @GET("app/magazine/unpublished/{current}/{size}/{magazineType}")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean<MessageBean1.DataBean>>  getMaga(@HeaderMap Map<String,String> heard, @Path("current") long current, @Path("size") long size, @Path("magazineType") String name);



    /**
     * 推荐
     * */
    @GET("app/friend/pageList/{current}/{size}/{userId}")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean<MessageBean2.DataBean>>  getTuiJian(@HeaderMap Map<String,String> heard, @Path("current") long current, @Path("size") long size, @Path("userId") long userId);


    /**
     * 关注
     * */
    @GET("app/friend/getFocus/{current}/{size}/{userId}")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    Flowable<BaseObjectBean<MessageBean3.DataBean>>  getGuanzhu(@HeaderMap Map<String,String> heard, @Path("current") long current, @Path("size") long size, @Path("userId") long userId);



  /**
   * 获取朋友圈信息
   * */
  @GET("app/friend/info/{friendId}/{userId}")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Flowable<BaseObjectBean<MessageImageBean.DataBean>>  getFinder(@HeaderMap Map<String,String> heard, @Path("friendId") long friendId, @Path("userId") long userId);


  /**
   * 发送朋友圈
   * */
  @POST("app/friend/add")
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Flowable<BaseObjectBean> friendAdd(@HeaderMap Map<String,String> heard, @Body RequestBody body);


  /**
   * 关注朋友圈
   * */
  @PUT("app/friend/getFocus/{friendId}/{userId}")
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Flowable<BaseObjectBean> getFoucus(@HeaderMap Map<String,String> heard,@Path("friendId") long friendId, @Path("userId") long userId);




  /**
   * 点赞
   * */
  @PUT("app/friend/addLike/{friendId}/{userId}/{type}")
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Flowable<BaseObjectBean> getZan(@HeaderMap Map<String,String> heard,@Path("friendId") long friendId, @Path("userId") long userId,@Path("type") long type);


  /**
   * 我的关注
   * */
  @GET("app/friend/getFocusList/{current}/{size}/{userId}")
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Flowable<BaseObjectBean<GuanZhuBean.DataBean>> myGuanzhu(@HeaderMap Map<String,String> heard, @Path("current") long current, @Path("size") long size, @Path("userId") long userId);


  /**
   * 我的发布
   * */
  @GET("app/friend/myRelease/{current}/{size}/{userId}")
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Flowable<BaseObjectBean<ReleaseBean.DataBean>> myFabu(@HeaderMap Map<String,String> heard, @Path("current") long current, @Path("size") long size, @Path("userId") long userId);


  /**
   * 更换头像
   * */
  @PUT("app/user/updateImages")
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Flowable<BaseObjectBean> ChangeLogo(@HeaderMap Map<String,String> heard,@Body RequestBody body);

  /**
   * 图片上传
   * */
  @Multipart
  @POST("sys/upload/add")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Flowable<BaseObjectBean<ImageUrlBean.DataBean>> postImage(@HeaderMap Map<String,String> heard,@Part  MultipartBody.Part partList);

  /**
   * 取消关注
   * */
  @PUT("/app/friend/deleteFocus/{fuId}")
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Flowable<BaseObjectBean> Remove(@HeaderMap Map<String,String> heard,@Path("fuId") String fuId);


  /**
   * 修改个人信息
   * */
  @PUT("/app/user/update")
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Flowable<BaseObjectBean> UpdateAbout(@HeaderMap Map<String,String> heard ,@Body RequestBody body);


  /**
   * 绑换手机号
   * */
  @PUT("/app/user/updateMobile")
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Flowable<BaseObjectBean>  UpdateMobile(@HeaderMap Map<String,String> heard ,@Body RequestBody body);




  /**
   * 我的收藏
   * */
  @GET("/app/user/myCollection/{current}/{size}/{userId}")
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Flowable<BaseObjectBean>  MyCollect(@HeaderMap Map<String,String> heard , @Path("current") long current, @Path("size") long size, @Path("userId") long userId);




  /**
   * 点赞收藏想要
   * */
  @PUT("/app/collection/praise/{type}/{dailyId}/{userId}")
  @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Flowable<BaseObjectBean>  FindCollect(@HeaderMap Map<String,String> heard , @Path("type") long current, @Path("dailyId") long dailyId, @Path("userId") long userId);


//  /**
//   * 图片上传
//   * */
//  @Multipart
//  @POST("sys/upload/addMp4")
////    @Headers({ "Content-Type: application/json;charset=UTF-8"})
//  Flowable<BaseObjectBean<Mp4Bean.DataBean>> postMp4(@HeaderMap Map<String,String> heard,@Part("description") RequestBody description, @Part  MultipartBody.Part partList);
//}
  /**
   * 图片上传
   * */
  @Multipart
  @POST("sys/upload/addMp4")
//    @Headers({ "Content-Type: application/json;charset=UTF-8"})
  Flowable<BaseObjectBean<Mp4Bean.DataBean>> postMp4(@HeaderMap Map<String,String> heard,@Part("description") RequestBody description, @Part  MultipartBody.Part partList);
}
