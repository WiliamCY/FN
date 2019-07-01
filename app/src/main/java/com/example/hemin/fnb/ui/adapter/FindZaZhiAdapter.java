package com.example.hemin.fnb.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.bean.MessageBean1;
import java.util.List;


public class FindZaZhiAdapter extends BaseQuickAdapter<MessageBean1.DataBean.RecordsBean, BaseViewHolder> {


    public FindZaZhiAdapter(int layoutResId, @Nullable List<MessageBean1.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MessageBean1.DataBean.RecordsBean item) {
              helper.setText(R.id.message_titl1,item.getMagazineStage()).setText(R.id.titl2,item.getMagazineName());
              Glide.with(mContext).load(item.getMagazineUrl()).into((ImageView) helper.getView(R.id.messahe_image1));

    }


}
