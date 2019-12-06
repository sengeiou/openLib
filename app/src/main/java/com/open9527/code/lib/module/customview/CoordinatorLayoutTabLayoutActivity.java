package com.open9527.code.lib.module.customview;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.BarUtils;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityCoordinatorlayoutTablayoutBinding;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/10 13:20.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CoordinatorLayoutTabLayoutActivity extends CommonBindingActivity<ActivityCoordinatorlayoutTablayoutBinding> {
    @Override
    public void initData(@Nullable Bundle bundle) {
        BarUtils.setStatusBarLightMode(this, true);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_coordinatorlayout_tablayout;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {

    }
}
