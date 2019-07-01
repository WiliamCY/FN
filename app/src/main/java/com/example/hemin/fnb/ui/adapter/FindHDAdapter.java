package com.example.hemin.fnb.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.AboutActivity;
import com.example.hemin.fnb.ui.activity.UserAbout;
import com.example.hemin.fnb.ui.bean.FindBean;
import com.example.hemin.fnb.ui.bean.HDItem;
import com.example.hemin.fnb.ui.util.AppUtils;

import java.util.List;

import me.drakeet.multitype.ItemViewBinder;

public class FindHDAdapter extends ItemViewBinder<HDItem, FindHDAdapter.FindZaZhiHolder> {
    @NonNull
    @Override
    protected FindHDAdapter.FindZaZhiHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.find_card5_view,parent,false);
        return new FindZaZhiHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final FindHDAdapter.FindZaZhiHolder holder, @NonNull final HDItem item) {
//        for(int i =0;i<item.url.size();i++){
            Glide.with(AppUtils.getContext()).load(item.url.getImageUrl()).into(holder.image);
//        }
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppUtils.getContext(), UserAbout.class);
                intent.putExtra("path",item.url.getPathUrl());
                AppUtils.getContext().startActivity(intent);
            }
        });

    }

    public class FindZaZhiHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public FindZaZhiHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.imagess);
        }
    }
}
