package com.open9527.code.common.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.LayoutRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.Group;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.open9527.code.base.BaseLazyFragment;
import com.open9527.code.common.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 15:20.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class CommonTitleFragment extends BaseLazyFragment {



    protected Toolbar mToolbar;
    protected TextView tvTitle;
    protected FrameLayout mContentView;
    protected ViewStub mTitleViewStub, mContentViewStub;
    protected View mViewStatus;
    protected Group mTitleGroup;

    @Override
    public void setRootLayout(@LayoutRes int layoutId) {
        super.setRootLayout(R.layout.common_fragment_title);
        //title
        mTitleGroup = findViewById(R.id.common_title_group);
        mViewStatus = findViewById(R.id.view_status);
        mTitleViewStub = findViewById(hasCentre() ? R.id.common_centre_toolbar : R.id.common_toolbar);
        mTitleViewStub.setVisibility(View.VISIBLE);
        mToolbar = findViewById(R.id.common_title_toolbar);
        //content
        mContentViewStub = findViewById(hasContentScroll() ? R.id.common_scroll_content : R.id.common_content);
        mContentViewStub.setVisibility(View.VISIBLE);
        mContentView = findViewById(R.id.common_content);
        //inflate
        LayoutInflater.from(mActivity).inflate(layoutId, mContentView);
        //title text
        setTitleBar();
        //StatusBar
        setStatusBar();
    }

    /**
     * 配置app主题UI样式
     * 需要定制不同的可以自行重写该方法
     */
    protected void setStatusBar() {
        mToolbar.setBackgroundResource(R.drawable.common_bar_bg);
//        BarUtils.setStatusBarColor(mActivity, Color.WHITE)
//                .setBackgroundResource(R.drawable.common_bar_bg);
        BarUtils.setStatusBarColor(mViewStatus, ColorUtils.getColor(R.color.color_fff));
    }

    private void setTitleBar() {
        if (hasCentre()) {
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
