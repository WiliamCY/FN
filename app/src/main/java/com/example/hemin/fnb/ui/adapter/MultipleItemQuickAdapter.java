package com.example.hemin.fnb.ui.adapter;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.bean.MultipleItem;

import java.util.List;

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleItemQuickAdapter(List<MultipleItem> data) {
        super(data);
        addItemType(MultipleItem.One, R.layout.find_card_view);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, MultipleItem item) {
        switch (viewHolder.getItemViewType()){
            case MultipleItem.One:
//                helper.s
//                Glide.with(mContext).load(item.getImageUrl()).into(holder.getView());
                Glide.with(mContext).load(item.getUrl()).into((ImageView) viewHolder.getView(R.id.find_logo2));
        }

    }
}
