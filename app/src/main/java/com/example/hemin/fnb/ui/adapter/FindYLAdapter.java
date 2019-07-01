package com.example.hemin.fnb.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.UserAbout;
import com.example.hemin.fnb.ui.bean.HDItem;
import com.example.hemin.fnb.ui.bean.YlItem;
import com.example.hemin.fnb.ui.util.AppUtils;

import me.drakeet.multitype.ItemViewBinder;

public class FindYLAdapter extends ItemViewBinder<YlItem, FindYLAdapter.FindZaZhiHolder> {
    @NonNull
    @Override
    protected FindYLAdapter.FindZaZhiHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.find_card5_view,parent,false);
        return new FindZaZhiHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final FindYLAdapter.FindZaZhiHolder holder, @NonNull final YlItem item) {
//        for(int i =0;i<item.url.size();i++){
            Glide.with(AppUtils.getContext()).load(item.bean.getImagesUrl()).into(holder.image);
//        }
//        holder.image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AppUtils.getContext(), UserAbout.class);
//                intent.putExtra("path",item.url.getPathUrl());
//                AppUtils.getContext().startActivity(intent);
//            }
//        });

    }

    public class FindZaZhiHolder extends RecyclerView.ViewHolder {
        ImageView image;
        public FindZaZhiHolder(@NonNull View view) {
            super(view);
            image = view.findViewById(R.id.imagess);
        }
    }
}
