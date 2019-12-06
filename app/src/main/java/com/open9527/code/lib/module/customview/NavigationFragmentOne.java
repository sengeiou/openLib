package com.open9527.code.lib.module.customview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.common.databinding.CommonBindingFragment;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.FragmentNavigationOneBinding;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/28 17:20.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class NavigationFragmentOne extends CommonBindingFragment<FragmentNavigationOneBinding> {

    private String title;

    public static NavigationFragmentOne newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        NavigationFragmentOne fragment = new NavigationFragmentOne();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void doLazyBusiness() {
        LogUtils.i(TAG, "doLazyBusiness" + title);
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
        title = bundle.getString("title");

        LogUtils.i(TAG, "initData" + title);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_navigation_one;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mBinding.tvTitle.setText(title);

        LogUtils.i(TAG, "initView" + title);
    }
}
