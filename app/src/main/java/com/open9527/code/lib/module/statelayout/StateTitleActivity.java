package com.open9527.code.lib.module.statelayout;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityStateBinding;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/12 15:15.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class StateTitleActivity extends CommonBindingTitleActivity<ActivityStateBinding> {

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_state;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {

    }

}
