package com.example.hemin.fnb.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.bean.MessageBean3;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class QuanziApdater extends RecyclerView.Adapter<QuanziApdater.ViewHolder> {
    private Context context;
    private List<MessageBean3.DataBean.RecordsBean> list = new ArrayList<>();
    private OnRecyclerItemClickListener monItemClickListener;

    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
        monItemClickListener = listener;
    }

    public QuanziApdater(Context context, List<MessageBean3.DataBean.RecordsBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public QuanziApdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.quanzi_adapters, viewGroup, false);
        QuanziApdater.ViewHolder holder = new QuanziApdater.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuanziApdater.ViewHolder holder, int position) {
        MessageBean3.DataBean.RecordsBean bean = list.get(position);
        Glide.with(context).load(bean.getImagesUrl()).into(holder.imageView1);
        holder.textView1.setText(bean.getFriendHead());
        Glide.with(context).load(bean.getUserUrl()).into(holder.imageView2);
        holder.textView2.setText(bean.getNickname());
        holder.textView3.setText(bean.getGiveNum());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView1, imageView2, imageView3;
        public TextView textView1, textView2, textView3;

        public ViewHolder(@NonNull View view) {
            super(view);
            imageView1 = view.findViewById(R.id.image1);
            imageView2 = view.findViewById(R.id.image2);
            imageView3 = view.findViewById(R.id.image3);
            textView1 = view.findViewById(R.id.title1);
            textView2 = view.findViewById(R.id.title2);
            textView3 = view.findViewById(R.id.title3);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (monItemClickListener != null) {
                        monItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
