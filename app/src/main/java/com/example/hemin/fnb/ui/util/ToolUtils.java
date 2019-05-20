package com.example.hemin.fnb.ui.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.ActivityInfo;
import android.content.res.TypedArray;

import java.lang.reflect.Method;
import java.util.List;

public class ToolUtils {

    /**
     * 判断list是否为空
     * @param list
     * @return
     */
    public static boolean isEmpty(List list){
        return list == null || list.size()==0;
    }


    public static Activity getActivity(Context context) {
        // Gross way of unwrapping the Activity so we can get the FragmentManager
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity)context;
            }
            context = ((ContextWrapper)context).getBaseContext();
        }
        throw new IllegalStateException("The MediaRouteButton's Context is not an Activity.");
    }


    /**
     * 是否是主进程
     */
    public static boolean isMainProcess(Context context){
        return context.getApplicationContext().getPackageName().equals
                (getCurrentProcessName(context));
    }

    /**
     * 获取当前进程名
     */
    public static String getCurrentProcessName(Context context) {

        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager manager = (ActivityManager) context.getSystemService
                (Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo process : manager.getRunningAppProcesses()) {
            if (process.pid == pid) {
                processName = process.processName;
            }
        }
        return processName;
    }

    public static boolean checkNoNull(Object o){
        return  o!= null;
    }

    public static boolean checkNoZero(int o){
        return  o!= 0;
    }

    public static boolean isImageFormat(String fileName){
        return fileName.endsWith(".jpeg")|| fileName.endsWith(".png") || fileName.endsWith(".jpg")|| fileName.equals(".gif")||
                fileName.endsWith(".GIF") || fileName.endsWith(".JPG")|| fileName.equals(".PNG");
    }

    public static boolean isTranslucentOrFloating(Activity activity){
        boolean isTranslucentOrFloating = true;
        try {
            int [] styleableRes = (int[]) Class.forName("com.android.internal.R$styleable").getField("Window").get(null);
            final TypedArray ta = activity.obtainStyledAttributes(styleableRes);
            Method m = ActivityInfo.class.getMethod("isTranslucentOrFloating", TypedArray.class);
            m.setAccessible(true);
            isTranslucentOrFloating = (boolean)m.invoke(null, ta);
            m.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isTranslucentOrFloating;
    }

}
