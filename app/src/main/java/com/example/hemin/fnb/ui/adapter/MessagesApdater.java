package com.example.hemin.fnb.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.bean.MessageBean1;
import com.example.hemin.fnb.ui.bean.MessageBean2;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MessagesApdater extends BaseQuickAdapter<MessageBean2.DataBean.RecordsBean, BaseViewHolder> {

  private List<MessageBean2.DataBean.RecordsBean> list = new ArrayList<>();
    public MessagesApdater(int layoutResId, @Nullable List<MessageBean2.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, MessageBean2.DataBean.RecordsBean item) {

        Glide.with(mContext).load(item.getImagesUrl()).into((ImageView) viewHolder.getView(R.id.image1));
        Glide.with(mContext).load(item.getUserUrl()).into((ImageView) viewHolder.getView(R.id.image2));
        viewHolder.setText(R.id.title1,item.getFriendHead()).setText(R.id.title2,item.getNickname()).setText(R.id.title3,item.getGiveNum());

    }

    public void addtData(List<MessageBean2.DataBean.RecordsBean> list) {
        if (null != list) {
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

    public void setData(List<MessageBean2.DataBean.RecordsBean> list) {
        if (null != list) {
            this.list.clear();
            this.list.addAll(list);
            notifyDataSetChanged();
        }
    }

}

