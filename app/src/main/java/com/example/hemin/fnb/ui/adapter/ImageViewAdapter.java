package com.example.hemin.fnb.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewAdapter.ViewHolder> {
    private List<String> imagePath = new ArrayList<>();
    private OnRecyclerItemClickListener monItemClickListener;
    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener){
        monItemClickListener=listener;
    }

    private Context context;
    @NonNull
    @Override
    public ImageViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_imageview,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewAdapter.ViewHolder holder, int position) {
        Glide.with(context).load(imagePath.get(position)).into(holder.imageViews);
    }
    public  ImageViewAdapter(List<String>  imagePath,Context context){
                this.imagePath = imagePath;
                this.context = context;
    }

    @Override
    public int getItemCount() {
        return imagePath.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public  final  ImageView imageViews;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViews = itemView.findViewById(R.id.image_adapter);
          itemView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  if (monItemClickListener!=null){
                      monItemClickListener.onItemClick(getAdapterPosition(),imagePath.get(getAdapterPosition()));
                  }
              }
          });

        }
    }
}
