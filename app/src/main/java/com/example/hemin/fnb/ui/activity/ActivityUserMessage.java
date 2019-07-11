package com.example.hemin.fnb.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.hemin.fnb.ui.base.BaseMvpActivity;
import com.example.hemin.fnb.ui.bean.BaseObjectBean;
import com.example.hemin.fnb.ui.bean.JsonBean;
import com.example.hemin.fnb.ui.contract.UpdateAboutContract;
import com.example.hemin.fnb.ui.presenter.UpdateAboutPresenter;
import com.example.hemin.fnb.ui.util.GetJsonDataUtil;
import com.example.hemin.fnb.ui.util.GlideLoadUtils;
import com.example.hemin.fnb.ui.util.MessageEvent;
import com.example.hemin.fnb.ui.util.Utils;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//import org.greenrobot.eventbus.Subscribe;
//import org.greenrobot.eventbus.ThreadMode;

public class ActivityUserMessage extends BaseMvpActivity<UpdateAboutPresenter> implements UpdateAboutContract.View {
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
    @BindView(R.id.title6)
    TextView title6;
    @BindView(R.id.user_logo6)
    TextView userLogo6;
    @BindView(R.id.user_righ6)
    ImageView userRigh6;
    @BindView(R.id.c6)
    ConstraintLayout c6;
    private TimePickerView pickerView;
    private static final String[] date2 = new String[]{"男", "女"};
    private List<String> mDataList2 = Arrays.asList(date2);
    private String sexStatus;
    private String url, time;
    private String birthday, nicknames, signature, userid, sexs, sex;
    private Thread thread;
    private static final int MSG_LOAD_DATA = 0x0001;
    private static final int MSG_LOAD_SUCCESS = 0x0002;
    private static final int MSG_LOAD_FAILED = 0x0003;
    private static boolean isLoaded = false;
    private List<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();


    @Override
    public int getLayoutId() {
        return R.layout.activity_user_message;
    }

    private Map<String, String> token = new HashMap<>();

    @Override
    public void initView() {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        mPresenter = new UpdateAboutPresenter();
        mPresenter.attachView(this);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        SharedPreferences sp = getSharedPreferences("userDate", Context.MODE_PRIVATE);
        nicknames = sp.getString("nickName", "");
        url = sp.getString("url", "").trim();
        userLogo2.setText(nicknames);
        birthday = sp.getString("birthday", "");
        sex = sp.getString("sex", "");
        signature = sp.getString("signature", "");
        userid = sp.getString("userId", "");
        token = Utils.getAuthorization(this);
        Glide.with(this).load(url).into(userLogo);
        userLogo3.setText(sex);
        userLogo4.setText(birthday);
        userLogo5.setText(signature);
        mHandler.sendEmptyMessage(MSG_LOAD_DATA);

    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread == null) {//如果已创建就不再重新创建子线程了
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 子线程中解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;
                case MSG_LOAD_SUCCESS:
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                    Toast.makeText(ActivityUserMessage.this, "Parse Failed", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.c1, R.id.c2, R.id.c3, R.id.c4, R.id.c5, R.id.back, R.id.c6})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.c1:
                Intent intent = new Intent(this, UserChangeLogo.class);
                intent.putExtra("url", url);
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
            case R.id.c6:
                if (isLoaded) {
                    showPickerView();
                } else {
                    Toast.makeText(ActivityUserMessage.this, "Please waiting until the data is parsed", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    private void showPickerView() {// 弹出选择器

        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String opt1tx = options1Items.size() > 0 ?
                        options1Items.get(options1).getPickerViewText() : "";

                String opt2tx = options2Items.size() > 0
                        && options2Items.get(options1).size() > 0 ?
                        options2Items.get(options1).get(options2) : "";

                String opt3tx = options2Items.size() > 0
                        && options3Items.get(options1).size() > 0
                        && options3Items.get(options1).get(options2).size() > 0 ?
                        options3Items.get(options1).get(options2).get(options3) : "";

                String tx = opt1tx + opt2tx + opt3tx;

                userLogo6.setText(tx);
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String cityName = jsonBean.get(i).getCityList().get(c).getName();
                cityList.add(cityName);//添加城市
                ArrayList<String> city_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                /*if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    city_AreaList.add("");
                } else {
                    city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }*/
                city_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                province_AreaList.add(city_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(cityList);

            /**
             * 添加地区数据
             */
            options3Items.add(province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }


    private void initOptionPicker(final List<String> typeName) {
        OptionsPickerView optionsPickerView = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
//                userLogo3.setText(typeName.get(options1));
                sexs = typeName.get(options1);
                if (sexs.equals("男")) {
                    sexStatus = "0";
                } else {
                    sexStatus = "1";
                }
                HashMap<String, String> map = new HashMap<>();
//                         map.put("birthday",birthday);
                map.put("nickname", nicknames);
                map.put("sex", sexStatus);
//                         map.put("signature",signature);
//                         map.put("url",url);
                map.put("userId", userid);
                mPresenter.updateAbout(token, Utils.RetrofitHead(map), 0);
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
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
//                Toast.makeText(getApplicationContext(), getTime(date), Toast.LENGTH_SHORT).show();
//                userLogo4.setText(getTime(date));
                time = getTime(date);
                HashMap<String, String> map = new HashMap<>();
                map.put("birthday", getTime(date));
                map.put("nickname", nicknames);
//                map.put("sex",sexStatus);
//                         map.put("signature",signature);
//                         map.put("url",url);
                map.put("userId", userid);
                mPresenter.updateAbout(token, Utils.RetrofitHead(map), 1);
            }
        })
                .setCancelText("取消")
                .setSubmitText("确定")
                .setSubmitColor(ContextCompat.getColor(this, R.color.c4D6EEF))
                .setCancelColor(ContextCompat.getColor(this, R.color.c4D6EEF))
                .setRangDate(startDate, endDate)
                .setType(new boolean[]{true, true, true, false, false, false})
                .build();
        pickerView.show();

    }

    @Subscribe(id = 2)
    public void Event(MessageEvent messageEvent) {
//        Glide.with(this).load(messageEvent.getMessage()).into(userLogo);
        GlideLoadUtils.getInstance().glideLoad(this, messageEvent.getMessage(), userLogo);
    }

    @Subscribe(id = 1)
    public void print(String message) {
        Log.i("tagccccc", message);
        userLogo2.setText(message);
    }

    @Subscribe(id = 3)
    public void prints(String message) {
        Log.i("tagccccc", message);
        userLogo5.setText(message);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
    }

    @Override
    public void onSuccess(BaseObjectBean bean, int status) {
        if (status == 0 && bean.getCode() == 0) {
            SharedPreferences.Editor sp = this.getSharedPreferences("userDate", Context.MODE_PRIVATE).edit();
            sp.putString("sex", sexs);
            sp.commit();
            userLogo3.setText(sexs);

        } else if (status == 1 && bean.getCode() == 0) {
            SharedPreferences.Editor sp = this.getSharedPreferences("userDate", Context.MODE_PRIVATE).edit();
            sp.putString("birthday", time);
            sp.commit();
            userLogo4.setText(time);

        } else {
            Toast.makeText(this, bean.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

}
