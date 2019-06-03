package com.example.hemin.fnb.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.bean.GuanZhuBean;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListeners;

import java.util.ArrayList;
import java.util.List;

public class TranslateAdapter extends RecyclerView.Adapter<TranslateAdapter.ViewHolder> {
    private List<GuanZhuBean.DataBean.RecordsBean> beans = new ArrayList<>();
    private Context context;
    private OnRecyclerItemClickListeners monItemClickListener;

    public void setRecyclerItemClickListeners(OnRecyclerItemClickListeners listener) {
        monItemClickListener = listener;
    }
    public TranslateAdapter(Context context,List<GuanZhuBean.DataBean.RecordsBean> beans){
        this.beans = beans;
        this.context = context;
    }
    public TranslateAdapter(){

    }
    @NonNull
    @Override
    public TranslateAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gz_adapter,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TranslateAdapter.ViewHolder holder, int position) {
            GuanZhuBean.DataBean.RecordsBean list = beans.get(position);
            Glide.with(context).load(list.getUserUrl()).into(holder.imageView);
            holder.textView1.setText(list.getNickname());
            holder.textView2.setText("发布：" + list.getFocusSum());

    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView1,textView2,textView3;
        public ConstraintLayout constraintLayout;
        public ViewHolder(@NonNull View view) {
            super(view);
            constraintLayout  = view.findViewById(R.id.c1);
            imageView =  view.findViewById(R.id.q_1);
            textView1 = view.findViewById(R.id.t_1);
            textView2 = view.findViewById(R.id.t_2);
            textView3 = view.findViewById(R.id.t_3);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (monItemClickListener != null) {
                        monItemClickListener.onItemClick(getAdapterPosition(),textView3);
                    }
                }
            });
        }
    }
}
