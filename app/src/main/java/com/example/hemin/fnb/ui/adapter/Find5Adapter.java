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
import com.example.hemin.fnb.ui.bean.Find5Bean;
import com.example.hemin.fnb.ui.bean.FindBean;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;

import java.util.List;

public class Find5Adapter extends RecyclerView.Adapter<Find5Adapter.ViewHolder> {
    private List<Find5Bean.DataBean.RecordsBean> beans;
    private Context context;
    private OnRecyclerItemClickListener monItemClickListener;

    public Find5Adapter(Context context, List<Find5Bean.DataBean.RecordsBean> beans) {
        this.beans = beans;
        this.context = context;
    }

    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
        monItemClickListener = listener;
    }

    @NonNull
    @Override
    public Find5Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_card5_view, parent, false);
        Find5Adapter.ViewHolder holder = new Find5Adapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Find5Adapter.ViewHolder holder, int position) {

        Find5Bean.DataBean.RecordsBean bean = beans.get(position);


//        for(int i =0 ;i<bean.getImageUrl().size();i++){
//             String url = beans.get(i).getPathUrl();
//            Log.d("imageUrlAdapter",url);
//            Glide.with(context).load(url).into(holder.imageView);
//        }
//
//                    for(int i = 0;i<bean.getImageUrl().size();i++){
//                         urls = bean.getImageUrl().get(i);
//                    Log.d("imageUrlAdapter",urls);
//                    }


//        int status = bean.getStatus();
//        Log.d("imageUrlAdapter",bean.getImageUrl());
//        switch (status) {
//            case 0:
//                holder.imageView2.setVisibility(View.VISIBLE);
//                holder.imageView.setVisibility(View.GONE);
//                holder.linearLayout.setVisibility(View.GONE);
//                holder.findButton.setVisibility(View.GONE);
//                Glide.with(context).load(bean.getImageUrl().trim()).into(holder.imageView2);
//                break;
//            case 1:
//                holder.imageView2.setVisibility(View.GONE);
//                holder.imageView.setVisibility(View.VISIBLE);
//                holder.linearLayout.setVisibility(View.VISIBLE);
//                holder.findButton.setVisibility(View.GONE);
//                Glide.with(context).load(bean.getImageUrl().trim()).into(holder.imageView);
//                break;
//            case 2:
//                holder.imageView2.setVisibility(View.GONE);
//                holder.imageView.setVisibility(View.VISIBLE);
//                holder.linearLayout.setVisibility(View.VISIBLE);
//                holder.findButton.setVisibility(View.VISIBLE);
//                Glide.with(context).load(bean.getImageUrl().trim()).into(holder.imageView);
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public int getItemCount() {
        return beans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView, imageView2;
        public TextView image1, image2, image3, text1, text2, text3, findButton;
        public LinearLayout linearLayout;

        public ViewHolder(@NonNull View view) {
            super(view);
            linearLayout = view.findViewById(R.id.layouts);
            imageView = view.findViewById(R.id.find_logo);
            imageView2 = view.findViewById(R.id.find_logo2);
            image1 = view.findViewById(R.id.image_1);
            image2 = view.findViewById(R.id.image_2);
            image3 = view.findViewById(R.id.image_3);
            text1 = view.findViewById(R.id.title_1);
            text2 = view.findViewById(R.id.title_2);
            text3 = view.findViewById(R.id.title_3);
            findButton = view.findViewById(R.id.find_button);
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
