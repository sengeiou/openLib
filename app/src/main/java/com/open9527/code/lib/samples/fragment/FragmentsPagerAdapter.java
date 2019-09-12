package com.open9527.code.lib.samples.fragment;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.open9527.code.common.pageadapter.CommonPagerAdapter;

import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 16:50.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class FragmentsPagerAdapter extends CommonPagerAdapter {

    private String[] mTitles;

    public FragmentsPagerAdapter(FragmentManager fm, List<Fragment> mFragments, String[] mTitles) {
        super(fm, mFragments);
        this.mTitles = mTitles;
    }

    /**
     * 由于 TabLayout + ViewPager setupWithViewPager导致 removeAllTabs()
     * 必须重写 getPageTitle
     *
     * @param position
     * @return
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];

    }
}
