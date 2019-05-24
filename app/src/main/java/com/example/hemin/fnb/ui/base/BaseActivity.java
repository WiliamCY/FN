package com.example.hemin.fnb.ui.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.util.MyApplication;
import com.example.hemin.fnb.ui.util.StatusUtils;
import com.example.hemin.fnb.ui.util.ToolUtils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class BaseActivity extends AppCompatActivity {

    //记录用户首次点击返回键的时间
    private long firstTime = 0;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.getLayoutId());
        unbinder = ButterKnife.bind(this);
        initView();
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O
                ||  !ToolUtils.isTranslucentOrFloating(this)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        if(isFullScreen()){
            StatusUtils.setFullScreen(this);
    }
    }
    public  boolean isFullScreen(){
        return false;
    }


    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int getLayoutId();

    /**
     * 初始化视图
     */
    public abstract void initView();
    protected boolean isWhiteBar(){
        return true;
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(!isFullScreen()&&isWhiteBar()){
            StatusUtils.setWhiteStatusBar(this);
        }
    }

//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
//            long secondTime = System.currentTimeMillis();
//            if (secondTime - firstTime > 2000) {
//                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                firstTime = secondTime;
//                return true;
//            } else {
////                finish();
//                MyApplication.exit();
//            }
//        }
//
//        return super.onKeyUp(keyCode, event);
//    }
}
