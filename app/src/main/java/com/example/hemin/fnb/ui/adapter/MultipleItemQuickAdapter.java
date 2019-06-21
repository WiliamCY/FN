package com.example.hemin.fnb.ui.adapter;

import android.annotation.SuppressLint;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.bean.MultipleItem;
import com.nostra13.universalimageloader.utils.L;

import java.util.List;

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    @SuppressLint("LongLogTag")
    public MultipleItemQuickAdapter(List data) {
           super(data);
            addItemType(MultipleItem.One, R.layout.find_card_view);
            addItemType(MultipleItem.Two,R.layout.find_card3_view);
            addItemType(MultipleItem.Three,R.layout.find_card4_view);
            addItemType(MultipleItem.Four,R.layout.find_card5_view);

    }


    @Override
    protected void convert(BaseViewHolder viewHolder, MultipleItem item) {
        switch (viewHolder.getItemViewType()){
            case MultipleItem.One:
//                helper.s
//                Glide.with(mContext).load(item.getImageUrl()).into(holder.getView());
//                Glide.with(mContext).load(item.getUrl()).into((ImageView) viewHolder.getView(R.id.find_logo2));
                break;
                case MultipleItem.Two:
//                    Glide.with(mContext).load(item.getUrl()).into((ImageView) viewHolder.getView(R.id.find_logo2));
                    break;
                    case MultipleItem.Four:
//                        viewHolder.setText(R.id.title_1,item.getGiveNum());
//                        viewHolder.setText(R.id.title_2,item.getCollectionNum());
//                        viewHolder.setText(R.id.title_3,item.getWantNum());
//                        Glide.with(mContext).load(item.getUrl()).into((ImageView) viewHolder.getView(R.id.find_logo));

        }

    }


}
