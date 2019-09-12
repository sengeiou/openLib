package com.open9527.code.lib.samples.tablayout;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.AdaptScreenUtils;
import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.tabs.TabLayout;
import com.open9527.code.common.activity.CommonTitleActivity;
import com.open9527.code.customview.tablayout.CustomTabLayout;
import com.open9527.code.customview.tablayout.CustomTabView;
import com.open9527.code.lib.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/11 13:21.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class TabLayoutActivity extends CommonTitleActivity {

    private TabLayout tabLayout;
    private TextView title;
    private String mTitles[] = {"上海", "头条推荐", "生活", "娱乐八卦", "体育", "段子", "美食", "电影", "科技", "搞笑",
            "社会", "财经", "时尚", "汽车", "军事", "小说", "育儿", "职场", "萌宠", "游戏", "健康", "动漫", "互联网"};
    private CustomTabLayout customTabLayout, customTabLayout1;

    @Override
    public CharSequence bindTitle() {
        return "TabLayoutActivity";
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_tablayout;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        tabLayout = findViewById(R.id.tab_layout);
        customTabLayout = findViewById(R.id.custom_tab_layout);
        customTabLayout1 = findViewById(R.id.custom_tab_layout_1);
        initTabView();
        initCustomTabView();
        initCustomTabView1();

    }

    private void initCustomTabView1() {
        for (int i = 0; i < mTitles.length; i++) {
            final CustomTabView customTabView = new CustomTabView(this);
            customTabView.setText(mTitles[i]);
            customTabView.setTag(i);
            customTabView.setTextChangeColor(getResources().getColor(R.color.colorAccent));
            customTabView.setTextOriginColor(getResources().getColor(R.color.color_333));
            customTabView.setDirection(2);
            customTabView.setProgress(5f);
            customTabView.setTextSize(AdaptScreenUtils.pt2Px(40));
            customTabLayout1.addTab(customTabLayout1.newTab().setCustomView(customTabView));
        }
    }

    private void initCustomTabView() {
        for (int i = 0; i < mTitles.length; i++) {
            TabLayout.Tab tab = customTabLayout.newTab();
            tab.setTag(i);
            tab.setText(mTitles[i]);
            customTabLayout.addTab(tab);
        }
        customTabLayout.setIndicator(AdaptScreenUtils.pt2Px(5), AdaptScreenUtils.pt2Px(5));
    }

    private void initTabView() {
        for (int i = 0; i < mTitles.length; i++) {
            final View view = LayoutInflater.from(mActivity).inflate(R.layout.item_tabview, null, false);
            final TextView title = view.findViewById(R.id.tv_title);
            title.setText(mTitles[i]);
            if (title.getText().equals("体育")) {
                Drawable drawable = getResources().getDrawable(R.mipmap.ic_launcher);
                drawable.setBounds(0, 0, AdaptScreenUtils.pt2Px(40), AdaptScreenUtils.pt2Px(40));// 设置边界
                title.setCompoundDrawables(drawable, null, null, null);
            }
            tabLayout.addTab(tabLayout.newTab().setCustomView(view));
        }
        setTabLayoutSelected(tabLayout.getTabAt(0), getResources().getColor(R.color.colorAccent), View.VISIBLE);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                LogUtils.i(TAG, "选中");
                setTabLayoutSelected(tab, getResources().getColor(R.color.colorAccent), View.VISIBLE);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                LogUtils.i(TAG, "未选中");
                setTabLayoutSelected(tab, getResources().getColor(R.color.color_333), View.INVISIBLE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                LogUtils.i(TAG, "再次选中");
                setTabLayoutSelected(tab, getResources().getColor(R.color.colorAccent), View.VISIBLE);
            }
        });
    }

    /**
     * 配置选中/未选中状态
     *
     * @param tab
     * @param color
     * @param visibility
     */
    private void setTabLayoutSelected(TabLayout.Tab tab, int color, int visibility) {
        TextView title = tab.getCustomView().findViewById(R.id.tv_title);
        View view = tab.getCustomView().findViewById(R.id.selected_view);
        title.setTextColor(color);

        view.setVisibility(visibility);
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }
}
