package com.open9527.code.lib.module.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.open9527.code.lib.R;
import com.open9527.code.lib.module.fragment.common.LazyFragment;

/**
 * Created by     : open9527
 * Created times  : on 2019/11/28/028 16:06.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class FragmentOne extends LazyFragment {
    private static final String TAG = "FragmentOne";
    private String bundleString;

    public static FragmentOne newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        FragmentOne fragment = new FragmentOne();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView(View view) {

        Bundle bundle = getArguments();
        TextView textView = view.findViewById(R.id.tv_title);
        bundleString = bundle.getString("title", "FragmentOne");
        textView.setText(bundleString);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        LogUtils.i(TAG, "initView--->" + bundleString);
    }

    @Override
    protected void initLazyData() {
        LogUtils.i(TAG, "initLazyData--->" + bundleString);
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_navigation_one;
    }


}
