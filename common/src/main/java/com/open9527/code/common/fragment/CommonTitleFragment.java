package com.open9527.code.common.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ColorUtils;
import com.open9527.code.base.BaseFragment;
import com.open9527.code.common.CommonAppViewModel;
import com.open9527.code.common.CommonApplication;
import com.open9527.code.common.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 15:20.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class CommonTitleFragment extends BaseFragment {


    protected Toolbar mToolbar;
    protected TextView tvTitle;
    protected FrameLayout mRootView;
    protected ViewStub mTitleViewStub, mContentViewStub;
    protected View mViewStatus;
    protected Group mTitleGroup;

    private CommonAppViewModel mCommonAppViewModel;
    private ViewModelProvider mFragmentProvider;
    private ViewModelProvider mActivityProvider;

//    @Override
//    public int bindLayout() {
//        return R.layout.common_fragment_title;
//    }

    @Override
    public void setContentView() {
        mContentView = mInflater.inflate(R.layout.common_fragment_title, null);
        //title
        mTitleGroup = findViewById(R.id.common_title_group);
        mViewStatus = findViewById(R.id.view_status);
        mTitleViewStub = findViewById(hasCentre() ? R.id.common_centre_toolbar : R.id.common_toolbar);
        mTitleViewStub.setVisibility(View.VISIBLE);
        mToolbar = findViewById(R.id.common_title_toolbar);
        //content
        mContentViewStub = findViewById(hasContentScroll() ? R.id.common_scroll_content : R.id.common_content);
        mContentViewStub.setVisibility(View.VISIBLE);
        mRootView = findViewById(R.id.common_content);
        //inflate
        LayoutInflater.from(mActivity).inflate(bindLayout(), mRootView);
        //title text
        setTitleBar();
        //StatusBar
        setStatusBar();
        bindingView();
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        mCommonAppViewModel = ((CommonApplication) mActivity.getApplicationContext()).getAppViewModelProvider(mActivity).get(CommonAppViewModel.class);
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

    protected <T extends ViewModel> T getFragmentViewModel(@NonNull Class<T> modelClass) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(this);
        }
        return mFragmentProvider.get(modelClass);
    }

    protected <T extends ViewModel> T getActivityViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(mActivity);
        }
        return mActivityProvider.get(modelClass);
    }

    public CommonAppViewModel getAppViewModel() {
        return mCommonAppViewModel;
    }
}
