package com.example.hemin.fnb.ui.interfaces;

import android.widget.TextView;

public interface OnFindClickListener {
    //RecyclerView的点击事件，将信息回调给view
    void onItemClick(TextView textView,int Position);

}
