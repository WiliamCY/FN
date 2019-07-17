package com.example.hemin.fnb.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.bean.GuanZhuBean;

import java.util.List;

public class FansAdapter extends BaseQuickAdapter<GuanZhuBean.DataBean.RecordsBean, BaseViewHolder> {
    public FansAdapter(int layoutResId, @Nullable List<GuanZhuBean.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, GuanZhuBean.DataBean.RecordsBean item) {
        Glide.with(mContext).load(item.getUserUrl()).into((ImageView) helper.getView(R.id.q_1));
        helper.setText(R.id.t_1,item.getNickname()).setText(R.id.t_2,item.getSignature());
        if (item.getRoleId() == 2){
            helper.getView(R.id.q_2).setVisibility(View.GONE);
        }else if(item.getRoleId() == 3){
            helper.getView(R.id.q_2).setVisibility(View.VISIBLE);
        }

    }
}
