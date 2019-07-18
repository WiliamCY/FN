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
import com.example.hemin.fnb.ui.bean.MessageBean2;
import com.example.hemin.fnb.ui.bean.MessageBean3;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class Message3Apdater extends BaseQuickAdapter<MessageBean3.DataBean.RecordsBean, BaseViewHolder> {


    public Message3Apdater(int layoutResId, @Nullable List<MessageBean3.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, MessageBean3.DataBean.RecordsBean item) {
        if(item.getImagesUrl().contains("/MP4/")){
            Glide.with(mContext).load(item.getImagesUrl()+"?x-oss-process=video/snapshot,t_5000,f_jpg,w_0,h_0,m_fast,ar_auto").into((ImageView) viewHolder.getView(R.id.image1));
            viewHolder.getView(R.id.play).setVisibility(View.VISIBLE);
        }else if(item.getImagesUrl().contains("/images/")) {

            Glide.with(mContext).load(item.getImagesUrl()).into((ImageView) viewHolder.getView(R.id.image1));
            viewHolder.getView(R.id.play).setVisibility(View.GONE);
        }
        Glide.with(mContext).load(item.getUserUrl()).into((ImageView) viewHolder.getView(R.id.image2));
        viewHolder.setText(R.id.title1,item.getFriendHead()).setText(R.id.title2,item.getNickname()).setText(R.id.title3,item.getGiveNum());

    }

}
