package com.example.hemin.fnb.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.bean.Find5Bean;
import com.example.hemin.fnb.ui.bean.PHBBean;

import java.util.List;

public class PHBAdapter extends BaseQuickAdapter<PHBBean, BaseViewHolder> {
    public PHBAdapter(int layoutResId, @Nullable List<PHBBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PHBBean item) {
         helper.setText( R.id.title_1,item.getGiveNum()).setText(R.id.title_2,item.getCollectionNum()).setText(R.id.title_3,item.getWantNum()).setText(R.id.number,"NO."+item.getIndex());
        Glide.with(mContext).load(item.getImagesUrl()).into((ImageView) helper.getView(R.id.find_logo));

    }
}
