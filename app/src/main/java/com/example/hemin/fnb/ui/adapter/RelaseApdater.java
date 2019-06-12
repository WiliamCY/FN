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
import com.example.hemin.fnb.ui.bean.GuanZhuBean;
import com.example.hemin.fnb.ui.bean.MessageBean2;
import com.example.hemin.fnb.ui.bean.ReleaseBean;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class RelaseApdater extends BaseQuickAdapter<ReleaseBean.DataBean.RecordsBean, BaseViewHolder> {
    public RelaseApdater(int layoutResId, @Nullable List<ReleaseBean.DataBean.RecordsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, ReleaseBean.DataBean.RecordsBean item) {
        Glide.with(mContext).load(item.getImagesUrl()).into((ImageView) viewHolder.getView(R.id.image1));
        Glide.with(mContext).load(item.getUserUrl()).into((ImageView) viewHolder.getView(R.id.image2));
        viewHolder.setText(R.id.title1,item.getFriendHead()).setText(R.id.title2,item.getNickname()).setText(R.id.title3,item.getGiveNum());

    }
//    private Context context;
//    private List<ReleaseBean.DataBean.RecordsBean> list = new ArrayList<>();
//    private OnRecyclerItemClickListener monItemClickListener;
//
//    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
//        monItemClickListener = listener;
//    }
//  public RelaseApdater(){
//
//  }
//    public RelaseApdater(Context context, List<ReleaseBean.DataBean.RecordsBean> list) {
//        this.context = context;
//        this.list = list;
//    }
//    public void addtData(List<ReleaseBean.DataBean.RecordsBean> dataList) {
//        if (null != dataList) {
//            this.list.addAll(list);
//            notifyDataSetChanged();
//        }
//    }
//
//    public void setData(List<ReleaseBean.DataBean.RecordsBean> dataList) {
//        if (null != dataList) {
//            this.list.clear();
//            this.list.addAll(list);
//            notifyDataSetChanged();
//        }
//    }
//    @NonNull
//    @Override
//    public RelaseApdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_adapters, viewGroup, false);
//        ViewHolder holder = new ViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RelaseApdater.ViewHolder holder, int position) {
//        ReleaseBean.DataBean.RecordsBean bean = list.get(position);
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

