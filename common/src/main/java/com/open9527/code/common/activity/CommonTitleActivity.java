package com.open9527.code.common.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.open9527.code.common.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/30 15:36.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class CommonTitleActivity extends CommonScreenActivity {

    protected CoordinatorLayout mCoordinatorLayout;
    protected AppBarLayout mAppBarLayout;
    protected Toolbar mToolbar;
    protected TextView tvTitle;
    protected FrameLayout mRootView;
    protected ViewStub mTitleViewStub;
    protected ViewStub mContentViewStub;


    @Override
    public void setContentView() {
        mContentView = LayoutInflater.from(this).inflate(R.layout.common_activity_title, null);
        setContentView(mContentView);

        mCoordinatorLayout = findViewById(R.id.common_coordinator_layout);
        mAppBarLayout = findViewById(R.id.common_appbar_layout);
        //title
        mTitleViewStub = findViewById(hasCentre() ? R.id.common_centre_toolbar : R.id.common_toolbar);
        mTitleViewStub.setVisibility(View.VISIBLE);
        mToolbar = findViewById(R.id.common_title_toolbar);
        //content
        mContentViewStub = findViewById(hasContentScroll() ? R.id.common_scroll_content : R.id.common_content);
        mContentViewStub.setVisibility(View.VISIBLE);
        mRootView = findViewById(R.id.common_content);
        //inflate
        LayoutInflater.from(this).inflate(bindLayout(), mRootView);
        //title text
        setTitleBar();
        //StatusBar
        setStatusBar();
        bindingView();
    }


    /**
     * 配置app主题UI样式
     * 需要定制不同的可以自行重写该方法
     */
    protected void setStatusBar() {
        mToolbar.setBackgroundResource(R.drawable.common_bar_bg);
        BarUtils.setStatusBarLightMode(this, true);
        BarUtils.setStatusBarColor(mActivity, ColorUtils.getColor(R.color.color_fff))
                .setBackgroundResource(R.drawable.common_bar_bg);
        BarUtils.addMarginTopEqualStatusBarHeight(mCoordinatorLayout);
    }


    private void setTitleBar() {
        if (hasCentre()) {
            tvTitle = findViewById(R.id.common_tv_title);
            if (tvTitle != null) {
                tvTitle.setText(bindTitle());
            }
        } else {
            setSupportActionBar(mToolbar);
            ActionBar titleBar = getSupportActionBar();
            if (titleBar != null) {
                titleBar.setDisplayHomeAsUpEnabled(true);
                titleBar.setTitle(bindTitle());
            }
        }
    }

}
