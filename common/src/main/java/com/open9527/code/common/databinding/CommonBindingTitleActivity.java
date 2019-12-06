package com.open9527.code.common.databinding;

import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.open9527.code.common.activity.CommonTitleActivity;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/12 14:53.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class CommonBindingTitleActivity<D extends ViewDataBinding> extends CommonTitleActivity {
    protected D mBinding;


    @Override
    public void bindingView() {
        if (null == mRootView) return;
        mBinding = DataBindingUtil.bind(getBindView());
    }

    /**
     * 获取 bindingView
     *
     * @return bindingView
     */
    protected View getBindView() {
        if (mRootView.getChildCount() > 0) {
            return mRootView.getChildAt(0);
        } else {
            logI(TAG, "bindingView is null");
        }
        return null;
    }

}
