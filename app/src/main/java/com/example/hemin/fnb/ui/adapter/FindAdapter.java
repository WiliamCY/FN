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
import com.example.hemin.fnb.ui.bean.FindBean;

import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder> {
    private  List<FindBean> beans = new ArrayList<>();
    private Context context;
    private OnRecyclerItemClickListener monItemClickListener;
    private String path;

    public FindAdapter(Context context,  List<FindBean> bean) {
        this.beans = bean;
        this.context = context;
    }

    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
        monItemClickListener = listener;
    }

    @NonNull
    @Override
    public FindAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_card_view, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull FindAdapter.ViewHolder holder, int position) {

        FindBean bean = beans.get(position);
        Log.d("findImmageViewPath",bean.getImageUrl());
        Glide.with(context).load(bean.getImageUrl()).into(holder.imageView2);


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
