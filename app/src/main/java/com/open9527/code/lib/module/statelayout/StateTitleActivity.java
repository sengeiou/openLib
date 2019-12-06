package com.open9527.code.lib.module.statelayout;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.open9527.code.common.databinding.CommonBindingTitleActivity;
import com.open9527.code.customview.empty.EmptyConfig;
import com.open9527.code.customview.empty.EmptyLayoutHelper;
import com.open9527.code.customview.empty.EmptyManager;
import com.open9527.code.customview.empty.IEmptyClick;
import com.open9527.code.image.compression.Constants;
import com.open9527.code.lib.R;
import com.open9527.code.lib.databinding.ActivityStateBinding;
import com.open9527.code.lib.model.LaunchModel;
import com.taobao.accs.ErrorCode;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/12 15:15.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class StateTitleActivity extends CommonBindingTitleActivity<ActivityStateBinding> implements IEmptyClick {
    private EmptyManager emptyManager;
    private StateTitleViewModel mViewModel;

    @Override
    public void initData(@Nullable Bundle bundle) {
        mViewModel = ViewModelProviders.of(this).get(StateTitleViewModel.class);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_state;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState, @Nullable View contentView) {
        mBinding.setVm(mViewModel);
        initEmptyLayou();
        applyDebouncingClickListener(mBinding.tvDesc);
    }

    private void initEmptyLayou() {
        //配置 状态布局管理器
        emptyManager = EmptyManager.build(EmptyConfig.builder()
                .setContentLayout(mRootView)
                .setEmptyLayoutHelper(new EmptyLayoutHelper(mRootView))
                .setIEmptyClick(this)
                .create());
        //加载错误
        emptyManager.showEmptyLayout(EmptyManager.ERROR);
        mViewModel.observableFieldLaunchModel.set(new LaunchModel("加载中..."));
    }

    @Override
    public void onDebouncingClick(@NonNull View view) {
        switch (view.getId()) {
            case R.id.tv_desc:
                //加载中..
//                emptyManager.showEmptyLayout(EmptyManager.LOADING);
                mViewModel.observableFieldLaunchModel.set(new LaunchModel("再次点击...."));
                break;
            default:
                break;
        }
    }

    /**
     * 错误重试
     *
     * @param view
     */
    @Override
    public void onErrorRetry(View view) {
        emptyManager.showSuccessLayout();
    }
}
