package com.example.hemin.fnb.ui.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


public class StatusUtils {


    /**
     * Set the status bar to dark.
     */
    public static boolean setStatusBarDarkFont(Activity activity, boolean darkFont) {
        if (setMIUIStatusBarFont(activity, darkFont)) {
            setDefaultStatusBarFont(activity, darkFont);
            return true;
        } else if (setMeizuStatusBarFont(activity, darkFont)) {
            setDefaultStatusBarFont(activity, darkFont);
            return true;
        } else {
            return setDefaultStatusBarFont(activity, darkFont);
        }
    }

    private static boolean setMeizuStatusBarFont(Activity activity, boolean darkFont) {
        try {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (darkFont) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(lp, value);
            activity.getWindow().setAttributes(lp);
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    private static boolean setMIUIStatusBarFont(Activity activity, boolean dark) {
        Window window = activity.getWindow();
        Class<?> clazz = window.getClass();
        try {
            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            if (dark) {
                extraFlagField.invoke(window, darkModeFlag, darkModeFlag);
            } else {
                extraFlagField.invoke(window, 0, darkModeFlag);
            }
            return true;
        } catch (Exception ignored) {
        }
        return false;
    }

    private static boolean setDefaultStatusBarFont(Activity activity, boolean dark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Window window = activity.getWindow();
            View decorView = window.getDecorView();
            if (dark) {
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                decorView.setSystemUiVisibility(decorView.getSystemUiVisibility() & ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
            return true;
        }
        return false;
    }

    /**
     * 设置白色状态栏
     * 暂时解决方案是6.0以上版本支持反色
     * 而在5.0-6.0采用渐变色状态栏
     *
     * @param activity
     */
    public static void setWhiteStatusBar(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //判断是否发色成功
            boolean isDark = setStatusBarDarkFont(activity,true);
            if(!isDark) {
                View decorView = activity.getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
                LinearLayout replaceLayout = new LinearLayout(activity);
                replaceLayout.setOrientation(LinearLayout.VERTICAL);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                replaceLayout.setLayoutParams(params);
                StatusView statusView = new StatusView(activity);
                statusView.setBackgroundColor(Color.WHITE);
                statusView.setStatusGradient(true);
                replaceLayout.addView(statusView);
                ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
                ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
                TypedArray a = activity.getTheme().obtainStyledAttributes(new int[]{
                        android.R.attr.windowBackground
                });
                int background = a.getResourceId(0, 0);
                a.recycle();
                decorChild.setBackgroundResource(background);
                decor.removeView(decorChild);
                decorChild.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                replaceLayout.addView(decorChild);
                decor.addView(replaceLayout);
            }else{
                activity.getWindow().setStatusBarColor(Color.WHITE);
            }
        }
    }

    /**
     * 设置黑色状态栏
     * 暂时解决方案是6.0以上版本支持反色
     * 而在5.0-6.0采用渐变色状态栏
     *
     * @param activity
     */
    public static void setBlackStatusBar(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //判断是否发色成功
            boolean isDark = setStatusBarDarkFont(activity,false);
            if(!isDark) {
                View decorView = activity.getWindow().getDecorView();
                int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
                decorView.setSystemUiVisibility(option);
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
                LinearLayout replaceLayout = new LinearLayout(activity);
                replaceLayout.setOrientation(LinearLayout.VERTICAL);
                ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                replaceLayout.setLayoutParams(params);
                StatusView statusView = new StatusView(activity);
                statusView.setBackgroundColor(Color.BLACK);
                statusView.setStatusGradient(true);
                replaceLayout.addView(statusView);
                ViewGroup decor = (ViewGroup) activity.getWindow().getDecorView();
                ViewGroup decorChild = (ViewGroup) decor.getChildAt(0);
                TypedArray a = activity.getTheme().obtainStyledAttributes(new int[]{
                        android.R.attr.windowBackground
                });
                int background = a.getResourceId(0, 0);
                a.recycle();
                decorChild.setBackgroundResource(background);
                decor.removeView(decorChild);
                decorChild.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
                replaceLayout.addView(decorChild);
                decor.addView(replaceLayout);
            }else{
                activity.getWindow().setStatusBarColor(Color.BLACK);
            }
        }
    }



    public static void setFullScreen(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /**
     * 隐藏状态栏
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void hideStatusBar(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0
            View decorView = activity.getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE);
        }
    }



    /**
     * 显示状态栏
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public static void showStatusBar(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0
            View decorView = activity.getWindow().getDecorView();
            if(decorView != null && decorView.getSystemUiVisibility() != View.SYSTEM_UI_FLAG_VISIBLE) {
                decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
            }
        }
    }


}
