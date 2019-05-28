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
import com.example.hemin.fnb.ui.bean.FindDeilyBean;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class Find3Adapter extends RecyclerView.Adapter<Find3Adapter.ViewHolder> {
    private List<FindDeilyBean.DataBean.ListBean>  beans = new ArrayList<>();
    private Context context;
    private OnRecyclerItemClickListener monItemClickListener;

    public Find3Adapter(Context context, List<FindDeilyBean.DataBean.ListBean>  beans) {
        this.beans = beans;
        this.context = context;
    }

    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
        monItemClickListener = listener;
    }

    @NonNull
    @Override
    public Find3Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_card3_view, parent, false);
        Find3Adapter.ViewHolder holder = new Find3Adapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Find3Adapter.ViewHolder holder, int position) {
        FindDeilyBean.DataBean.ListBean bean = beans.get(position);


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
