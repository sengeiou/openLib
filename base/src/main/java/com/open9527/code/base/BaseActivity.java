package com.open9527.code.base;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.ClickUtils;

/**
 * Created by     : open9527
 * Created times  : on 2019/8/22 17:35.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public abstract class BaseActivity extends AppCompatActivity implements IBaseView {

    protected final String TAG = getClass().getSimpleName();

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onDebouncingClick(v);
        }
    };

    protected View mContentView;
    protected Activity mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mActivity = this;
        super.onCreate(savedInstanceState);
        initData(getIntent().getExtras());
        setContentView();
        initView(savedInstanceState, mContentView);
        doBusiness();
    }

    @Override
    public void setContentView() {
        if (bindLayout() <= 0) return;
        mContentView = LayoutInflater.from(this).inflate(bindLayout(), null);
        setContentView(mContentView);
    }


    public void applyDebouncingClickListener(View... views) {
        ClickUtils.applyGlobalDebouncing(views, mClickListener);
        ClickUtils.applyPressedViewScale(views);
    }

}
