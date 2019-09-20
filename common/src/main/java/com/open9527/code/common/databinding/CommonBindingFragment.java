package com.open9527.code.common.databinding;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.common.fragment.CommonTitleFragment;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/19 12:12.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class CommonBindingFragment<D extends ViewDataBinding> extends CommonTitleFragment {
    protected D mBinding;

    @Override
    public void setRootLayout(int layoutId) {
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
