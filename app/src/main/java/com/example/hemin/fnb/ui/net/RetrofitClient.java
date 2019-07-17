package com.example.hemin.fnb.ui.net;


import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.hemin.fnb.BuildConfig;
import com.example.hemin.fnb.ui.activity.PasswordActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.util.AppUtils;
import com.example.hemin.fnb.ui.util.MessageEvent;
import com.example.hemin.fnb.ui.util.Utils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {

    private static volatile RetrofitClient instance;
    private APIService apiService;
    //    private static String baseUrl = "http://10.10.10.66:8080/";
//    private static  String baseUrl = "https://www.funwl.com/";
//    private static String baseUrl = "https://www.funwl.com/";
    private static String baseUrl = "http://ceshi.funwl.com:8443/";
    //private static String baseUrl = "http://qq1273106281.oicp.io:47141/";
//    private String baseUrl = "http://245i03m374.wicp.vip:51052/";
    private static Context context;


    private RetrofitClient() {
    }

    public static RetrofitClient getInstance() {
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    /**
     * 设置Header
     *
     * @return
     */
    private Interceptor getHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                Request.Builder requestBuilder = original.newBuilder();
                //添加Token
//                        .header("usERa", "application/json;charset=UTF-8");
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

    }

    /**
     * 设置拦截器
     *
     * @return
     */
    private Interceptor getInterceptor() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (BuildConfig.DEBUG) {
                    Log.i("OKHttps", message);

                }
            }
        });
        //显示日志
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    public APIService getApi() {
        //初始化一个client,不然retrofit会自己默认添加一个
        OkHttpClient client = new OkHttpClient().newBuilder()
                //设置Header
                .addInterceptor(getHeaderInterceptor())
//                .addInterceptor(new MyInterceptor())
                //设置拦截器
                .addInterceptor(getInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                //设置网络请求的Url地址
                .baseUrl(baseUrl)
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                //设置网络请求适配器，使其支持RxJava与RxAndroid
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //创建—— 网络请求接口—— 实例
        apiService = retrofit.create(APIService.class);
        return apiService;
    }

    public static class MyInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Connection", "close").build();
//            long t1 = System.nanoTime();
            Response response = chain.proceed(request);
            String result = response.body().string();
            BaseObjectBean baseBean = new Gson().fromJson(result, BaseObjectBean.class);
            Log.i("retrofitCode", "baseGetError" + baseBean.getResult());
            if (baseBean.getCode() == 99993) {
                context = AppUtils.getContext();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Looper.prepare();
                        Toast.makeText(context, "登录失效，请重新登录", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }).start();

                Intent intent = new Intent(context, PasswordActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
            return chain.proceed(request);
        }
    }


}
