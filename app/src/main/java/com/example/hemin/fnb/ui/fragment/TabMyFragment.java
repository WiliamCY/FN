package com.example.hemin.fnb.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.MyAppraisa;
import com.example.hemin.fnb.ui.activity.MyAppraisaS;
import com.example.hemin.fnb.ui.activity.PasswordActivity;
import com.example.hemin.fnb.ui.activity.UserChangeLogo;
import com.example.hemin.fnb.ui.activity.UserSetting;
import com.example.hemin.fnb.ui.base.BaseFragment;
import com.example.hemin.fnb.ui.util.CircleImageView;
import com.example.hemin.fnb.ui.util.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TabMyFragment extends BaseFragment {
    @BindView(R.id.setting)
    ImageView setting;
    @BindView(R.id.user_logo)
    CircleImageView userLogo;
    @BindView(R.id.qm)
    TextView qm;
    @BindView(R.id.card_1)
    CardView card1;
    @BindView(R.id.card_2)
    CardView card2;
    @BindView(R.id.card_3)
    CardView card3;
    @BindView(R.id.card_4)
    CardView card4;
    @BindView(R.id.card_5)
    CardView card5;
    Unbinder unbinder;
    @BindView(R.id.login)
    TextView login;
    @BindView(R.id.card_6)
    CardView card6;
    Unbinder unbinder1;

    //    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_tab_my, container, false);
//
//        userLogo = view.findViewById(R.id.user_logo);
//        return view;
//    }
    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
        SharedPreferences sp = getActivity().getSharedPreferences("userDate", Context.MODE_PRIVATE);
        String nickname = sp.getString("nickName", "");
        String url = sp.getString("url", "").trim();
        login.setText(nickname);
        Glide.with(this).load(url).into(userLogo);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab_my;
    }

    @OnClick({R.id.setting, R.id.user_logo, R.id.qm, R.id.card_1, R.id.card_2, R.id.card_3, R.id.card_4, R.id.card_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting:
                Intent intent = new Intent(getActivity(), UserSetting.class);
                startActivity(intent);
                break;
            case R.id.user_logo:
                Intent intent1 = new Intent(getActivity(), UserChangeLogo.class);
                startActivity(intent1);
                break;
            case R.id.qm:
                break;
            case R.id.card_1:
                break;
            case R.id.card_2:
                Intent intent2 = new Intent(getActivity(), MyAppraisa.class);
                startActivity(intent2);
                break;
            case R.id.card_3:
//                Intent intent3 = new Intent(getActivity(), MyAppraisaS.class);
//                startActivity(intent3);
                break;
            case R.id.card_4:
                break;
            case R.id.card_5:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
