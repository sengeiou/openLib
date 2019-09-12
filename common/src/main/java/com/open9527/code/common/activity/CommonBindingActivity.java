package com.open9527.code.common.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.open9527.code.common.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 14:53.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class CommonBindingActivity<D extends ViewDataBinding> extends CommonScreenActivity {
    protected D mBinding;

    protected abstract CharSequence bindTitle();

    //public abstract boolean isContentScroll();
    //public abstract boolean isCentre();
    protected boolean isContentScroll = false;

    protected boolean isCentre = true;

    protected CoordinatorLayout mCoordinatorLayout;
    protected AppBarLayout mAppBarLayout;
    protected Toolbar mToolbar;
    protected TextView tvTitle;
    protected FrameLayout mContentView;
    protected ViewStub mTitleViewStub;
    protected ViewStub mContentViewStub;

    @Override
    public void setRootLayout(@LayoutRes int layoutId) {
        super.setRootLayout(R.layout.common_activity_title);
        mCoordinatorLayout = findViewById(R.id.common_coordinator_layout);
        mAppBarLayout = findViewById(R.id.common_appbar_layout);
        //title
        mTitleViewStub = findViewById(isCentre ? R.id.common_centre_toolbar : R.id.common_toolbar);
        mTitleViewStub.setVisibility(View.VISIBLE);
        mToolbar = findViewById(R.id.common_title_toolbar);
        //content
        mContentViewStub = findViewById(isContentScroll ? R.id.common_scroll_content : R.id.common_content);
        mContentViewStub.setVisibility(View.VISIBLE);
        mContentView = findViewById(R.id.common_content);
        //inflate
        LayoutInflater.from(this).inflate(layoutId, mContentView);
        //mBinding
        mBinding = DataBindingUtil.bind(getBindView());
        //title text
        setTitleBar();
        //TODO(需要根据UI配置app主题) bar color
        mToolbar.setBackgroundResource(R.drawable.common_bar_bg);
        BarUtils.setStatusBarLightMode(this, true);
        BarUtils.setStatusBarColor(this, Color.WHITE)
                .setBackgroundResource(R.drawable.common_bar_bg);
        BarUtils.addMarginTopEqualStatusBarHeight(mAppBarLayout);
    }

    private void setTitleBar() {
        if (isCentre) {
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

    /**
     * 获取 bindingView
     *
     * @return bindingView
     */
    protected View getBindView() {
        if (mContentView.getChildCount() > 0) {
            return mContentView.getChildAt(0);
        } else {
            LogUtils.i(TAG, "bindingView is null");
        }
        return null;
    }

}
