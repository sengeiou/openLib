package com.open9527.code.lib.module.fragment.common;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/28/028 16:11.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class LazyFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> items;

    public LazyFragmentAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void setItems(List<Fragment> list){
        this.items = list;
    }

    public List<Fragment> getItems() {
        return items;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return items==null?null:items.get(position);
    }

    @Override
    public int getCount() {
        return items==null?0:items.size();
    }
}

