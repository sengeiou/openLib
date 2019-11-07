package com.open9527.code.lib.module.other;

import android.os.Bundle;
import android.view.View;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;

import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityDrawableBinding;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/5 14:07.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class DrawableTitleActivity extends CommonBindingTitleActivity<ActivityDrawableBinding> {
    @Override
    public boolean hasContentScroll() {
        return true;
    }

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_drawable;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {


    }
}
