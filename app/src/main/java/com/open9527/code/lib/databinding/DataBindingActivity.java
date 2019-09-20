package com.open9527.code.lib.databinding;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.lib.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/23 13:42.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class DataBindingActivity extends CommonBindingActivity<ActivityDatabindingBinding> {
    @Override
    public CharSequence bindTitle() {
        return "DataBindingActivity";
    }

    @Override
    public void initData(@Nullable Bundle bundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_databinding;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mBinding.tvMsg.setText("CommonBindingActivity");
    }

    @Override
    public void doBusiness() {

    }

    @Override
    public void onDebouncingClick(@NonNull View view) {

    }
}
