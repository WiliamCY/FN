package com.example.hemin.fnb.ui.interfaces;

import android.widget.TextView;

public interface OnRecyclerItemClickListener {
    //RecyclerView的点击事件，将信息回调给view
    void onItemClick(int Position,String path);
    void onItemClick(int Position);

}
