package com.example.hemin.fnb.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.PHBActivity;
import com.example.hemin.fnb.ui.activity.TaskBigImgActivityS;
import com.example.hemin.fnb.ui.bean.DailyItem;
import com.example.hemin.fnb.ui.bean.RankItem;
import com.example.hemin.fnb.ui.util.AppUtils;

import java.util.ArrayList;

import me.drakeet.multitype.ItemViewBinder;

public class FindRankdapter extends ItemViewBinder<RankItem, FindRankdapter.FindZaZhiHolder> {
    private ArrayList<String> path = new ArrayList<>();

    @NonNull
    @Override
    protected FindRankdapter.FindZaZhiHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.find_card5_view, parent, false);
        return new FindZaZhiHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull final FindRankdapter.FindZaZhiHolder holder, @NonNull final RankItem item) {
        holder.css.setVisibility(View.VISIBLE);
        holder.imagess.setVisibility(View.GONE);
        holder.find_button.setVisibility(View.VISIBLE);
        holder.title_1.setText(item.bean.getGiveNum());
        holder.title_2.setText(item.bean.getCollectionNum());
        holder.title_3.setText(item.bean.getWantNum());
        Glide.with(AppUtils.getContext()).load(item.bean.getImagesUrl()).into(holder.find_logo);
        holder.find_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppUtils.getContext(), PHBActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                AppUtils.getContext().startActivity(intent);

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
