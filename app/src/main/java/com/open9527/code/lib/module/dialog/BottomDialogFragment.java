package com.open9527.code.lib.module.dialog;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.open9527.code.base.dialog.BaseDialogFragment;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/4 10:23.
 * E-Mail Address ：open_9527@163.com.
 * DESC :BottomDialogFragment.
 */
public class BottomDialogFragment extends com.open9527.code.base.dialog.BaseDialogFragment {

    private static final String KEY_LAYOUT_RES = "bottom_layout_res";
    private static final String KEY_HEIGHT = "bottom_height";
    private static final String KEY_DIM = "bottom_dim";
    private static final String KEY_CANCEL_OUTSIDE = "bottom_cancel_outside";

    private FragmentManager mFragmentManager;
    private boolean mIsCancelOutside = false;
    private String mTag = super.getFragmentTag();
    private float mDimAmount = super.getDimAmount();
    private int mHeight = super.getHeight();

    @LayoutRes
    private int mLayoutRes;
    private ViewListener mViewListener;

    public static BottomDialogFragment create(FragmentManager manager) {
        BottomDialogFragment dialog = new BottomDialogFragment();
        dialog.setFragmentManager(manager);
        return dialog;
    }

    /**
     * 异常状态重启的话取出状态
     * @param savedInstanceState                    bundle
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setLocal(Local.BOTTOM);
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mLayoutRes = savedInstanceState.getInt(KEY_LAYOUT_RES);
            mHeight = savedInstanceState.getInt(KEY_HEIGHT);
            mDimAmount = savedInstanceState.getFloat(KEY_DIM);
            mIsCancelOutside = savedInstanceState.getBoolean(KEY_CANCEL_OUTSIDE);
        }
    }

    /**
     * 异常状态下保存重要信息
     * @param outState                              bundle
     */
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(KEY_LAYOUT_RES, mLayoutRes);
        outState.putInt(KEY_HEIGHT, mHeight);
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
     * @return                          默认可以取消
     */
    @Override
    protected boolean isCancel() {
        return mIsCancelOutside;
    }

    @Override
    public int getLayoutRes() {
        return mLayoutRes;
    }

    public BottomDialogFragment setFragmentManager(FragmentManager manager) {
        mFragmentManager = manager;
        return this;
    }

    public BottomDialogFragment setViewListener(ViewListener listener) {
        mViewListener = listener;
        return this;
    }

    public BottomDialogFragment setLayoutRes(@LayoutRes int layoutRes) {
        mLayoutRes = layoutRes;
        return this;
    }

    public BottomDialogFragment setCancelOutside(boolean cancel) {
        mIsCancelOutside = cancel;
        return this;
    }

    public BottomDialogFragment setTag(String tag) {
        mTag = tag;
        return this;
    }

    public BottomDialogFragment setDimAmount(float dim) {
        mDimAmount = dim;
        return this;
    }

    public BottomDialogFragment setHeight(int heightPx) {
        mHeight = heightPx;
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
    public String getFragmentTag() {
        return mTag;
    }


    public BaseDialogFragment show() {
        show(mFragmentManager);
        return this;
    }

    public void dismissDialogFragment(){
        dismissDialog();
    }

    public interface ViewListener {
        /**
         * 绑定
         * @param v     view
         */
        void bindView(View v);
    }

}