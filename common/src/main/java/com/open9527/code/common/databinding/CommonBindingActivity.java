package com.open9527.code.common.databinding;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.common.activity.CommonTitleActivity;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 14:53.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class CommonBindingActivity<D extends ViewDataBinding> extends CommonTitleActivity {
    protected D mBinding;

    @SuppressLint("ResourceType")
    @Override
    public void setRootLayout(int layoutId) {
        if (layoutId <= 0) return;
        super.setRootLayout(layoutId);
        //mBinding
        mBinding = DataBindingUtil.bind(getBindView());
    }

    /**
     * 获取 bindingView
     *
     * @return bindingView
     */
    protected View getBindView() {
        if (mContentView.getChildCount() > 0) {
            return mContentView.getChildAt(0);
        } else {
            LogUtils.i(TAG, "bindingView is null");
        }
        return null;
    }

}
