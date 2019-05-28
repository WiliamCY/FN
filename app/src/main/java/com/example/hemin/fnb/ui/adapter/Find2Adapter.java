package com.example.hemin.fnb.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.bean.Find2Bean;
import com.example.hemin.fnb.ui.bean.FindBean;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class Find2Adapter extends RecyclerView.Adapter<Find2Adapter.ViewHolder> {
    private List<Find2Bean.DataBean.RecordsBean>  beans = new ArrayList<>();
    private Context context;
    private OnRecyclerItemClickListener monItemClickListener;

    public Find2Adapter(Context context,List<Find2Bean.DataBean.RecordsBean>  beans) {
        this.beans = beans;
        this.context = context;
    }

    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
        monItemClickListener = listener;
    }

    @NonNull
    @Override
    public Find2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_card2_view, parent, false);
        Find2Adapter.ViewHolder holder = new Find2Adapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Find2Adapter.ViewHolder holder, int position) {
        Find2Bean.DataBean.RecordsBean bean = beans.get(position);
     Glide.with(context).load(bean.getTopicUrl().trim()).into(holder.imageView2);


    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView  imageView2;

        public ViewHolder(@NonNull View view) {
            super(view);

            imageView2 = view.findViewById(R.id.find_logo2);

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
