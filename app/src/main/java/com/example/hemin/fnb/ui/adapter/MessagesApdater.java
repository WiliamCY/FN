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
//    public MessagesApdater(Context context, List<MessageBean2.DataBean.RecordsBean> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//    @NonNull
//    @Override
//    public MessagesApdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_adapters, viewGroup, false);
//        ViewHolder holder = new ViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MessagesApdater.ViewHolder holder, int position) {
//        MessageBean2.DataBean.RecordsBean bean = list.get(position);
//        Glide.with(context).load(bean.getImagesUrl()).into(holder.imageView1);
//        holder.textView1.setText(bean.getFriendHead());
//        Glide.with(context).load(bean.getUserUrl()).into(holder.imageView2);
//        holder.textView2.setText(bean.getNickname());
//        holder.textView3.setText(bean.getGiveNum());
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public ImageView imageView1, imageView2, imageView3;
//        public TextView textView1, textView2, textView3;
//
//        public ViewHolder(@NonNull View view) {
//            super(view);
//            imageView1 = view.findViewById(R.id.image1);
//            imageView2 = view.findViewById(R.id.image2);
//            imageView3 = view.findViewById(R.id.image3);
//            textView1 = view.findViewById(R.id.title1);
//            textView2 = view.findViewById(R.id.title2);
//            textView3 = view.findViewById(R.id.title3);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (monItemClickListener != null) {
//                        monItemClickListener.onItemClick(getAdapterPosition());
//                    }
//                }
//            });
//        }
//    }
}

