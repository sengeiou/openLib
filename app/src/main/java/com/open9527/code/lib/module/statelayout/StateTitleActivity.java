package com.open9527.code.lib.module.statelayout;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.customview.empty.EmptyConfig;
import com.open9527.code.customview.empty.EmptyLayoutHelper;
import com.open9527.code.customview.empty.EmptyManager;
import com.open9527.code.customview.empty.IEmptyClick;
import com.open9527.code.image.compression.Constants;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityStateBinding;
import com.taobao.accs.ErrorCode;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/12 15:15.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class StateTitleActivity extends CommonBindingTitleActivity<ActivityStateBinding> {
    private EmptyManager emptyManager;

    @Override
    public void initData(@Nullable Bundle bundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_state;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        initEmptyLayou();
        applyDebouncingClickListener(mBinding.tvDesc);
    }

    private void initEmptyLayou() {
        //配置 状态布局管理器
        emptyManager = EmptyManager.build(EmptyConfig.builder()
                .setContentLayout(mContentView)
                .setEmptyLayoutHelper(new EmptyLayoutHelper(mContentView))
                .setIEmptyClick(new IEmptyClick() {
                    @Override
                    public void onErrorRetry(View view) {
                        emptyManager.showSuccessLayout();
                    }
                })
                .create());
        emptyManager.showEmptyLayout(EmptyManager.ERROR);
    }

    @Override
    public void onDebouncingClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.tv_desc:
                //加载中..
                emptyManager.showEmptyLayout(EmptyManager.LOADING);
                break;
            default:
                break;
        }
    }
}
