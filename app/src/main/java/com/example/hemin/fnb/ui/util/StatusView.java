package com.example.hemin.fnb.ui.util;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import com.example.hemin.fnb.R;


/**
 * 状态栏
 */
public class StatusView extends View {

    private int mBarSize;
    private boolean isStatusGradient = true;

    public StatusView(Context context) {
        this(context, null, 0);
    }

    public StatusView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Resources resources = getResources();
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.StatusView);
        isStatusGradient = a.getBoolean(R.styleable.StatusView_is_status_gradient,false);
        int resourceId = resources.getIdentifier("status_bar_height", "dimen", "android");
        mBarSize = resources.getDimensionPixelSize(resourceId);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), mBarSize);
            if(isStatusGradient && Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
                setBackgroundResource(R.drawable.status_bar_gradient);
            }
        } else {
            setMeasuredDimension(0, mBarSize);
        }
    }

    public boolean isStatusGradient() {
        return isStatusGradient;
    }

    public void setStatusGradient(boolean statusGradient) {
        isStatusGradient = statusGradient;
    }

    /**
     * Get status bar height.
     */
    public int getBarSize() {
        return mBarSize;
    }
}