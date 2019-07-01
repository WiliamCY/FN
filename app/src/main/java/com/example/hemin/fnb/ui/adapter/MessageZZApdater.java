package com.example.hemin.fnb.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.bean.FindBean;
import com.example.hemin.fnb.ui.bean.MessageBean1;

import java.util.List;

public class MessageZZApdater extends BaseQuickAdapter<FindBean, BaseViewHolder> {
    public MessageZZApdater(int layoutResId, @Nullable List<FindBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, FindBean item) {
        Glide.with(mContext).load(item.getImageUrl()).into((ImageView) viewHolder.getView(R.id.messahe_image1));
        viewHolder.setText(R.id.message_titl1,item.getGiveNum()).setText(R.id.titl2,item.getWantNum());

    }

    }

