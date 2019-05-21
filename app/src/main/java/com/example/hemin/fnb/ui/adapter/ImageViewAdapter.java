package com.example.hemin.fnb.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class ImageViewAdapter extends RecyclerView.Adapter<ImageViewAdapter.ViewHolder> {
    private List<String> imagePath = new ArrayList<>();
    private OnRecyclerItemClickListener monItemClickListener;
    public   ImageViewAdapter(){

    }
    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener){
        monItemClickListener=listener;
    }

    private Context context;
    @NonNull
    @Override
    public ImageViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_imageview,parent,false);
//        ViewHolder holder = new ViewHolder(view);
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int widthPixels = metrics.widthPixels;
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width=widthPixels/5;
        ViewHolder holder=new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewAdapter.ViewHolder holder, final int position) {
        if(position>0){
        holder.imageViewTtile.setVisibility(View.GONE);

        }else if(position == 3){
            holder.imageViews.setColorFilter(ContextCompat.getColor(context, R.color.cD0915E));
        }

        Glide.with(context).load(imagePath.get(position)).into(holder.imageViews);
        holder.imageViewDeltet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(imagePath.size() == 0){
                    Toast.makeText(context,"最后一条数据不能删除",Toast.LENGTH_SHORT).show();
                }else {
                    removeData(position);
                    Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public  ImageViewAdapter(List<String>  imagePath,Context context){
                this.imagePath = imagePath;
                this.context = context;
    }
    //  添加数据
    public void addData(int position) {
//      在list中添加数据，并通知条目加入一条
        imagePath.add(position, "添加成功" + position);
        //添加动画
        notifyItemInserted(position);
    }
    @Override
    public int getItemCount() {
        return imagePath.size();
    }
    //  删除数据
    public void removeData(int position) {
        imagePath.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public    ImageView imageViews,imageViewDeltet;
                    TextView imageViewTtile;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViews = itemView.findViewById(R.id.image_adapter);
            imageViewDeltet = itemView.findViewById(R.id.image_delete);
            imageViewTtile = itemView.findViewById(R.id.image_title);
            imageViewDeltet.setOnClickListener(new View.OnClickListener() {
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
