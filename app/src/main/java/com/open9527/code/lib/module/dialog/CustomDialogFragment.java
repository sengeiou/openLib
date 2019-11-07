package com.open9527.code.lib.module.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;

import com.blankj.utilcode.util.ScreenUtils;
import com.open9527.code.base.dialog.BaseDialogFragment;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/4 15:36.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CustomDialogFragment extends BaseDialogFragment {
    private static final String KEY_LAYOUT_RES = "custom_layout_res";
    private static final String KEY_HEIGHT = "custom_height";
    private static final String KEY_WIDTH = "custom_width";
    private static final String KEY_DIM = "custom_dim";
    private static final String KEY_X = "custom_x";
    private static final String KEY_Y = "custom_y";
    private static final String KEY_GRAVITY = "custom_gravity";
    private static final String KEY_CANCEL_OUTSIDE = "custom_cancel_outside";

    private FragmentManager mFragmentManager;
    private boolean mIsCancelOutside = false;
    private String mTag = super.getFragmentTag();
    private float mDimAmount = super.getDimAmount();
    private int mHeight = super.getHeight();
    private int mWidth = super.getWidth();
    private float mX = super.getX();
    private float mY = super.getY();
    private Dialog dialog = super.getDialog();
    private int mGravity = super.getGravity();
    @LayoutRes
    private int mLayoutRes;
    private CustomDialogFragment.ViewListener mViewListener;


    public static CustomDialogFragment create(FragmentManager manager) {
        CustomDialogFragment dialog = new CustomDialogFragment();
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

        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            mLayoutRes = savedInstanceState.getInt(KEY_LAYOUT_RES);
            mHeight = savedInstanceState.getInt(KEY_HEIGHT);
            mWidth = savedInstanceState.getInt(KEY_WIDTH);
            mDimAmount = savedInstanceState.getFloat(KEY_DIM);
            mX = savedInstanceState.getInt(KEY_X);
            mY = savedInstanceState.getInt(KEY_Y);
            mGravity = savedInstanceState.getInt(KEY_GRAVITY);
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
        outState.putFloat(KEY_X, mX);
        outState.putFloat(KEY_Y, mY);
        outState.putInt(KEY_GRAVITY, mGravity);
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

    public CustomDialogFragment setFragmentManager(FragmentManager manager) {
        mFragmentManager = manager;
        return this;
    }

    public CustomDialogFragment setViewListener(CustomDialogFragment.ViewListener listener) {
        mViewListener = listener;
        return this;
    }

    public CustomDialogFragment setLayoutRes(@LayoutRes int layoutRes) {
        mLayoutRes = layoutRes;
        return this;
    }

    public CustomDialogFragment setCancelOutside(boolean cancel) {
        mIsCancelOutside = cancel;
        return this;
    }

    public CustomDialogFragment setTag(String tag) {
        mTag = tag;
        return this;
    }

    public CustomDialogFragment setDimAmount(float dim) {
        mDimAmount = dim;
        return this;
    }

    public CustomDialogFragment setHeight(int heightPx) {
        mHeight = heightPx;
        return this;
    }

    public CustomDialogFragment setWidth(int WidthPx) {
        mWidth = WidthPx;
        return this;
    }


    public CustomDialogFragment setX(float x) {
        mX = x;

        return this;
    }

    public CustomDialogFragment setY(float y) {
        mY = y;
        return this;
    }

    public CustomDialogFragment setGravity(int gravity) {
        mGravity = gravity;
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
    public float getX() {
        return mX;
    }

    @Override
    public float getY() {
        return mY;
    }

    @Override
    public int getGravity() {
        return mGravity;
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

    @Override
    public void setDialogGravity() {
        if (dialog == null) {
            dialog = getDialog();
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (getActivity() == null || getActivity().getWindowManager() == null) return;
            getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
            layoutParams.gravity = getGravity();//对齐方式
            layoutParams.y = (int)getX();//距离头部距离
            layoutParams.x = (int)getY();
            layoutParams.width = getWidth() > 0 ? getWidth() : ScreenUtils.getScreenWidth();//设置宽
            layoutParams.height = getHeight() > 0 ? getHeight() : ViewGroup.LayoutParams.WRAP_CONTENT;//设置高度
            dialog.getWindow().setAttributes(layoutParams);
        }
    }

}




