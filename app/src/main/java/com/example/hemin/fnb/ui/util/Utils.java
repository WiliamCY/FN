package com.example.hemin.fnb.ui.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.PasswordActivity;
import com.google.gson.Gson;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import okhttp3.RequestBody;

public class Utils {

    public static boolean isPhoneNumber(String input) {
        String regex = "(1[0-9][0-9]|15[0-9]|18[0-9])\\d{8}";
        Pattern p = Pattern.compile(regex);
        return p.matches(regex, input);
    }

    public static class TimeCount extends CountDownTimer {
        private TextView btn_count;
        public TimeCount(long millisInFuture, long countDownInterval, TextView btn_count) {
            super(millisInFuture, countDownInterval);
           this.btn_count = btn_count;
        }

        @Override
        public void onTick(long millisUntilFinished) {
            btn_count.setEnabled(false);
            btn_count.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            btn_count.setEnabled(true);
            btn_count.setText("获取验证码");

        }

    }
    public  static RequestBody RetrofitHead(HashMap hashMap){
        Gson gson = new Gson();
        String strEntity = gson.toJson(hashMap);
        RequestBody body = RequestBody.create(okhttp3.MediaType.parse("application/json;charset=UTF-8"),strEntity);
        return body;

    }
    public static void hideBottomUIMenu(Activity activity) {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT < 19) { // lower api
            View v = activity.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            View decorView = activity.getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY ;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }
    public  static  boolean  initLogin(final Context context) {
        SharedPreferences sp = context.getSharedPreferences("userDate", Context.MODE_PRIVATE);
        if (sp.getString("Authorization", "").equals("")) {
            AlertDialog dialog = new AlertDialog(context).builder();
            dialog.setGone().setTitle("提示")
                    .setMsg("请先登录")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, PasswordActivity.class);
                            context.startActivity(intent);
                        }
                    }).show();

        }
        return false;
    }
    public  static boolean booleanisLogin(final  Context context){
        SharedPreferences sp = context.getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String c = sp.getString("Authorization","");
        if (c.equals("")) {

         return true;

        }
        return false;
    }

    public   static  void  initLogins(final Context context) {
        SharedPreferences sp = context.getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String c = sp.getString("Authorization", "");
        if (sp.getString("Authorization", "").equals("")) {
            AlertDialog dialog = new AlertDialog(context).builder();
            dialog.setGone().setTitle("提示")
                    .setNegativeButton("取消", null)
                    .setMsg("是否退出")
                    .setPositiveButton("确定", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();

        }
    }
    /**
     * 删除单个文件
     * @param   filePath    被删除文件的文件名
     * @return 文件删除成功返回true，否则返回false
     */
    public  static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            return file.delete();
        }
        return false;
    }

    /**
     * 删除文件夹以及目录下的文件
     * @param   filePath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String filePath) {
        boolean flag = false;
        //如果filePath不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator)) {
            filePath = filePath + File.separator;
        }
        File dirFile = new File(filePath);
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        flag = true;
        File[] files = dirFile.listFiles();
        //遍历删除文件夹下的所有文件(包括子目录)
        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                //删除子文件
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } else {
                //删除子目录
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        if (!flag) return false;
        //删除当前空目录
        return dirFile.delete();
    }

    /**
     *  根据路径删除指定的目录或文件，无论存在与否
     *@param filePath  要删除的目录或文件
     *@return 删除成功返回 true，否则返回 false。
     */
    public static boolean DeleteFolder(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            return false;
        } else {
            if (file.isFile()) {
                // 为文件时调用删除文件方法
                return deleteFile(filePath);
            } else {
                // 为目录时调用删除目录方法
                return deleteDirectory(filePath);
            }
        }
    }
    public  static  void showMyToast(final Toast toast, final int cnt) {
        final Timer timer =new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        },0,2000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, cnt );
    }

}
