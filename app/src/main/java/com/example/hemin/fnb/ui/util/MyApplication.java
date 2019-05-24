package com.example.hemin.fnb.ui.util;


import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {

    private static List<Activity> activityList = new LinkedList<Activity>();
    private static MyApplication instance;

    public MyApplication() {
    }

    // 单例模式中获取唯一的MyApplication实例
    public static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        activityList.add(activity);
    }

    // 遍历所有Activity并finish
    public static void exit() {
        for (Activity activity : activityList) {
//            RLog.i("-aa_aa-", "destroy all activity: " + activity.toString() + " !!!");
            activity.finish();
        }
        System.exit(0);
    }
}