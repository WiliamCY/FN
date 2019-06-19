package com.example.hemin.fnb.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

public class ApplicationAdapter extends BaseQuickAdapter<Object, BaseViewHolder> {
    private static final int VIEW_TYPE_ONE = 1;
    private static final int VIEW_TYPE_TWO = 2;
    private static final int VIEW_TYPE_THREE = 3;
    private static final int VIEW_TYPE_Four = 4;
    public ApplicationAdapter(int layoutResId, @Nullable List<Object> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {

    }
}
