package com.open9527.code.common.databinding;


import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.open9527.code.common.activity.CommonScreenActivity;

/**
 * Created by     : open9527
 * Created times  : on 2019/10/17 10:45.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class CommonBindingActivity<D extends ViewDataBinding> extends CommonScreenActivity {
    protected D mBinding;

    @Override
    public void setRootLayout(int layoutId) {
        super.setRootLayout(layoutId);
        //mBinding
        mBinding = DataBindingUtil.bind(mContentView);
    }
}
