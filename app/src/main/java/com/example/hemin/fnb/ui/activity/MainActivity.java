package com.example.hemin.fnb.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.fragment.TabFindFragment;
import com.example.hemin.fnb.ui.fragment.TabMessageFragment;
import com.example.hemin.fnb.ui.fragment.TabMyFragment;
import com.example.hemin.fnb.ui.fragment.TabShopFragment;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private static String homepage = "findFragment";
    FrameLayout activityMaterialDesign;
    private List<Fragment> fragmentList = new ArrayList<>();
    private TabFindFragment tabFindFragment;
    private TabShopFragment tabShopFragment;
    private TabMessageFragment tabMessageFragment;
    private TabMyFragment tabMyFragment;
    protected ImageView imgProtruding;
    private FragmentManager fm;
    FragmentTransaction transaction;
    private RadioGroup mRadioButtonRg;
    private FragmentTransaction transaction1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        StatusBarUtil.setTranslucent(this);
        initView();
        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            tabFindFragment = new TabFindFragment();
            fragmentManager.beginTransaction().replace(R.id.fl, tabFindFragment, homepage).commit();
        }
    }

    private void initView() {
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
                Intent intent = new Intent(getApplicationContext(),PublishingCollections.class);
                startActivity(intent);

            }
        });
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
