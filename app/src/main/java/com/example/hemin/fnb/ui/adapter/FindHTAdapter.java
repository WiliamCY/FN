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
import com.example.hemin.fnb.ui.bean.Find2Tiem;
import com.example.hemin.fnb.ui.util.AppUtils;

import me.drakeet.multitype.ItemViewBinder;

public class FindHTAdapter extends ItemViewBinder<Find2Tiem, FindHTAdapter.FindZaZhiHolder> {
    @NonNull
    @Override
    protected FindHTAdapter.FindZaZhiHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.find_cardc_view,parent,false);
        return new FindZaZhiHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final FindHTAdapter.FindZaZhiHolder holder, @NonNull final Find2Tiem item) {
            Glide.with(AppUtils.getContext()).load(item.beans.getTopicUrl().trim()).into(holder.image);
               holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppUtils.getContext(), UserAbout.class);
                intent.putExtra("path",item.beans.getTopicUrl());
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
