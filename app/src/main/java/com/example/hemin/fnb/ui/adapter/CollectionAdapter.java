package com.example.hemin.fnb.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.bean.ColletionBean;

import java.util.ArrayList;
import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {
    private List<ColletionBean.DataBean.ListBean> listBeans = new ArrayList<>();
    private Context context;
    public CollectionAdapter(){

    }
    public  CollectionAdapter(Context context,List<ColletionBean.DataBean.ListBean> list ){
        this.context = context;
        this.listBeans = list;
    }
    @NonNull
    @Override
    public CollectionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.collection_image_result,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CollectionAdapter.ViewHolder holder, int position) {
       ColletionBean.DataBean.ListBean bean = listBeans.get(position);

    }

    @Override
    public int getItemCount() {
        return listBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView coolectImage;
        public TextView collectResult;
        public ViewHolder(@NonNull View view) {
            super(view);
            coolectImage = view.findViewById(R.id.collection_image);
            collectResult = view.findViewById(R.id.collection_title);
        }
    }
}
