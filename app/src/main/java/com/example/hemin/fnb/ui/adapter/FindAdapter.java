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

import java.util.List;

public class FindAdapter extends RecyclerView.Adapter<FindAdapter.ViewHolder> {
    private List<String> imageUrl;
    private Context context;
    private int status;

    public FindAdapter(Context context,List<String> imageUrl,  int status) {
        this.imageUrl = imageUrl;
        this.context = context;
        this.status = status;
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
        String url = imageUrl.get(position).trim();
        switch (status){
            case 0:
                holder.image1.setVisibility(View.GONE);
                holder.image2.setVisibility(View.GONE);
                holder.image3.setVisibility(View.GONE);
//                holder.text1.setVisibility(View.GONE);
//                holder.text2.setVisibility(View.GONE);
//                holder.text3.setVisibility(View.GONE);
//                holder.findButton.setVisibility(View.GONE);
                Glide.with(context).load(url).into(holder.imageView);
                break;
            case 1:
                holder.image1.setVisibility(View.VISIBLE);
                holder.image2.setVisibility(View.VISIBLE);
                holder.image3.setVisibility(View.VISIBLE);
//                holder.text1.setVisibility(View.VISIBLE);
//                holder.text2.setVisibility(View.VISIBLE);
//                holder.text3.setVisibility(View.VISIBLE);
//                holder.findButton.setVisibility(View.GONE);
                Glide.with(context).load(url).into(holder.imageView);
                break;
            case 3:
                holder.image1.setVisibility(View.VISIBLE);
                holder.image2.setVisibility(View.VISIBLE);
                holder.image3.setVisibility(View.VISIBLE);
    //                holder.text1.setVisibility(View.VISIBLE);
    //                holder.text2.setVisibility(View.VISIBLE);
    //                holder.text3.setVisibility(View.VISIBLE);
    //                holder.findButton.setVisibility(View.VISIBLE);
                Glide.with(context).load(url).into(holder.imageView);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return imageUrl.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView image1, image2, image3, text1, text2, text3, findButton;

        public ViewHolder(@NonNull View view) {
            super(view);
            imageView = view.findViewById(R.id.find_logo);
            image1 = view.findViewById(R.id.image_1);
            image2 = view.findViewById(R.id.image_2);
            image3 = view.findViewById(R.id.image_3);
            text1 = view.findViewById(R.id.titl1);
            text2 = view.findViewById(R.id.titl2);
            text3 = view.findViewById(R.id.titl3);
            findButton = view.findViewById(R.id.find_button);
        }
    }
}
