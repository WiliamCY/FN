package com.example.hemin.fnb.ui.activity;

import android.app.Application;

import com.pgyersdk.PgyerActivityManager;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.crash.PgyerCrashObservable;
import com.pgyersdk.crash.PgyerObserver;

public class BaseApplication extends Application {
    public static BaseApplication mContext;
    public static BaseApplication getInstance(){
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        PgyCrashManager.register(); //推荐使用
        PgyerCrashObservable.get().attach(new PgyerObserver() {
            @Override
            public void receivedCrash(Thread thread, Throwable throwable) {

            }
        });
        PgyerActivityManager.set(this);

    }
}
