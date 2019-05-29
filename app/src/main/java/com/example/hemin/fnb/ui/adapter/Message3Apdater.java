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
import com.example.hemin.fnb.ui.bean.MessageBean2;
import com.example.hemin.fnb.ui.bean.MessageBean3;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class Message3Apdater extends RecyclerView.Adapter<Message3Apdater.ViewHolder> {
    private Context context;
    private List<MessageBean3.DataBean.RecordsBean> list = new ArrayList<>();
    private OnRecyclerItemClickListener monItemClickListener;
    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener){
        monItemClickListener=listener;
    }
    public Message3Apdater(Context context, List<MessageBean3.DataBean.RecordsBean> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public Message3Apdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_adapters,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Message3Apdater.ViewHolder holder, int position) {
        MessageBean3.DataBean.RecordsBean bean = list.get(position);
        Glide.with(context).load(bean.getImagesUrl()).into(holder.imageView1);
        holder.textView1.setText(bean.getFriendHead());
        Glide.with(context).load(bean.getUserUrl()).into(holder.imageView2);
//     holder.textView3.setText(bean.getGiveNum());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public  ImageView imageView1,imageView2,imageView3;
        public TextView textView1,textView2,textView3;

        public ViewHolder(@NonNull View view) {
            super(view);
      imageView1 = view.findViewById(R.id.image1);
      imageView2 = view.findViewById(R.id.image2);
      imageView3 = view.findViewById(R.id.image3);
      textView1 = view.findViewById(R.id.title1);
      textView2 = view.findViewById(R.id.titl2);
      textView3 = view.findViewById(R.id.titl3);
      view.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (monItemClickListener!=null){
                  monItemClickListener.onItemClick(getAdapterPosition());
              }
          }
      });
        }
    }
}
