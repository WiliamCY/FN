package com.example.hemin.fnb.ui.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.AboutActivity;
import com.example.hemin.fnb.ui.activity.UserAbout;
import com.example.hemin.fnb.ui.bean.MessageBean1;
import com.example.hemin.fnb.ui.bean.ZZItem;
import com.example.hemin.fnb.ui.bean.ZZSItem;
import com.example.hemin.fnb.ui.util.AppUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import me.drakeet.multitype.ItemViewBinder;

public class FindZaZhiSAdapter extends ItemViewBinder<ZZSItem, FindZaZhiSAdapter.FindZaZhiHolder> {

    @NonNull
    @Override
    protected FindZaZhiSAdapter.FindZaZhiHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.messages_adapter,parent,false);
        return new FindZaZhiHolder(view);
    }



    @Override
    protected void onBindViewHolder(@NonNull FindZaZhiSAdapter.FindZaZhiHolder holder, @NonNull final ZZSItem item) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AppUtils.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        holder.recyclerView.setLayoutManager(linearLayoutManager);
        FindZaZhiAdapter adapter = new FindZaZhiAdapter(R.layout.message_adapter,item.bean);
        holder.recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(AppUtils.getContext(), UserAbout.class);
                intent.putExtra("path",item.bean.get(position).getMagazineContent());
                AppUtils.getContext().startActivity(intent);
            }
        });
        holder.smartRefreshLayout.setRefreshHeader(new ClassicsHeader(AppUtils.getContext()));
        holder.smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }
        });
    }

    public class FindZaZhiHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        SmartRefreshLayout smartRefreshLayout;
        public FindZaZhiHolder(@NonNull View view) {
            super(view);
            recyclerView = view.findViewById(R.id.mazhi_recyclerview);
            smartRefreshLayout = view.findViewById(R.id.smartRefresh);

        }
    }
}
