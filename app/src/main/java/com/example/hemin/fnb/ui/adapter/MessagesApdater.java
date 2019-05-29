package com.example.hemin.fnb.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemin.fnb.R;

public class MessagesApdater extends RecyclerView.Adapter<MessagesApdater.ViewHolder> {
    @NonNull
    @Override
    public MessagesApdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_adapter,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MessagesApdater.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 0;
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
        }
    }
}
