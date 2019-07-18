package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
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
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.base.BaseActivity;
import com.example.hemin.fnb.ui.fragment.TabFindFragment;
import com.example.hemin.fnb.ui.fragment.TabMessageFragment;
import com.example.hemin.fnb.ui.fragment.TabMyFragment;
import com.example.hemin.fnb.ui.fragment.TabShopFragment;
import com.example.hemin.fnb.ui.sever.DemoIntentService;
import com.example.hemin.fnb.ui.sever.DemoPushService;
import com.example.hemin.fnb.ui.util.AppUtils;
import com.example.hemin.fnb.ui.util.ImageBean;
import com.example.hemin.fnb.ui.util.Utils;
import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.igexin.sdk.PushManager;
import com.zzti.fengyongge.imagepicker.PhotoSelectorActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class MainActivity extends BaseActivity {

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
    @BindView(R.id.rd_analysis)
    RadioButton rdAnalysis;
    @BindView(R.id.rd_educationadmin)
    RadioButton rdEducationadmin;
    @BindView(R.id.rd_finance)
    RadioButton rdFinance;
    @BindView(R.id.rd_me)
    RadioButton rdMe;
    private TabFindFragment tabFindFragment;
    private TabShopFragment tabShopFragment;
    private TabMessageFragment tabMessageFragment;
    private TabMyFragment tabMyFragment;
    private FragmentManager fm;
    private PublishingCollections pubFragment;

    private RadioGroup mRadioButtonRg;
    private FragmentTransaction transaction1;
    private MessageAdd messageAdd;
    private static boolean mBackKeyPressed = false;//记录是否有首次按键
    private boolean isLoading = false;
    private ImageView imageView1, imageView2, imageView3, dissmissage;
    private PopupWindow popupWindow;
    private Fragment fragment;
    private FragmentManager fragmentManager;
    private Fragment currentFragment;
    private boolean network = false;


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
        fragmentManager = getSupportFragmentManager();
        addFragment("fragment1");
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


        rgOper.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rd_analysis:
                        addFragment("fragment1");
                        break;
                    case R.id.rd_educationadmin:
                        addFragment("fragment2");
                        break;
                    case R.id.rd_finance:
                        addFragment("fragment3");
                        break;
                    case R.id.rd_me:
                        addFragment("fragment4");
                        break;

                }
            }
        });
    }

    private void addFragment(String fTag) {
        fragment = fragmentManager.findFragmentByTag(fTag);
        if (fragment == null) {
            transaction1 = fragmentManager.beginTransaction();
            if (fTag.equals("fragment1")) {
                fragment = new TabFindFragment();
            } else if (fTag.equals("fragment2")) {
                fragment = new TabShopFragment();
            } else if (fTag.equals("fragment3")) {
                fragment = new TabMessageFragment();
            } else if (fTag.equals("fragment4")) {
                fragment = new TabMyFragment();
            }
            if (currentFragment != null) {
                transaction1.hide(currentFragment);
            }
            transaction1.add(R.id.fl, fragment, fTag);
            transaction1.addToBackStack(fTag);
            transaction1.commit();
            currentFragment = fragment;
        } else {
            transaction1 = fragmentManager.beginTransaction();
            transaction1.hide(currentFragment);
            transaction1.show(fragment);
            currentFragment = fragment;
            transaction1.commit();
//            }
        }
    }

    private void initPopwdowns() {
        View view = LayoutInflater.from(this).inflate(R.layout.upload_activity, null, false);
        imageView1 = view.findViewById(R.id.image_1);
        imageView2 = view.findViewById(R.id.image_2);
        imageView3 = view.findViewById(R.id.image_3);
        dissmissage = view.findViewById(R.id.upload_dissmiss);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable());
        popupWindow.showAsDropDown(fl);
        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AppUtils.getContext(), PhotoSelectorActivity.class);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                intent2.putExtra("limit", 9);//number是选择图片的数量
                startActivityForResult(intent2, 0);

            }
        });
        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, MediaRecordActivity.class);
                intent3.putExtra("type", "1");
                intent3.putExtra("StatusType", 0);
                startActivity(intent3);
            }
        });
        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PublishingCollections.class);
                startActivity(intent);
            }
        });
        dissmissage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 0:
                if (data != null) {
                    List<String> paths = (List<String>) data.getExtras().getSerializable("photos");//path是选择拍照或者图片的地址数组
                    Intent intent = new Intent(AppUtils.getContext(), MessageAdd.class);
                    intent.putStringArrayListExtra("paths", (ArrayList<String>) paths);
                    startActivity(intent);
                }
                break;

//            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void initViews() {
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


    //    @Override
//    public void onCheckedChanged(RadioGroup group, int checkedId) {
//        transaction1 = fm.beginTransaction();
//
//        if (tabFindFragment != null) {
//            transaction1.hide(tabFindFragment);
//        }
//        if (tabShopFragment != null) {
//            transaction1.hide(tabShopFragment);
//        }
//
//        if (tabMessageFragment != null) {
//            transaction1.hide(tabMessageFragment);
//        }
//        if (tabMyFragment != null) {
//            transaction1.hide(tabMyFragment);
//        }
//
//        if (checkedId == R.id.rd_analysis) {
//            if (tabFindFragment == null) {
//                tabFindFragment = new TabFindFragment();
//                transaction1.add(R.id.fl, tabFindFragment, homepage);
//
//            } else {
//                transaction1.show(tabFindFragment);
//            }
//
//
//        } else if (checkedId == R.id.rd_educationadmin) {
//            if (tabShopFragment == null) {
//                tabShopFragment = new TabShopFragment();
//                transaction1.add(R.id.fl, tabShopFragment, "shop");
//            } else {
//                transaction1.show(tabShopFragment);
//            }
//
//
//        } else if (checkedId == R.id.rd_finance) {
//            if (tabMessageFragment == null) {
//                tabMessageFragment = new TabMessageFragment();
//                transaction1.add(R.id.fl, tabMessageFragment, "message");
//            } else {
//                transaction1.show(tabMessageFragment);
//            }
//
//
//        } else if (checkedId == R.id.rd_me) {
//            if (tabMyFragment == null) {
//                tabMyFragment = new TabMyFragment();
//                transaction1.add(R.id.fl, tabMyFragment, "my");
//            } else {
//                transaction1.show(tabMyFragment);
//            }
//
//        }
//
//
//        transaction1.commit();
//    }


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
