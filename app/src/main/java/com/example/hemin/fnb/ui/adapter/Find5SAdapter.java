package com.example.hemin.fnb.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.hemin.fnb.R;
import com.example.hemin.fnb.ui.activity.PHBActivity;
import com.example.hemin.fnb.ui.activity.TaskBigImgActivity;
import com.example.hemin.fnb.ui.activity.TaskBigImgActivityS;
import com.example.hemin.fnb.ui.bean.FindBean;
import com.example.hemin.fnb.ui.util.AppUtils;
import com.example.hemin.fnb.ui.util.Utils;
import com.nostra13.universalimageloader.utils.L;

import java.util.ArrayList;
import java.util.List;

public class Find5SAdapter extends BaseQuickAdapter<FindBean, BaseViewHolder> {
    private ArrayList<String> path = new ArrayList<>();
    private RecyclerView zzRecyclerview;
    private List<FindBean> beans = new ArrayList<>();
    public Find5SAdapter(int layoutResId, @Nullable List<FindBean> data,int status) {
        super(layoutResId, data);
        if(status == 6){
            beans = data;
        }

    }


    @Override
    protected void convert(final BaseViewHolder helper, final FindBean item) {

            if(item.getGiveNum() != null  && item.getCollectionNum() != null && item.getWantNum() != null  && item.getIsPHB() == 2 ){
                helper.getView(R.id.css).setVisibility(View.VISIBLE);
                helper.getView(R.id.imagess).setVisibility(View.GONE);
                helper.setText(R.id.title_1,item.getGiveNum());
                helper.setText(R.id.title_2,item.getCollectionNum());
                helper.setText(R.id.title_3,item.getWantNum());
                helper.getView(R.id.find_button).setVisibility(View.VISIBLE);
                helper.getView(R.id.find_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                      Intent intent = new Intent(AppUtils.getContext(), PHBActivity.class);
                        AppUtils.getContext().startActivity(intent);
                    }
                });
                Glide.with(mContext).load(item.getImageUrl()).into((ImageView) helper.getView(R.id.find_logo));
            }else if(item.getGiveNum() != null  && item.getCollectionNum() != null && item.getWantNum() != null){
                helper.getView(R.id.css).setVisibility(View.VISIBLE);
                helper.getView(R.id.imagess).setVisibility(View.GONE);
                helper.setText(R.id.title_1,item.getGiveNum());
                helper.setText(R.id.title_2,item.getCollectionNum());
                helper.setText(R.id.title_3,item.getWantNum());
                helper.getView(R.id.find_button).setVisibility(View.GONE);
                Glide.with(mContext).load(item.getImageUrl()).into((ImageView) helper.getView(R.id.find_logo));
                helper.getView(R.id.find_logo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         path = item.getDailyList();
                         if(path.size()>0){
                             Intent imgIntent = new Intent(AppUtils.getContext(), TaskBigImgActivityS.class);
                             imgIntent.putStringArrayListExtra("paths", path);
                             imgIntent.putExtra("title", "每日鉴赏");
                             AppUtils.getContext().startActivity(imgIntent);
                         }
                    }
                });
            } else if(item.getIsPHB() == 3){
                helper.getView(R.id.imagess).setVisibility(View.GONE);
                helper.getView(R.id.refreshLayout).setVisibility(View.VISIBLE);
                helper.getView(R.id.zzRecyclerview).setVisibility(View.VISIBLE);
                zzRecyclerview = helper.getView(R.id.zzRecyclerview);
                helper.getView(R.id.zaText).setVisibility(View.VISIBLE);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                zzRecyclerview.setLayoutManager(linearLayoutManager);
                MessageZZApdater apdater = new MessageZZApdater(R.layout.message_adapter,beans);
                zzRecyclerview.setAdapter(apdater);


            } else if(item.getIsPHB() == 4){
                helper.getView(R.id.find_button).setVisibility(View.GONE);
                helper.getView(R.id.imagess).setVisibility(View.VISIBLE);
                helper.addOnClickListener(R.id.imagess);
                Glide.with(mContext).load(item.getImageUrl()).into((ImageView) helper.getView(R.id.imagess));
            }





    }
//    private List<Find5Bean.DataBean.RecordsBean> beans;
//    private Context context;
//    private OnRecyclerItemClickListener monItemClickListener;
//
//    public Find5Adapter(Context context, List<Find5Bean.DataBean.RecordsBean> beans) {
//        this.beans = beans;
//        this.context = context;
//    }
//
//    public void setRecyclerItemClickListener(OnRecyclerItemClickListener listener) {
//        monItemClickListener = listener;
//    }
//
//    @NonNull
//    @Override
//    public Find5Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_card5_view, parent, false);
//        Find5Adapter.ViewHolder holder = new Find5Adapter.ViewHolder(view);
//        return holder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Find5Adapter.ViewHolder holder, int position) {
//
//        Find5Bean.DataBean.RecordsBean bean = beans.get(position);
//
//
////        for(int i =0 ;i<bean.getImageUrl().size();i++){
////             String url = beans.get(i).getPathUrl();
////            Log.d("imageUrlAdapter",url);
////            Glide.with(context).load(url).into(holder.imageView);
////        }
////
////                    for(int i = 0;i<bean.getImageUrl().size();i++){
////                         urls = bean.getImageUrl().get(i);
////                    Log.d("imageUrlAdapter",urls);
////                    }
//
//
////        int status = bean.getStatus();
////        Log.d("imageUrlAdapter",bean.getImageUrl());
////        switch (status) {
////            case 0:
////                holder.imageView2.setVisibility(View.VISIBLE);
////                holder.imageView.setVisibility(View.GONE);
////                holder.linearLayout.setVisibility(View.GONE);
////                holder.findButton.setVisibility(View.GONE);
////                Glide.with(context).load(bean.getImageUrl().trim()).into(holder.imageView2);
////                break;
////            case 1:
////                holder.imageView2.setVisibility(View.GONE);
////                holder.imageView.setVisibility(View.VISIBLE);
////                holder.linearLayout.setVisibility(View.VISIBLE);
////                holder.findButton.setVisibility(View.GONE);
////                Glide.with(context).load(bean.getImageUrl().trim()).into(holder.imageView);
////                break;
////            case 2:
////                holder.imageView2.setVisibility(View.GONE);
////                holder.imageView.setVisibility(View.VISIBLE);
////                holder.linearLayout.setVisibility(View.VISIBLE);
////                holder.findButton.setVisibility(View.VISIBLE);
////                Glide.with(context).load(bean.getImageUrl().trim()).into(holder.imageView);
////                break;
////            default:
////                break;
////        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return beans.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        public ImageView imageView, imageView2;
//        public TextView image1, image2, image3, text1, text2, text3, findButton;
//        public LinearLayout linearLayout;
//
//        public ViewHolder(@NonNull View view) {
//            super(view);
//            linearLayout = view.findViewById(R.id.layouts);
//            imageView = view.findViewById(R.id.find_logo);
//            imageView2 = view.findViewById(R.id.find_logo2);
//            image1 = view.findViewById(R.id.image_1);
//            image2 = view.findViewById(R.id.image_2);
//            image3 = view.findViewById(R.id.image_3);
//            text1 = view.findViewById(R.id.title_1);
//            text2 = view.findViewById(R.id.title_2);
//            text3 = view.findViewById(R.id.title_3);
//            findButton = view.findViewById(R.id.find_button);
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (monItemClickListener != null) {
//                        monItemClickListener.onItemClick(getAdapterPosition());
//                    }
//                }
//            });
//        }
//
//
//    }
}
