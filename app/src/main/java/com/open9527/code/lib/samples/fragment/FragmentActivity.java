package com.open9527.code.lib.samples.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.BarUtils;
import com.google.android.material.tabs.TabLayout;
import com.open9527.code.common.activity.CommonScreenActivity;
import com.open9527.code.lib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 15:41.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class FragmentActivity extends CommonScreenActivity {

    private List<Fragment> fragments = new ArrayList<>();
    private String mTitles[] = {"home", "other", "mine"};

    @Override
    public void initData(@Nullable Bundle bundle) {
        BarUtils.setStatusBarLightMode(this, true);

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fragments;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);
        fragments.add(HomeFragment.newInstance());
        fragments.add(OtherFragment.newInstance());
        fragments.add(MineFragment.newInstance());
        FragmentsPagerAdapter FragmentPagerAdapter = new FragmentsPagerAdapter(getSupportFragmentManager(), fragments, mTitles);
        viewPager.setAdapter(FragmentPagerAdapter);
        for (int i = 0; i < mTitles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(mTitles[i]));
//            final View view = LayoutInflater.from(this).inflate(R.layout.item_tabview, null, false);
//            final TextView title = view.findViewById(R.id.tv_title);
//            title.setText(mTitles[i]);
//            tabLayout.addTab(tabLayout.newTab().setCustomView(view));
        }
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }
}
