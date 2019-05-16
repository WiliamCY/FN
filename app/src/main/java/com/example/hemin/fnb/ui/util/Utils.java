package com.example.hemin.fnb.ui.util;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

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


}
