package com.example.hemin.fnb.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.CodeLoginActivity;
import com.example.hemin.fnb.ui.activity.MyAppraisa;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class TabMyFragment extends Fragment {
    @BindView(R.id.setting)
    ImageView setting;
    @BindView(R.id.user_logo)
    ImageView userLogo;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_my, container, false);
        unbinder = ButterKnife.bind(this, view);
        userLogo = view.findViewById(R.id.user_logo);
        userLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getActivity(),CodeLoginActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.setting, R.id.user_logo, R.id.qm, R.id.card_1, R.id.card_2, R.id.card_3, R.id.card_4, R.id.card_5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setting:
                break;
            case R.id.user_logo:
                Toast.makeText(getActivity(),"1",Toast.LENGTH_SHORT).show();
                Intent login = new Intent(getActivity(),CodeLoginActivity.class);
                startActivity(login);
                break;
            case R.id.qm:
                break;
            case R.id.card_1:
                break;
            case R.id.card_2:
                break;
            case R.id.card_3:
                    Intent intent = new Intent(getActivity(), MyAppraisa.class);
                    startActivity(intent);
                break;
            case R.id.card_4:
                break;
            case R.id.card_5:
                break;
        }
    }
}
