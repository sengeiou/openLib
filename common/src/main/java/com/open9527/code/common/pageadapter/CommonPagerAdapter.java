package com.open9527.code.common.pageadapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.blankj.utilcode.util.LogUtils;

import java.util.List;

/**
 * Created by     : Mr.kk.
 * Created times  : on 2019/6/26 18:23.
 * E-Mail Address ：open_9527@163.com.
 * DESC :通用PagerAdapter.
 */
public class CommonPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "CommonPagerAdapter";
    private List<Fragment> mFragments;
    private FragmentManager mFragmentManager;

    public CommonPagerAdapter(FragmentManager fm, int behavior, List<Fragment> mFragments) {
        super(fm,behavior);
        this.mFragmentManager = fm;
        this.mFragments = mFragments;
    }

    public CommonPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        mFragmentManager.beginTransaction().show(fragment).commitAllowingStateLoss();
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Fragment fragment = mFragments.get(position);
        mFragmentManager.beginTransaction().hide(fragment).commitAllowingStateLoss();
    }

}