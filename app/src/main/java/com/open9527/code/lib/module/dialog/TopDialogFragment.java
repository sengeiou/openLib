package com.open9527.code.lib.module.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.open9527.code.base.dialog.BaseDialogFragment;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/4 15:22.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class TopDialogFragment extends BaseDialogFragment {
    private static final String KEY_LAYOUT_RES = "centered_layout_res";
    private static final String KEY_HEIGHT = "centered_height";
    private static final String KEY_WIDTH = "centered_width";
    private static final String KEY_DIM = "centered_dim";
    private static final String KEY_CANCEL_OUTSIDE = "centered_cancel_outside";
    private FragmentManager mFragmentManager;
    private boolean mIsCancelOutside = false;
    private String mTag = super.getFragmentTag();
    private float mDimAmount = super.getDimAmount();
    private int mHeight = super.getHeight();
    private int mWidth = super.getWidth();

    @LayoutRes
    private int mLayoutRes;
    private TopDialogFragment.ViewListener mViewListener;

    public static TopDialogFragment create(FragmentManager manager) {
        TopDialogFragment dialog = new TopDialogFragment();
        dialog.setFragmentManager(manager);
        return dialog;
    }

    /**
     * 异常状态重启的话取出状态
     *
     * @param savedInstanceState bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setLocal(Local.TOP);
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mLayoutRes = savedInstanceState.getInt(KEY_LAYOUT_RES);
            mHeight = savedInstanceState.getInt(KEY_HEIGHT);
            mWidth = savedInstanceState.getInt(KEY_WIDTH);
            mDimAmount = savedInstanceState.getFloat(KEY_DIM);
            mIsCancelOutside = savedInstanceState.getBoolean(KEY_CANCEL_OUTSIDE);
        }
    }

    /**
     * 异常状态下保存重要信息
     *
     * @param outState bundle
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(KEY_LAYOUT_RES, mLayoutRes);
        outState.putInt(KEY_HEIGHT, mHeight);
        outState.putInt(KEY_WIDTH, mWidth);
        outState.putFloat(KEY_DIM, mDimAmount);
        outState.putBoolean(KEY_CANCEL_OUTSIDE, mIsCancelOutside);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void bindView(View v) {
        if (mViewListener != null) {
            mViewListener.bindView(v);
        }
    }

    /**
     * 是否支持点击弹窗外部取消
     *
     * @return 默认可以取消
     */
    @Override
    protected boolean isCancel() {
        return mIsCancelOutside;
    }

    @Override
    public int getLayoutRes() {
        return mLayoutRes;
    }

    public TopDialogFragment setFragmentManager(FragmentManager manager) {
        mFragmentManager = manager;
        return this;
    }

    public TopDialogFragment setViewListener(TopDialogFragment.ViewListener listener) {
        mViewListener = listener;
        return this;
    }

    public TopDialogFragment setLayoutRes(@LayoutRes int layoutRes) {
        mLayoutRes = layoutRes;
        return this;
    }

    public TopDialogFragment setCancelOutside(boolean cancel) {
        mIsCancelOutside = cancel;
        return this;
    }

    public TopDialogFragment setTag(String tag) {
        mTag = tag;
        return this;
    }

    public TopDialogFragment setDimAmount(float dim) {
        mDimAmount = dim;
        return this;
    }

    public TopDialogFragment setHeight(int heightPx) {
        mHeight = heightPx;
        return this;
    }
    public TopDialogFragment setWidth(int widthPx) {
        mWidth = widthPx;
        return this;
    }
    @Override
    public float getDimAmount() {
        return mDimAmount;
    }

    @Override
    public int getHeight() {
        return mHeight;
    }

    @Override
    public int getWidth() {
        return mWidth;
    }

    @Override
    public String getFragmentTag() {
        return mTag;
    }


    public BaseDialogFragment show() {
        show(mFragmentManager);
        return this;
    }

    public void dismissDialogFragment() {
        dismissDialog();
    }

    public interface ViewListener {
        /**
         * 绑定
         *
         * @param v view
         */
        void bindView(View v);
    }

}
