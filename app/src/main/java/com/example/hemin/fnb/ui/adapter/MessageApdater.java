package com.example.hemin.fnb.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.example.hemin.fnb.ui.activity.MessageAdd;
import com.example.hemin.fnb.ui.bean.GuanZhuBean;
import com.example.hemin.fnb.ui.bean.MessageBean1;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MessageApdater extends BaseQuickAdapter<MessageBean1.DataBean.RecordsBean, BaseViewHolder> {
    public MessageApdater(int layoutResId, @Nullable List<MessageBean1.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, MessageBean1.DataBean.RecordsBean item) {
        Glide.with(mContext).load(item.getMagazineUrl()).into((ImageView) viewHolder.getView(R.id.messahe_image1));
        viewHolder.setText(R.id.message_titl1,item.getMagazineStage()).setText(R.id.titl2,item.getMagazineName());

    }

    }

