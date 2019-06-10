package com.example.hemin.fnb.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;
import com.example.hemin.fnb.ui.util.MessageEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityUserMessage extends BaseActivity {
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.user_toolbar)
    LinearLayout userToolbar;
    @BindView(R.id.title1)
    TextView title1;
    @BindView(R.id.user_logo)
    ImageView userLogo;
    @BindView(R.id.user_right1)
    ImageView userRight1;
    @BindView(R.id.view1)
    View view1;
    @BindView(R.id.title2)
    TextView title2;
    @BindView(R.id.user_logo2)
    TextView userLogo2;
    @BindView(R.id.user_right2)
    ImageView userRight2;
    @BindView(R.id.view2)
    View view2;
    @BindView(R.id.title3)
    TextView title3;
    @BindView(R.id.user_logo3)
    TextView userLogo3;
    @BindView(R.id.user_right3)
    ImageView userRight3;
    @BindView(R.id.view3)
    View view3;
    @BindView(R.id.title4)
    TextView title4;
    @BindView(R.id.user_logo4)
    TextView userLogo4;
    @BindView(R.id.user_right4)
    ImageView userRight4;
    @BindView(R.id.view4)
    View view4;
    @BindView(R.id.title5)
    TextView title5;
    @BindView(R.id.user_logo5)
    TextView userLogo5;
    @BindView(R.id.user_right5)
    ImageView userRight5;
    @BindView(R.id.view5)
    View view5;
    @BindView(R.id.c1)
    ConstraintLayout c1;
    @BindView(R.id.c2)
    ConstraintLayout c2;
    @BindView(R.id.c3)
    ConstraintLayout c3;
    @BindView(R.id.c4)
    ConstraintLayout c4;
    @BindView(R.id.c5)
    ConstraintLayout c5;
    private TimePickerView pickerView;
    private static final String[] date2 = new String[]{"男", "女"};
    private List<String> mDataList2 = Arrays.asList(date2);

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_message;
    }

    @Override
    public void initView() {
        SharedPreferences sp =getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String nickname = sp.getString("nickName", "");
        String url = sp.getString("url", "").trim();
        userLogo2.setText(nickname);
        Glide.with(this).load(url).into(userLogo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.c1:
                Intent intent = new Intent(this, UserChangeLogo.class);
                startActivity(intent);
                break;
            case R.id.c2:
                Intent intent2 = new Intent(this, FixNickName.class);
                intent2.putExtra("status", 0);
                startActivity(intent2);
                break;
            case R.id.c3:
                initOptionPicker(mDataList2);
                break;
            case R.id.c4:
                initBirthday();
                break;
            case R.id.c5:
                Intent intent5 = new Intent(this, FixNickName.class);
                intent5.putExtra("status", 1);
                startActivity(intent5);
                break;
            case R.id.back:
                finish();
        }
    }

    private void initOptionPicker(final List<String> typeName) {
        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                userLogo3.setText(typeName.get(options1));
            }
        }).setSubmitColor(ContextCompat.getColor(this, R.color.c4D6EEF))
                .setCancelColor(ContextCompat.getColor(this, R.color.c4D6EEF))
                .setDividerColor(Color.BLACK)
                .setCancelText("取消")
                .setSubmitText("确定")
                .setTextColorCenter(Color.BLACK)
                .setContentTextSize(20)
                .build();
        optionsPickerView.setPicker(typeName);

        optionsPickerView.show();
    }

    private String getTime(Date date) {//可根据需要自行截取数据显示
        Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    private void initBirthday() {
        Calendar calendar = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

//年
        int year = calendar.get(Calendar.YEAR);
//月
        int month = calendar.get(Calendar.MONTH);
//日
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        endDate.set(year, month, day);
        startDate.set(1919, 0, 1);
        pickerView = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                Toast.makeText(getApplicationContext(), getTime(date), Toast.LENGTH_SHORT).show();
                userLogo4.setText(getTime(date));
            }
        })
                .setCancelText("取消")
                .setSubmitText("确定")
                .setSubmitColor(ContextCompat.getColor(this, R.color.c4D6EEF))
                .setCancelColor(ContextCompat.getColor(this, R.color.c4D6EEF))
                .setRangDate(startDate, endDate)
                .build();
        pickerView.show();

    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent) {
        Glide.with(this).load(messageEvent.getMessage()).into(userLogo);
    }
}
