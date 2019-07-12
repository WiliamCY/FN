package com.example.hemin.fnb.ui.activity;

import android.app.Application;
import android.graphics.Typeface;

//import com.example.hemin.fnb.MyEventBusIndex;
import com.example.hemin.fnb.MyEventBusIndex;
import com.example.hemin.fnb.ui.util.AppUtils;
import com.pgyersdk.PgyerActivityManager;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.crash.PgyerCrashObservable;
import com.pgyersdk.crash.PgyerObserver;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Field;

public class BaseApplication extends Application {
    public  static Typeface typeface;
    public static BaseApplication mContext;
    public static BaseApplication getInstance(){
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        setTypeface();
        EventBus.builder().addIndex(new MyEventBusIndex()).installDefaultEventBus();
        mContext = this;
        PgyCrashManager.register(); //推荐使用
        PgyerCrashObservable.get().attach(new PgyerObserver() {
            @Override
            public void receivedCrash(Thread thread, Throwable throwable) {

            }
        });
        PgyerActivityManager.set(this);
        AppUtils.setApplication(this);

    }
//    public void setTypeface(){
//        typeface = Typeface.createFromAsset(getAssets(), "fonts/zh-j.ttf");
//        try
//        {
//            Field field = Typeface.class.getDeclaredField("SERIF");
//            field.setAccessible(true);
//            field.set(null, typeface);
//        }
//        catch (NoSuchFieldException e)
//        {
//            e.printStackTrace();
//        }
//        catch (IllegalAccessException e)
//        {
//            e.printStackTrace();
//        }
    }


