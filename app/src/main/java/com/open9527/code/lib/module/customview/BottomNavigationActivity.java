package com.open9527.code.lib.module.customview;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.blankj.utilcode.util.BarUtils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.open9527.code.common.activity.CommonScreenActivity;
import com.open9527.code.common.pageadapter.CommonPagerAdapter;
import com.open9527.code.customview.viewpager.NoScrollViewPager;
import com.open9527.code.lib.R;

import java.util.ArrayList;
import java.util.List;

import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/29 17:27.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class BottomNavigationActivity extends CommonScreenActivity implements ViewPager.OnPageChangeListener, BottomNavigationView.OnNavigationItemSelectedListener {

    List<Fragment> fragments = new ArrayList<>(4);
    private CommonPagerAdapter mPageAdapter;
    private BottomNavigationView bottomNavigationView;
    private NoScrollViewPager noScrollViewPager;

    @Override
    public void initData(@Nullable Bundle bundle) {
        fragments.add(NavigationFragmentOne.newInstance("001"));
        fragments.add(NavigationFragmentOne.newInstance("002"));
        fragments.add(NavigationFragmentOne.newInstance("003"));
        fragments.add(NavigationFragmentOne.newInstance("004"));
        mPageAdapter = new CommonPagerAdapter(getSupportFragmentManager(),BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragments);
        BarUtils.setStatusBarLightMode(this, true);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_navigation;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        noScrollViewPager = findViewById(R.id.viewpager);
        bottomNavigationView = findViewById(R.id.navigation);
        noScrollViewPager.setAdapter(mPageAdapter);
        // 限制页面数量
        noScrollViewPager.setOffscreenPageLimit(mPageAdapter.getCount());
        // 不使用图标默认变色
        bottomNavigationView.setItemIconTintList(null);
        //添加页面监听
        noScrollViewPager.addOnPageChangeListener(this);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        //app:labelVisibilityMode="labeled"
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case 0:
                bottomNavigationView.setSelectedItemId(R.id.menu_home);
                break;
            case 1:
                bottomNavigationView.setSelectedItemId(R.id.home_found);
                break;
            case 2:
                bottomNavigationView.setSelectedItemId(R.id.home_message);
                break;
            case 3:
                bottomNavigationView.setSelectedItemId(R.id.home_me);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.menu_home:
                noScrollViewPager.setCurrentItem(0);
                return true;
            case R.id.home_found:
                noScrollViewPager.setCurrentItem(1);
                return true;
            case R.id.home_message:
                noScrollViewPager.setCurrentItem(2);
                return true;
            case R.id.home_me:
                noScrollViewPager.setCurrentItem(3);
                return true;
            default:
                break;
        }
        return false;
    }
}
