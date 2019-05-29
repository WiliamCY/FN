package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.util.Utils;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 登陆的加载界面
 */
public class WelcomeLoading extends AppCompatActivity {

    @BindView(R.id.tv)
    TextView tv;
    private int recLen = 2;//跳过倒计时提示2秒
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;
    private SharedPreferences preaIsCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        getWindow().setFlags(flag, flag);
        setContentView(R.layout.welcome_loading_activity);
        ButterKnife.bind(this);
        Utils.hideBottomUIMenu(this);
//        initView();
//        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒
        /**
         * 正常情况下不点击跳过
         */
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                //从闪屏界面跳转到首界面
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }, 1000);//延迟5S后发送handler信息

    }
//
//    @Override
//    protected int setLayoutID() {
//        return R.layout.welcome_loading_activity;
//    }
//
//    @Override
//    protected void initView(@Nullable Bundle savedInstanceState) {
//
//    }
//
//    @Override
//    public boolean isFullScreen() {
//        return true;
//    }

//    private void initView() {
//        tv = findViewById(R.id.tv);//跳过
//        tv.setOnClickListener(this);//跳过监听
//    }
//
//    TimerTask task = new TimerTask() {
//        @Override
//        public void run() {
//            runOnUiThread(new Runnable() { // UI thread
//                @Override
//                public void run() {
//                    recLen--;
//                    tv.setText("跳过 " + recLen);
//                    if (recLen < 0) {
//                        timer.cancel();
//                        tv.setVisibility(View.GONE);//倒计时到0隐藏字体
//                    }
//                }
//            });
//        }
//    };

//    /**
//     * 点击跳过
//     */
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv:
//                //从闪屏界面跳转到首界面
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
////                booleanToken();
//                if (runnable != null) {
//                    handler.removeCallbacks(runnable);
//                }
//                break;
//            default:
//                break;
//        }
//    }

//    private void booleanToken() {
//        //是否记住密码
//        preaIsCheck = this.getSharedPreferences("data", MODE_PRIVATE);
//        boolean is_rember = preaIsCheck.getBoolean("is_rember", false);
//        if (is_rember) {
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//            finish();
//
//        } else {
//            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }

}
