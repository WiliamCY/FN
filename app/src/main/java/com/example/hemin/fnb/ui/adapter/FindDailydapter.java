package com.example.hemin.fnb.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.TaskBigImgActivityS;
import com.example.hemin.fnb.ui.activity.UserAbout;
import com.example.hemin.fnb.ui.bean.DailyItem;
import com.example.hemin.fnb.ui.bean.Find2Tiem;
import com.example.hemin.fnb.ui.bean.FindDeilyBean;
import com.example.hemin.fnb.ui.interfaces.OnFindClickListener;
import com.example.hemin.fnb.ui.interfaces.OnRecyclerItemClickListener;
import com.example.hemin.fnb.ui.util.AppUtils;

import java.util.ArrayList;

import me.drakeet.multitype.ItemViewBinder;

public class FindDailydapter extends ItemViewBinder<DailyItem, FindDailydapter.FindZaZhiHolder> {
    private ArrayList<String> path = new ArrayList<>();
    private OnFindClickListener monItemClickListener;
    public void setRecyclerItemClickListener(OnFindClickListener monItemClickListener) {
        this.monItemClickListener = monItemClickListener;
    }

    @NonNull
    @Override
    protected FindDailydapter.FindZaZhiHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.find_card5_view, parent, false);
        return new FindZaZhiHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final FindDailydapter.FindZaZhiHolder holder, @NonNull final DailyItem item) {
               holder.css.setVisibility(View.VISIBLE);
               holder.imagess.setVisibility(View.GONE);
               holder.find_button.setVisibility(View.GONE);
               holder.title_1.setText(item.bean.getGiveNum());
               holder.title_2.setText(item.bean.getIsCollectionSum());
               holder.title_3.setText(item.bean.getWantNum());
               Glide.with(AppUtils.getContext()).load(item.bean.getList().get(0).getImagesUrl()).into(holder.find_logo);
               holder.find_logo.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                           for(int i =0 ;i<item.bean.getList().size();i++){
                               String c = item.bean.getList().get(i).getImagesUrl();
                               path.add(c);
                           }
                       if(path.size()>0) {
                           Intent imgIntent = new Intent(AppUtils.getContext(), TaskBigImgActivityS.class);
                           imgIntent.putStringArrayListExtra("paths", path);
                           imgIntent.putExtra("title", "每日鉴赏");
                           AppUtils.getContext().startActivity(imgIntent);
                       }
                   }
               });
            holder.title_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(monItemClickListener != null){
                        monItemClickListener.onItemClick(item.bean.getDailyId(),holder.title_1,0);
                    }else {
                      Toast.makeText(AppUtils.getContext(),"11111",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        holder.title_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monItemClickListener != null){
                    monItemClickListener.onItemClick(item.bean.getDailyId(),holder.title_2,1);
                }else {
                    Toast.makeText(AppUtils.getContext(),"11111",Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.title_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monItemClickListener != null){
                    monItemClickListener.onItemClick(item.bean.getDailyId(),holder.title_3,2);
                }else {
                    Toast.makeText(AppUtils.getContext(),"11111",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public class FindZaZhiHolder extends RecyclerView.ViewHolder {
        ConstraintLayout css;
        ImageView imagess, find_logo;
        TextView title_1, title_2, title_3;
        TextView find_button;

        public FindZaZhiHolder(@NonNull View view) {
            super(view);
            css = view.findViewById(R.id.css);
            imagess = view.findViewById(R.id.imagess);
            find_logo = view.findViewById(R.id.find_logo);
            title_1 = view.findViewById(R.id.title_1);
            title_2 = view.findViewById(R.id.title_2);
            title_3 = view.findViewById(R.id.title_3);
            find_button = view.findViewById(R.id.find_button);

        }
    }
}
