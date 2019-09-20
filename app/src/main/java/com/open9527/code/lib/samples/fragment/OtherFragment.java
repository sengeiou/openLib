package com.open9527.code.lib.samples.fragment;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.common.databinding.CommonBindingFragment;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.FragmentOtherBinding;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 15:50.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class OtherFragment extends CommonBindingFragment<FragmentOtherBinding> {

    public static OtherFragment newInstance() {
        Bundle args = new Bundle();
        OtherFragment fragment = new OtherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public CharSequence bindTitle() {
        return "OtherFragment";
    }

    @Override
    public void doLazyBusiness() {
        LogUtils.i(TAG, "doLazyBusiness");
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    protected void setStatusBar() {
        mTitleGroup.setVisibility(View.GONE);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_other;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mBinding.ivHead.setBackgroundColor(ColorUtils.getColor(R.color.color_00aaae));
        mBinding.tvMsg.setText("CommonBindingFragment");

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }
}
