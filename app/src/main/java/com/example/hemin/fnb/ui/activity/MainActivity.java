package com.example.hemin.fnb.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;
import com.example.hemin.fnb.ui.base.BaseFragment;
import com.example.hemin.fnb.ui.fragment.TabFindFragment;
import com.example.hemin.fnb.ui.fragment.TabMessageFragment;
import com.example.hemin.fnb.ui.fragment.TabMyFragment;
import com.example.hemin.fnb.ui.fragment.TabShopFragment;


import com.example.hemin.fnb.ui.util.AlertDialog;
import com.example.hemin.fnb.ui.util.Utils;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener{

    private static String homepage = "findFragment";
    FrameLayout activityMaterialDesign;
    private List<BaseFragment> fragmentList = new ArrayList<>();
    private TabFindFragment tabFindFragment;
    private TabShopFragment tabShopFragment;
    private TabMessageFragment tabMessageFragment;
    private TabMyFragment tabMyFragment;
    protected ImageView imgProtruding;
    private FragmentManager fm;
    FragmentTransaction transaction;
    private RadioGroup mRadioButtonRg;
    private FragmentTransaction transaction1;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);
        initViews();
        FragmentManager fragmentManager = getSupportFragmentManager();
        tabFindFragment = new TabFindFragment();
        fragmentManager.beginTransaction().replace(R.id.fl, tabFindFragment, homepage).commit();
        Utils.initLogins(this);

    }



    private void initViews() {
        mRadioButtonRg = (RadioGroup) findViewById(R.id.rg_oper);
        mRadioButtonRg.setOnCheckedChangeListener(this);
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();
        tabFindFragment = (TabFindFragment) fm.findFragmentByTag(homepage);
        tabShopFragment = (TabShopFragment) fm.findFragmentByTag("shop");
        tabMessageFragment = (TabMessageFragment) fm.findFragmentByTag("message");
        tabMyFragment = (TabMyFragment) fm.findFragmentByTag("my");
        imgProtruding = (ImageView) findViewById(R.id.img_protruding);
        imgProtruding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PublishingCollections.class);
                startActivity(intent);

            }
        });
        XXPermissions.with(this)
                .constantRequest() //可设置被拒绝后继续申请，直到用户授权或者永久拒绝
                //.permission(Permission.SYSTEM_ALERT_WINDOW, Permission.REQUEST_INSTALL_PACKAGES) //支持请求6.0悬浮窗权限8.0请求安装权限
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
        if (checkedId == R.id.rd_analysis) {
            if (tabFindFragment == null) {
                tabFindFragment = new TabFindFragment();
                transaction1.add(R.id.fl, tabFindFragment, homepage);
            } else {
                transaction1.show(tabFindFragment);
            }


        } else if (checkedId == R.id.rd_educationadmin) {
            if (tabShopFragment == null) {
                tabShopFragment = new TabShopFragment();
                transaction1.add(R.id.fl, tabShopFragment, "shop");
            } else {
                transaction1.show(tabShopFragment);
            }




        } else if (checkedId == R.id.rd_finance) {
            if (tabMessageFragment == null) {
                tabMessageFragment = new TabMessageFragment();
                transaction1.add(R.id.fl, tabMessageFragment, "message");
            } else {
                transaction1.show(tabMessageFragment);
            }


        } else if (checkedId == R.id.rd_me) {
            if (tabMyFragment == null) {
                tabMyFragment = new TabMyFragment();
                transaction1.add(R.id.fl, tabMyFragment, "my");
            } else {
                transaction1.show(tabMyFragment);
            }

        }
        transaction1.commit();
    }


}
