package com.open9527.empty;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * 空布局(可自定义)
 */
public class EmptyLayout extends FrameLayout {

    private ViewGroup emptyView;
    private ImageView mIvDefault;
    private TextView mTvDefault;

    public EmptyLayout(@NonNull Context context) {
        this(context, null);
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        setClickable(true);
//        setFocusable(true);
//        setFocusableInTouchMode(true);
        initLayout();
    }

    /**
     * 初始化提示的布局
     */
    private void initLayout() {
        emptyView = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.empty_default, this, false);
        mIvDefault = emptyView.findViewById(R.id.iv_default);
        mTvDefault = emptyView.findViewById(R.id.tv_default);

        if (emptyView.getBackground() == null) {
            // 默认使用 windowBackground 作为背景
            TypedArray ta = getContext().obtainStyledAttributes(new int[]{android.R.attr.windowBackground});
            emptyView.setBackground(ta.getDrawable(0));
            ta.recycle();
        }
        addView(emptyView);
    }

    @SuppressLint("ResourceType")
    public void show(@DrawableRes int drawableRes, String msg) {
        Log.i("EmptyLayout", "msg:" + msg);
        if (mIvDefault != null) {
            if (drawableRes > 0) {
                mIvDefault.setVisibility(VISIBLE);
                mIvDefault.setImageResource(drawableRes);

            } else {
                mIvDefault.setVisibility(GONE);
            }
            Log.i("EmptyLayout", "mIvDefault:" + mIvDefault.getVisibility());
        }
        if (mTvDefault != null) {
            if (!TextUtils.isEmpty(msg)) {
                mTvDefault.setVisibility(VISIBLE);
                mTvDefault.setText(msg);
            } else {
                mTvDefault.setVisibility(GONE);
            }
            Log.i("EmptyLayout", "mTvDefault:" + mTvDefault.getVisibility());
        }
    }
}
