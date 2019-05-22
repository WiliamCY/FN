package com.example.hemin.fnb.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public abstract class BaseFragmentPagerAdapter extends FragmentPagerAdapter {

    private String[] mTitles;
    private static final String EMPTY = "";

    public BaseFragmentPagerAdapter(FragmentManager fm, String... titles) {
        super(fm);
        mTitles = titles;
    }

    public BaseFragmentPagerAdapter(FragmentManager fm, int count) {
        super(fm);
        mTitles = new String[count];
        for(int i = 0;i <count;i++){
            mTitles[i] =EMPTY;
        }
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }

  /*  @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }*/

}
