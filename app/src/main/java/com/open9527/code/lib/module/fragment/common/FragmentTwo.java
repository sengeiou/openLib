package com.open9527.code.lib.module.fragment.common;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.open9527.code.common.databinding.CommonBindingFragment;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.FragmentNavigationOneBinding;
import com.open9527.code.lib.module.customview.RadioRecycleViewActivity;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/29/029 10:27.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class FragmentTwo extends CommonBindingFragment<FragmentNavigationOneBinding> {

    private String bundleString;

    public static FragmentTwo newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        FragmentTwo fragment = new FragmentTwo();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_navigation_one;
    }
    @Override
    public void initData(@Nullable Bundle bundle) {
        Bundle arguments = getArguments();
        bundleString = arguments.getString("title", "FragmentTwo");
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mTitleGroup.setVisibility(View.GONE);
        mBinding.tvTitle.setText(bundleString);

        mBinding.tvTitle.setOnClickListener(v -> {
            ActivityUtils.startActivity(RadioRecycleViewActivity.class);
        });

    }
    @Override
    public void doBusiness() {
        logI(TAG, "doBusiness--->" + bundleString);
    }
}
