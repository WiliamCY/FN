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
import com.example.hemin.fnb.ui.bean.MessageBean1;

import java.util.ArrayList;
import java.util.List;

public class MessageApdater extends RecyclerView.Adapter<MessageApdater.ViewHolder> {
  private  Context context;
  private   List<MessageBean1.DataBean.RecordsBean> list = new ArrayList<>();
    public MessageApdater(Context context, List<MessageBean1.DataBean.RecordsBean> list){
          this.context = context;
          this.list = list;
    }
    @NonNull
    @Override
    public MessageApdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_adapter,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageApdater.ViewHolder holder, int position) {
             MessageBean1.DataBean.RecordsBean bean = list.get(position);
        Glide.with(context).load(bean.getMagazineUrl()).into(holder.imageView);
        holder.titl1.setText(bean.getMagazineStage());
        holder.titl2.setText(bean.getMagazineName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView titl1,titl2;
        public ViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.messahe_image1);
            titl1 = view.findViewById(R.id.message_titl1);
            titl2 = view.findViewById(R.id.titl2);
        }
    }
}
