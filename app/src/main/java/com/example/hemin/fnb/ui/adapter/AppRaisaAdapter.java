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
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.util.Utils;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.List;

public class AppRaisaAdapter extends RecyclerView.Adapter<AppRaisaAdapter.ViewHolder> {
    private List<AppraisaBean.DataBean.RecordsBean> list = new ArrayList<>();
    private Context context;
    private OnRecyclerItemClickListener monItemClickListener;
    public  AppRaisaAdapter(Context context,List<AppraisaBean.DataBean.RecordsBean> list){
        this.context = context;
        this.list = list;
    }
    public  AppRaisaAdapter(){

    }
    public void addtData(List<AppraisaBean.DataBean.RecordsBean> dataList) {
        if (null != dataList) {
            this.list.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    public void setData(List<AppraisaBean.DataBean.RecordsBean> dataList) {
        if (null != dataList) {
            this.list.clear();
            this.list.addAll(dataList);
            notifyDataSetChanged();
        }
    }

    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener){
        monItemClickListener=listener;
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
                   String c = bean.getImagesUrl().trim();
                  Glide.with(context).load(c).into(holder.collect_logo);
                  String time = Utils.dateToStamp(bean.getCreateTime());
                    String times = Utils.stampToDate(time);
                  holder.app_comit.setText("提交时间："+times);
                  holder.app_number.setText("订单编号："+bean.getCollectionNum());
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
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (monItemClickListener!=null){
                        monItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });
                }


        }
    }

