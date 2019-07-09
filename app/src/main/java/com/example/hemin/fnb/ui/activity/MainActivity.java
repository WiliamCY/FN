package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;
import com.example.hemin.fnb.ui.base.BaseFragment;
import com.example.hemin.fnb.ui.fragment.TabFindFragment;
import com.example.hemin.fnb.ui.fragment.TabMessageFragment;
import com.example.hemin.fnb.ui.fragment.TabMyFragment;
import com.example.hemin.fnb.ui.fragment.TabShopFragment;
import com.example.hemin.fnb.ui.sever.DemoIntentService;
import com.example.hemin.fnb.ui.sever.DemoPushService;
import com.example.hemin.fnb.ui.util.AppUtils;
import com.example.hemin.fnb.ui.util.Utils;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.igexin.sdk.PushManager;
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private static String homepage = "findFragment";
    @BindView(R.id.rg_oper)
    RadioGroup rgOper;
    @BindView(R.id.fl)
    FrameLayout fl;
    @BindView(R.id.r1)
    RelativeLayout r1;
    @BindView(R.id.vb_mian_show)
    ViewStub vbMianShow;
    @BindView(R.id.img_protruding)
    RadioButton imgProtruding;
    private TabFindFragment tabFindFragment;
    private TabShopFragment tabShopFragment;
    private TabMessageFragment tabMessageFragment;
    private TabMyFragment tabMyFragment;
    private FragmentManager fm;
    private PublishingCollections pubFragment;
    FragmentTransaction transaction;
    private RadioGroup mRadioButtonRg;
    private FragmentTransaction transaction1;
    private MessageAdd messageAdd;
    private static boolean mBackKeyPressed = false;//记录是否有首次按键
    private boolean isLoading = false;
    private ImageView imageView1, imageView2, imageView3, dissmissage;
    private PopupWindow popupWindow;


    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initViews();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        PushManager.getInstance().initialize(getApplicationContext(), DemoPushService.class);
        PushManager.getInstance().registerPushIntentService(getApplicationContext(), DemoIntentService.class);

        FragmentManager fragmentManager = getSupportFragmentManager();
        tabFindFragment = new TabFindFragment();
        fragmentManager.beginTransaction().replace(R.id.fl, tabFindFragment, homepage).commit();
//        imgProtruding.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, UploadActivity.class);
//                startActivity(intent);
//            }
//        });
//
        imgProtruding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   initPopwdowns();
            }
        });
        if (Utils.booleanisLogin(this)) {
            Intent intent = new Intent(this, WelcomeLoading.class);
            startActivity(intent);
            finish();
        }

    }
    private void initPopwdowns() {
        View view = LayoutInflater.from(this).inflate(R.layout.upload_activity, null, false);
        imageView1 = view.findViewById(R.id.image_1);
        imageView2 = view.findViewById(R.id.image_2);
        imageView3 = view.findViewById(R.id.image_3);
        dissmissage = view.findViewById(R.id.upload_dissmiss);
//        imageView1.setOnClickListener(this);
//        imageView2.setOnClickListener(this);
//        imageView3.setOnClickListener(this);
//        dissmissage.setOnClickListener(this);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAsDropDown(fl);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AppUtils.getContext(), PhotoSelectorActivity.class);
//                intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                intent2.putExtra("limit", 9 - adapter.getItemCount());//number是选择图片的数量
//                startActivityForResult(intent2, 0);
//                popupWindow.dismiss();
            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivityForResult(new Intent(getActivity(), MediaRecordActivity.class), 100);
                popupWindow.dismiss();
            }
        });
        dissmissage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

        getSupportFragmentManager().popBackStack("1",0);
                popupWindow.dismiss();

            }
        });

    }


    private void initViews() {
        mRadioButtonRg = (RadioGroup) findViewById(R.id.rg_oper);
        mRadioButtonRg.setOnCheckedChangeListener(this);
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        tabFindFragment = (TabFindFragment) fm.findFragmentByTag(homepage);
        tabShopFragment = (TabShopFragment) fm.findFragmentByTag("shop");
        messageAdd = (MessageAdd) fm.findFragmentByTag("messageAdd");
        tabMessageFragment = (TabMessageFragment) fm.findFragmentByTag("message");
        tabMyFragment = (TabMyFragment) fm.findFragmentByTag("my");
//        pubFragment = (PublishingCollections) fm.findFragmentByTag("pub");
        XXPermissions.with(this)
                .constantRequest()
                .permission(Permission.Group.STORAGE, Permission.Group.CALENDAR) //不指定权限则自动获取清单中的危险权限
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean isAll) {

                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {

                    }
                });
//    }
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        transaction1 = fm.beginTransaction();

        if (tabFindFragment != null) {
            transaction1.hide(tabFindFragment);
        }
        if (tabShopFragment != null) {
            transaction1.hide(tabShopFragment);
        }

        if (tabMessageFragment != null) {
            transaction1.hide(tabMessageFragment);
        }
        if (tabMyFragment != null) {
            transaction1.hide(tabMyFragment);
        }
//        if (pubFragment != null) {
//            transaction1.hide(pubFragment);
//        }
//        if(messageAdd != null){
//            transaction1.hide(messageAdd);
//        }
        if (checkedId == R.id.rd_analysis) {
            if (tabFindFragment == null) {
                tabFindFragment = new TabFindFragment();
                transaction1.add(R.id.fl, tabFindFragment, homepage);
                transaction1.addToBackStack("1");
            } else {
                transaction1.show(tabFindFragment);
                transaction1.addToBackStack("1");
            }


        } else if (checkedId == R.id.rd_educationadmin) {
            if (tabShopFragment == null) {
                tabShopFragment = new TabShopFragment();
                transaction1.add(R.id.fl, tabShopFragment, "shop");
                transaction1.addToBackStack(null);
            } else {
                transaction1.show(tabShopFragment);
            }


        } else if (checkedId == R.id.rd_finance) {
            if (tabMessageFragment == null) {
                tabMessageFragment = new TabMessageFragment();
                transaction1.add(R.id.fl, tabMessageFragment, "message");
                transaction1.addToBackStack(null);
            } else {
                transaction1.show(tabMessageFragment);
            }


        } else if (checkedId == R.id.rd_me) {
            if (tabMyFragment == null) {
                tabMyFragment = new TabMyFragment();
                transaction1.add(R.id.fl, tabMyFragment, "my");
                transaction1.addToBackStack(null);
            } else {
                transaction1.show(tabMyFragment);
            }

        }
//        else if (checkedId == R.id.img_protruding) {
//            if (messageAdd == null) {
//                messageAdd = new MessageAdd();
//                transaction1.add(R.id.fl, messageAdd, "pub");
//                transaction1.addToBackStack(null);
//            } else {
//                transaction1.show(messageAdd);
//            }
//
//        }

        transaction1.commit();
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            //do something.
            this.finish();
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
