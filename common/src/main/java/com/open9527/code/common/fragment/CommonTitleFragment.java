package com.open9527.code.common.fragment;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.blankj.utilcode.util.BarUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.open9527.code.base.BaseLazyFragment;
import com.open9527.code.common.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 15:20.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class CommonTitleFragment extends BaseLazyFragment {
    public abstract CharSequence bindTitle();

    //public abstract boolean isContentScroll();
    //public abstract boolean isCentre();
    protected boolean isContentScroll = false;

    protected boolean isCentre = true;


    protected Toolbar mToolbar;
    protected TextView tvTitle;
    protected FrameLayout mContentView;
    protected ViewStub mTitleViewStub;
    protected ViewStub mContentViewStub;
    protected View mViewStatus;

    @Override
    public void setRootLayout(@LayoutRes int layoutId) {
        super.setRootLayout(R.layout.common_fragment_title);
        //title
        mViewStatus = findViewById(R.id.view_status);
        mTitleViewStub = findViewById(isCentre ? R.id.common_centre_toolbar : R.id.common_toolbar);
        mTitleViewStub.setVisibility(View.VISIBLE);
        mToolbar = findViewById(R.id.common_title_toolbar);
        //content
        mContentViewStub = findViewById(isContentScroll ? R.id.common_scroll_content : R.id.common_content);
        mContentViewStub.setVisibility(View.VISIBLE);
        mContentView = findViewById(R.id.common_content);
        //inflate
        LayoutInflater.from(mActivity).inflate(layoutId, mContentView);
        //title text
        setTitleBar();
        //StatusBar
        setStatusBar();
    }

    protected void setStatusBar() {
        //TODO(需要根据UI配置app主题) bar color
//        mToolbar.setBackgroundResource(R.drawable.common_bar_bg);
//        BarUtils.setStatusBarLightMode(mActivity, true);
//        BarUtils.setStatusBarColor(mActivity, Color.WHITE)
//                .setBackgroundResource(R.drawable.common_bar_bg);
//        BarUtils.addMarginTopEqualStatusBarHeight(mAppBarLayout);
    }

    private void setTitleBar() {
        if (isCentre) {
            tvTitle = findViewById(R.id.common_tv_title);
            if (tvTitle != null) {
                tvTitle.setText(bindTitle());
            }
        } else {
            ((AppCompatActivity) mActivity).setSupportActionBar(mToolbar);
            ActionBar titleBar = ((AppCompatActivity) mActivity).getSupportActionBar();
            if (titleBar != null) {
                titleBar.setDisplayHomeAsUpEnabled(true);
                titleBar.setTitle(bindTitle());
            }
        }
    }
}
