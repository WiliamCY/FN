package com.example.hemin.fnb.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.bean.GuanZhuBean;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListeners;

import java.util.ArrayList;
import java.util.List;

public class TranslateAdapter extends BaseQuickAdapter<GuanZhuBean.DataBean.RecordsBean, BaseViewHolder> {


    public TranslateAdapter(int layoutResId, @Nullable List<GuanZhuBean.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, final GuanZhuBean.DataBean.RecordsBean item) {
        Glide.with(mContext).load(item.getUserUrl()).into((ImageView) viewHolder.getView(R.id.q_1));
        viewHolder.setText(R.id.t_1,item.getNickname());
        viewHolder.setText(R.id.t_2,item.getFocusSum()).addOnClickListener(R.id.t_3);



    }

}
