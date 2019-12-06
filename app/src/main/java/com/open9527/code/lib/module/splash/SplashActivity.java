package com.open9527.code.lib.module.splash;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.blankj.utilcode.util.ActivityUtils;
import com.open9527.code.common.databinding.CommonBindingActivity;
import com.open9527.code.lib.MainActivity;
import com.open9527.code.lib.R;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/16 17:25.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class SplashActivity extends CommonBindingActivity {
    @Override
    public void initData(@Nullable Bundle bundle) {
        getWindow().setBackgroundDrawable(null);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        ActivityUtils.startActivity(MainActivity.class);
        finish();
    }
}
