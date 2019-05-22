package com.example.hemin.fnb.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.bean.AppraisaBean;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.List;

public class AppRaisaAdapter extends RecyclerView.Adapter<AppRaisaAdapter.ViewHolder> {
    private List<AppraisaBean.DataBean.RecordsBean> list = new ArrayList<>();
    private Context context;
    public  AppRaisaAdapter(Context context,List<AppraisaBean.DataBean.RecordsBean> list){
        this.context = context;
        this.list = list;
    }
    public  AppRaisaAdapter(){

    }
    @NonNull
    @Override
    public AppRaisaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_appraisal,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AppRaisaAdapter.ViewHolder holder, int position) {
                   AppraisaBean.DataBean.RecordsBean bean = list.get(position);
                  Glide.with(context).load(bean.getImagesUrl()).into(holder.collect_logo);
                  holder.app_comit.setText(bean.getCreateTime());
                  holder.app_number.setText(bean.getCollectionNum());
                  switch (bean.getCollectionAudit()){
                      case "0":
                          holder.app_status.setText("鉴定状态：待审核");
                          break;
                      case "1":
                          holder.app_status.setText("鉴定状态：审核种");
                          break;
                      case "2":
                          holder.app_status.setText("鉴定状态：审核失败");
                          break;
                          default:
                              holder.app_status.setText("鉴定状态：鉴定中");
                              break;
                  }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView collect_logo;
        public  TextView app_number,app_status,app_comit;
        public ViewHolder(@NonNull View view) {
            super(view);
                collect_logo = view.findViewById(R.id.collect_logo);
                app_number = view.findViewById(R.id.title1);
                app_status = view.findViewById(R.id.title2);
                app_comit = view.findViewById(R.id.title3);
                }


        }
    }

